package arcadeGame;

import java.awt.Graphics2D;
import java.util.Map;

public class MenuUpdater extends SceneUpdater {
    private GameUpdater gameUpdater;
    private Map<Integer, Boolean> keys;

    MenuUpdater(SceneManager sceneManager, GameUpdater gameUpdater,
            Map<Integer, Boolean> keys) {
        super(sceneManager);
        this.gameUpdater = gameUpdater;
        this.keys = keys;
    }

    @Override
    void updateScene() {
        if (keys.getOrDefault(32, false)) {
            sceneManager.switchScene(gameUpdater);
        }
    }

    @Override
    void drawScene(Graphics2D g2) {
        super.drawScene(g2);
        g2.drawImage(MenuImage.LOGO.getImage(), 0, 0, null);
        g2.drawImage(MenuImage.PLAY.getImage(), 100, 200, null);
        g2.drawImage(MenuImage.HELP.getImage(), 0, 225, null);
        g2.drawImage(MenuImage.CREDITS.getImage(), 200, 225, null);
    }

    String getSceneName() {
        return "menu";
    }

    // For testing purposes
    void setKeys(Map<Integer, Boolean> keys) {
        this.keys = keys;
    }

}
