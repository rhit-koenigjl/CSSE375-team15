package arcadeGame;

import java.awt.Graphics2D;

public abstract class SceneUpdater {
    protected static final int FONT_SIZE = 28;

    protected SceneManager sceneManager;
    protected Level level = null;

    SceneUpdater(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    boolean displayStats() {
        return false;
    }

    abstract void updateScene();

    abstract void drawScene(Graphics2D g);

    // For testing purposes
    abstract String getSceneName();
}
