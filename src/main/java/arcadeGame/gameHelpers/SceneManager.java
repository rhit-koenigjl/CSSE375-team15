package arcadeGame.gameHelpers;

import java.awt.Graphics2D;
import arcadeGame.gameHelpers.screens.SceneUpdater;
import arcadeGame.levelManagers.Level;

public class SceneManager {
    private SceneUpdater scene;

    public SceneManager(SceneUpdater scene) {
        this.scene = scene;
    }

    public void runScene() {
        this.scene.updateScene();
    }

    public void switchScene(SceneUpdater newScene) {
        newScene.onFirstLoad();
        this.scene = newScene;
    }

    public void drawScene(Graphics2D g) {
        this.scene.drawScene(g);
    }

    public SceneUpdater getCurrentScene() {
        return this.scene;
    }

    public void setLevel(Level level) {
        this.scene.setLevel(level);
    }

    public boolean displayStats() {
        return this.scene.displayStats();
    }

}
