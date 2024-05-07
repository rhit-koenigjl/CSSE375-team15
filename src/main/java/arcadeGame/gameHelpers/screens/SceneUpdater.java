package arcadeGame.gameHelpers.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import arcadeGame.gameComponents.imageManagers.GameImage;
import arcadeGame.gameHelpers.SceneManager;
import arcadeGame.levelManagers.Level;

public abstract class SceneUpdater {
    public static final int FONT_SIZE = 28;
    public static final int BACKGROUND_SIZE = 100;

    protected SceneManager sceneManager;
    protected Level level;

    protected SceneUpdater(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public boolean displayStats() {
        return false;
    }

    public void drawScene(Graphics2D g) {
        Dimension screenSize = g.getClipBounds().getSize();
        for (int i = 0; i < screenSize.getWidth(); i += BACKGROUND_SIZE) {
            for (int j = 0; j < screenSize.getHeight(); j += BACKGROUND_SIZE) {
                g.drawImage(GameImage.BACKGROUND.getImage(), i, j, BACKGROUND_SIZE, BACKGROUND_SIZE,
                        null);
            }
        }
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void onFirstLoad() {}

    public abstract void updateScene();

    // For testing purposes
    public abstract String getSceneName();

}
