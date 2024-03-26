package arcadeGame;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Map;

public class MenuUpdater extends SceneUpdater {
    private GameUpdater gameUpdater;
    private Map<Integer, Boolean> keys;

    public MenuUpdater(SceneManager sceneManager, GameUpdater gameUpdater, Map<Integer, Boolean> keys) {
        super(sceneManager);
        this.gameUpdater = gameUpdater;
        this.keys = keys;
    }

    @Override
    public void updateScene() {
        if (keys.getOrDefault(32, false)) {
            sceneManager.switchScene(gameUpdater);
        }
    }

    @Override
    public void drawScene(Graphics2D g2, String shownString, int xMiddle, int yMiddle, int score) {
        g2.setFont(new Font("Monospaced", Font.BOLD, 28));
        shownString = "Press Spacebar to Start";
        g2.drawString(shownString, xMiddle, yMiddle - 50);
        g2.drawString("Use the Arrow Keys to move", xMiddle, yMiddle);
        g2.drawString("Use Escape to pause the game", xMiddle, yMiddle + 50);
    }
    
}
