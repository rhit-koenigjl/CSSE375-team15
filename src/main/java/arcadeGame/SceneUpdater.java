package arcadeGame;

import java.awt.Graphics2D;

public abstract class SceneUpdater {
    protected SceneManager sceneManager;

    public SceneUpdater(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public abstract void updateScene();
    public abstract void drawScene(Graphics2D g, String showString, int xMiddle, int yMiddle, int score);
}
