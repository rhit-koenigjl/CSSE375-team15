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
        newScene.onFirstLoad();
        this.scene = newScene;
    }

    void drawScene(Graphics2D g) {
        this.scene.drawScene(g);
    }

    SceneUpdater getCurrentScene() {
        return this.scene;
    }

    void setLevel(Level level) {
        this.scene.level = level;
    }

    boolean displayStats() {
        return this.scene.displayStats();
    }

}
