package arcadeGame;

import java.awt.Dimension;
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

    void drawScene(Graphics2D g) {
        Dimension screenSize = g.getClipBounds().getSize();
        for (int i = 0; i < screenSize.getWidth(); i += 100) {
            for (int j = 0; j < screenSize.getHeight(); j += 100) {
                g.drawImage(GameImage.BACKGROUND.getImage(), i, j, 100, 100, null);
            }
        }
    }

    // For testing purposes
    abstract String getSceneName();
}
