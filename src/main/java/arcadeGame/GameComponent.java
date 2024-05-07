package arcadeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JFrame;
import arcadeGame.gameComponents.Player;
import arcadeGame.gameHelpers.SceneManager;
import arcadeGame.gameHelpers.UpdateState;
import arcadeGame.gameHelpers.screens.GameUpdater;
import arcadeGame.gameHelpers.screens.LossUpdater;
import arcadeGame.gameHelpers.screens.MenuUpdater;
import arcadeGame.gameHelpers.screens.ResetUpdater;
import arcadeGame.gameHelpers.screens.SceneUpdater;
import arcadeGame.gameHelpers.screens.TransitionUpdater;
import arcadeGame.gameHelpers.screens.WinUpdater;
import arcadeGame.gameHelpers.transitions.AiMessageGenerator;
import arcadeGame.gameHelpers.transitions.MessageGenerator;
import arcadeGame.levelManagers.Level;
import arcadeGame.stateComponents.MouseListener;

public class GameComponent extends JComponent {
    private static final String LEVEL_DIRECTORY = "levels/user_test_level_set/";
    private static final String LEVELS_DEFINITION = LEVEL_DIRECTORY + "levels";
    private static final int STARTING_LIVES = 3;
    private static final int STARTING_SCORE = 0;
    private static final int FONT_SIZE = 28;
    private static final Color TEXT_COLOR = new Color(200, 255, 200);
    private static final int TEXT_X = 25;
    private static final int TEXT_Y = 30;

    private final MessageGenerator generator = new AiMessageGenerator();
    private SceneManager sceneManager;
    private int score = STARTING_SCORE;
    private int lives = STARTING_LIVES;
    private String[] levelFiles;
    private Level currentLevel;
    private final Player hero = new Player(0, 0, 0, 0);
    private final Map<Integer, Boolean> keys = new HashMap<>();
    private final JFrame frame;

    public GameComponent(JFrame frame, MouseListener mouseListener) {
        buildLevelsList();
        this.frame = frame;
        this.currentLevel = new Level(LEVEL_DIRECTORY + levelFiles[0], 0, hero);
        this.sceneManager = new SceneManager(null);
        UpdateState state = new UpdateState(this);
        GameUpdater g = new GameUpdater(sceneManager, currentLevel, keys, state);
        MenuUpdater m = new MenuUpdater(sceneManager, g, mouseListener);
        this.sceneManager.switchScene(m);
    }

    private void buildLevelsList() {
        try {
            InputStream levelNames =
                    ClassLoader.getSystemClassLoader().getResourceAsStream(LEVELS_DEFINITION);
            levelFiles = Arrays.asList(new String(levelNames.readAllBytes()).split("\n"))
                    .toArray(String[]::new);
        } catch (Exception e) {
            System.err.println("Could not load levels");
        }
    }

    public void loadLevelByIndex(int index) {
        switchLevel(levelFiles[index], index);
    }

    public void handleKey(int keyCode, boolean newVal) {
        keys.put(keyCode, newVal);
    }

    public void updateState() {
        sceneManager.runScene();
    }

    public void drawScreen() {
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        sceneManager.drawScene(g2);
        if (sceneManager.displayStats()) {
            g2.setFont(new Font("Monospaced", Font.BOLD, FONT_SIZE));
            g2.setColor(TEXT_COLOR);
            g2.drawString("Lives: " + lives, TEXT_X, TEXT_Y);
            g2.drawString("Score: " + score, TEXT_X, 2 * TEXT_Y);
        }
    }

    void switchLevel(String newLevel, int index) {
        if (index == currentLevel.getIndex()) {
            currentLevel.reset();
            return;
        } else {
            this.currentLevel = new Level(LEVEL_DIRECTORY + newLevel, index, hero);
            this.sceneManager.setLevel(currentLevel);
            currentLevel.generateLevel();
        }
        resize();
        frame.repaint();
    }

    void levelReset() {
        loadLevelByIndex(currentLevel.getIndex());
    }

    public void loseLife() {
        hero.loseLife();
        lives--;
        levelReset();
        if (lives > 0) {
            sceneManager.switchScene(new ResetUpdater(sceneManager, sceneManager.getCurrentScene(),
                    currentLevel.getDeathType()));
        } else {
            restart(false);
        }
    }

    public void winGame() {
        restart(true);
    }

    private void restart(boolean win) {
        loadLevelByIndex(0);
        SceneUpdater newScene = win ? new WinUpdater(sceneManager, keys, score)
                : new LossUpdater(sceneManager, keys, score);
        sceneManager.switchScene(newScene);
        lives = STARTING_LIVES;
        score = STARTING_SCORE;
    }

    public int getLevelCount() {
        return levelFiles.length;
    }

    public void incrementScore(int score) {
        this.score += score;
    }

    public void nextLevel() {
        loadLevelByIndex(currentLevel.getIndex() + 1);
        sceneManager.switchScene(new TransitionUpdater(sceneManager, generator));
    }

    public void resize() {
        this.frame.setSize(currentLevel.getWidth() + 14, currentLevel.getHeight() + 37);
    }

    void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

}
