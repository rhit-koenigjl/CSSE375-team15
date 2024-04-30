package arcadeGame.levelManagers;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import arcadeGame.gameComponents.Enemy;
import arcadeGame.gameComponents.Player;
import arcadeGame.gameComponents.Tile;
import arcadeGame.gameComponents.spriteAnimations.CollectedCoinSprite;
import arcadeGame.gameComponents.spriteAnimations.DeadEnemySprite;
import arcadeGame.gameComponents.spriteAnimations.DisplaySprite;
import arcadeGame.gameHelpers.SceneManager;
import arcadeGame.gameHelpers.UpdateState;
import arcadeGame.gameHelpers.screens.PauseUpdater;

public class Level {
    private int levelIndex;
    private String levelPath;
    private int levelHeight;
    private int levelWidth;
    private Player hero;
    private List<Tile> tiles;
    private List<Enemy> enemies;
    private int numCoins = 0;
    private boolean heroHurt = false;
    private List<DisplaySprite> sprites;

    public Level(String levelPath, int index, Player hero) {
        this.levelPath = levelPath;
        this.levelIndex = index;
        this.hero = hero;
        this.sprites = new ArrayList<DisplaySprite>();
    }

    public Object[] generateLevel() {
        sprites.clear();
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
        hero.update(keys, tiles, sprites);

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
                if (hero.getVy() > 0) {
                    hero.setVy(-hero.getWidth() / 5);
                }
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
            sprites.add(new DeadEnemySprite(e.getX(), e.getY(), e.getWidth(), e.getHeight(),
                    e.getVx(), e.getVy()));
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
                sprites.add(
                        new CollectedCoinSprite(t.getX(), t.getY(), t.getWidth(), t.getHeight()));
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
                state.handleWinGame();
            } else {
                state.transitionNextLevel();
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
            sceneManager.switchScene(
                    new PauseUpdater(sceneManager, sceneManager.getCurrentScene(), keys, this));
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

    public void draw(Graphics2D g2) {
        for (Tile t : tiles) {
            t.display(g2);
        }
        for (Enemy e : enemies) {
            e.drawActor(g2);
        }
        hero.drawActor(g2);

        for (DisplaySprite ds : sprites) {
            ds.display(g2);
            ds.updatePosition();
        }
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
