package arcadeGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

public class Level {
  private int levelIndex;
  private String levelPath;
  private int levelHeight;
  private int levelWidth;

  // fields pertaining the current level
  private Player hero;
  private List<Tile> tiles;
  private List<Enemy> enemies;
  private int numCoins = 0;
  private boolean heroHurt = false;

  private Image backgroundImage;

  public Level(String levelPath, int index, Player hero) {
    this.levelPath = levelPath;
    this.levelIndex = index;
    this.hero = hero;
    this.backgroundImage =
        new ImageIcon(ClassLoader.getSystemClassLoader().getResource("images/background.png"))
            .getImage();
  }

  /**
   * ensures: the creation of new levels from a string filename
   * 
   * @param inputFilename: the text file the level is taking data from
   */
  public Object[] generateLevel() {
    LevelLoader ll = new LevelLoader(levelPath);
    ll.loadLevel();
    tiles = ll.getTiles();
    enemies = ll.getEnemies();
    hero = ll.getPlayer();
    numCoins = ll.getNumCoins();
    levelHeight = ll.getHeight();
    levelWidth = ll.getWidth();
    return new Object[] {tiles, hero, enemies};
  }

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
      numCoins--;
    }
  }

  private void handleCoins(UpdateState state, SceneManager sceneManager) {
    if (numCoins == 0) {
      state.incrementScore(100);
      if (levelIndex == state.getLevelCount() - 1) {
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
    handleCoins(state, sceneManager);
    handleDebugControls(keys, state, sceneManager);

    if (heroHurt) {
      state.heroLostLife();
    }
  }

  public void draw(Graphics2D g2, int score) {
    for (int i = 0; i < levelWidth; i++) {
      for (int j = 0; j < levelHeight; j++) {
        g2.drawImage(this.backgroundImage, i * 100, j * 100, 100, 100, null);
      }
    }
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
    numCoins = 0;
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
