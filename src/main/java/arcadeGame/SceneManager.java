package arcadeGame;

import java.awt.Graphics2D;

public class SceneManager {
    private SceneUpdater scene;

    SceneManager(SceneUpdater scene) {
        this.scene = scene;
    }

    void runScene() {
        this.scene.updateScene();
    }

    void switchScene(SceneUpdater newScene) {
        this.scene = newScene;
    }

    void drawScene(Graphics2D g, int score) {
        this.scene.drawScene(g, score);
    }

    SceneUpdater getCurrentScene() {
        return this.scene;
    }

    void setLevel(Level level) {
        this.scene.level = level;
    }

}
