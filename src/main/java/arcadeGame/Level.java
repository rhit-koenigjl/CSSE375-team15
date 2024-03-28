package arcadeGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Level {
  private int levelIndex;
  private File levelFile;
  private List<String> levelLayout = new ArrayList<String>();
  private int levelHeight;
  private int levelWidth;

  // fields pertaining the current level
  private Player hero;
  private List<Tile> tiles = new ArrayList<Tile>();
  private List<Enemy> enemies = new ArrayList<Enemy>();
  private int numBombs = 0;
  private boolean heroHurt = false;

  public Level(File levelFile, int index, Player hero) {
    this.levelFile = levelFile;
    this.levelIndex = index;
    this.hero = hero;
  }

  /**
   * ensures: the creation of new levels from a string filename
   * 
   * @param inputFilename: the text file the level is taking data from
   */
  public Object[] generateLevel() {
    tiles.clear();
    enemies.clear();
    // HunterSeekers to be added after hero added
    List<int[]> hunterSeekersToAdd = new ArrayList<int[]>();
    List<String> l = loadLevel();
    for (int y = 0; y < l.size(); y++) { // reads line number
      for (int x = 0; x < l.get(y).length(); x++) { // reads line length
        char blockChar = l.get(y).charAt(x);
        switch (blockChar) {
          case '-':
            tiles.add(new Platform(x * 50, y * 50, 50, 20));
            break;
          case '|':
            tiles.add(new Wall(x * 50, y * 50, 50, 50));
            break;
          case '^':
            tiles.add(new Spike(x * 50, y * 50, 50));
            break;
          case 'P':
            hero.setX(x * 50 + 10);
            hero.setY(y * 50);
            hero.setWidth(30);
            hero.setHeight(40);
            hero.clearMovementSpeed();
            break;
          case 'E':
            Enemy enemy = new Enemy(x * 50 + 10, y * 50, 40, 40);
            enemies.add(enemy);
            break;
          case 'V':
            enemy = new Enemy(x * 50 + 10, y * 50, 40, 40, 0, 5);
            enemies.add(enemy);
            break;
          case 'H':
            enemy = new Enemy(x * 50 + 10, y * 50, 40, 40, 5, 0);
            enemies.add(enemy);
            break;
          case 'S':
            int[] addedHunterSeeker = {x * 50 + 10, y * 50, 40, 40};
            hunterSeekersToAdd.add(addedHunterSeeker); // adds HunterSeeker info to a ArrayList to
                                                       // be created after everything else
            break;
          case 'B':
            tiles.add(new Bomb(x * 50 + 10, y * 50, 30, 50));
            numBombs++;
            break;
          case 'M':
            tiles.add(new BouncePad(x * 50, y * 50 + 30, 50, 20));
            break;
          case 'C':
            enemies.add(new EnemyGenerator(x * 50 + 10, y * 50 + 10, 30, 30, enemies, hero));
            break;
          case 'D':
            enemies.add(new EnemySpawnerGenerator(x * 50 + 10, y * 50 + 10, 30, 30,
                enemies, hero));
            break;
        }
      }
    }
    for (int[] HSInfo : hunterSeekersToAdd) {
      // HunterSeeker needs a hero to track, so it cannot be created before the hero
      // Therefore, the HunterSeekers are added after everything else to guarantee the
      // hero has been
      // created beforehand
      HunterSeeker h = new HunterSeeker(HSInfo[0], HSInfo[1], HSInfo[2], HSInfo[3], hero);
      enemies.add(h);
    }
    levelHeight = l.size();
    levelWidth = l.get(0).length();
    return new Object[] {tiles, hero, enemies};
  }

  /**
   * @param inputFilename: the text file the level is pulling data from
   * @return an ArrayList<String> of text that represents a level
   */
  protected List<String> loadLevel() {
    List<String> levelLayout = new ArrayList<String>();
    Scanner scanner = null;
    try {
      scanner = new Scanner(
          ClassLoader.getSystemClassLoader().getResource(levelFile.toString()).openStream());
      String title = scanner.nextLine();
      System.out.println("Title: " + title);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        System.out.println(line);
        levelLayout.add(line);
      } // end while
      scanner.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
      System.exit(1);
    } // end try-catch
    return levelLayout;
  } // readFile

  private void handlePlayer(Map<Integer, Boolean> keys) {
    hero.update(keys, tiles);

    if (hero.getSpikeCollision()) {
      heroHurt = true;
    }
  }

  private void handleEnemies(UpdateState state) {
    List<Enemy> enemiesRemove = new ArrayList<Enemy>();
    List<Enemy> enemiesToAdd = new ArrayList<Enemy>();
    for (Enemy enemy : enemies) {
      int collisionType = hero.handleCollisions(enemy);
      if (collisionType == 1) {
        heroHurt = true;
      }
      if (collisionType == 2) {
        enemiesRemove.add(enemy);
      }
      enemy.update(tiles);
      if (enemy.getAdding()) {
        enemy.setAdding(false);
        enemiesToAdd.add(enemy.returnNew());
      }
      if (enemy.getSpikeCollision()) {
        enemiesRemove.add(enemy);
      }
    }
    for (Enemy e : enemiesRemove) {
      enemies.remove(e);
      state.incrementScore(50);
    }
    for (Enemy e : enemiesToAdd) {
      enemies.add(e);
    }
  }

  private void handleTiles(UpdateState state) {
    List<Tile> toRemove = new ArrayList<Tile>();
    for (Tile t : tiles) {
      if (t.shouldRemove()) {
        toRemove.add(t);
      }
    }
    for (Tile t : toRemove) {
      tiles.remove(t);
      state.incrementScore(25);
      numBombs--;
    }
  }

  private void handleBombs(UpdateState state, SceneManager sceneManager) {
    if (numBombs == 0) {
      state.incrementScore(100);
      if (levelIndex == state.getLevelCount() - 1) {
        // switch to endscreen
        sceneManager.switchScene(new WinUpdater(sceneManager));
      } else {
        state.setNextLevel(levelIndex + 1);
      }
    }
  }

  private void handleDebugControls(Map<Integer, Boolean> keys, UpdateState state,
      SceneManager sceneManager) {
    if (keys.getOrDefault(85, false) && levelIndex < state.getLevelCount() - 1) {
      state.setNextLevel(levelIndex + 1);
      keys.put(85, false);
    }
    if (keys.getOrDefault(68, false) && levelIndex > 0) {
      state.setNextLevel(levelIndex - 1);
      keys.put(68, false);
    }
    if (keys.getOrDefault(27, false)) {
      sceneManager
          .switchScene(new PauseUpdater(sceneManager, sceneManager.getCurrentScene(), keys, this));
      keys.remove(27);
    }
  }

  private void generateStarterLevel() {
    if (tiles.size() == 0) {
      generateLevel();
    }
  }

  public void update(Map<Integer, Boolean> keys, UpdateState state, SceneManager sceneManager) {
    generateStarterLevel();
    handlePlayer(keys);
    handleEnemies(state);
    handleTiles(state);
    handleBombs(state, sceneManager);
    handleDebugControls(keys, state, sceneManager);

    if (heroHurt) {
      state.heroLostLife();
    }
  }

  public void draw(Graphics2D g2, int score) {
    for (Tile t : tiles) {
      t.display(g2);
    }
    for (Enemy e : enemies) {
      e.drawActor(g2);
    }
    hero.drawActor(g2);
    g2.setColor(Color.blue);
    g2.drawString("Lives: " + hero.getNumberOfLives() + "      Level: " + levelIndex
        + "         Score: " + score, 25, 30);
  }

  public void handlePause(Graphics2D g2, Color previousColor) {
    Color pauseColor = new Color(0, 0, 0, 75);
    for (Tile t : tiles) {
      t.display(g2);
    }
    for (Enemy e : enemies) {
      e.drawActor(g2);
    }
    hero.drawActor(g2);
    g2.setColor(pauseColor);
    g2.fillRect(0, 0, levelWidth * 50 + 14, levelHeight * 50 + 37);
    g2.setColor(previousColor);
    g2.setColor(Color.blue);
    g2.drawString("Lives: " + hero.getNumberOfLives() + "      Level: " + levelIndex, 25, 30);
    g2.setColor(previousColor);
  }

  public void reset() {
    numBombs = 0;
    enemies.clear();
    tiles.clear();
    levelLayout.clear();
    generateLevel();
  }

  public int getHeight() {
    return levelHeight;
  }

  public int getWidth() {
    return levelWidth;
  }

  public int getIndex() {
    return levelIndex;
  }

}
