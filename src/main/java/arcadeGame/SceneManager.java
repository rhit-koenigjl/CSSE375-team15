package arcadeGame;

import java.awt.Graphics2D;

public class SceneManager {
    private SceneUpdater scene;
    public SceneManager(SceneUpdater scene) {
        this.scene = scene;
    }

    public void runScene() {
        this.scene.updateScene();
    }

    public void switchScene(SceneUpdater newScene) {
        this.scene = newScene;
    }

    public void drawScene(Graphics2D g, String showString, int xMiddle, int yMiddle, int score) {
        this.scene.drawScene(g, showString, xMiddle, yMiddle, score);
    }

    public SceneUpdater getCurrentScene() {
        return this.scene;
    }

    public void setLevel(Level level) {
        this.scene.level = level;
    }
}
