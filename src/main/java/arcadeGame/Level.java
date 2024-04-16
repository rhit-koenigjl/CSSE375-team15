package arcadeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
  private GameImage gameImage;

  public Level(String levelPath, int index, Player hero) {
    this.levelPath = levelPath;
    this.levelIndex = index;
    this.hero = hero;
    this.gameImage = GameImage.BACKGROUND;
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

  public void update(Map<Integer, Boolean> keys, UpdateState state, SceneManager sceneManager) {
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
    for (int i = 0; i < levelWidth; i += 100) {
      for (int j = 0; j < levelHeight; j += 100) {
        g2.drawImage(gameImage.getImage(), i, j, 100, 100, null);
      }
    }
    for (Tile t : tiles) {
      t.display(g2);
    }
    for (Enemy e : enemies) {
      e.drawActor(g2);
    }
    hero.drawActor(g2);
    drawScore(g2, score);
  }

  public void drawScore(Graphics2D g2, int score) {
    g2.setColor(new Color(200, 255, 200));
    g2.setFont(new Font("Monospaced", Font.BOLD, 28));
    g2.drawString("Level: " + levelIndex, 200, 30);
  }

  public void reset() {
    numCoins = 0;
    heroHurt = false;
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
