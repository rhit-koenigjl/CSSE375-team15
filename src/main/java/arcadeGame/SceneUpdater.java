package arcadeGame;

import java.awt.Graphics2D;

public abstract class SceneUpdater {
    protected static final int FONT_SIZE = 28;

    protected SceneManager sceneManager;
    protected Level level = null;

    SceneUpdater(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    abstract void updateScene();

    abstract void drawScene(Graphics2D g, int score);

    // For testing purposes
    abstract String getSceneName();
}
