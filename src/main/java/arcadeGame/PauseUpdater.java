package arcadeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Map;

public class PauseUpdater extends SceneUpdater {
    private SceneUpdater gameUpdater;
    private Map<Integer, Boolean> keys;
    private Level level;

    public PauseUpdater(SceneManager sceneManager, SceneUpdater gameUpdater, Map<Integer, Boolean> keys, Level level) {
        super(sceneManager);
        this.gameUpdater = gameUpdater;
        this.keys = keys;
        this.level = level;
    }

    @Override
    public void updateScene() {
        if (keys.getOrDefault(27, false)) {
            sceneManager.switchScene(gameUpdater);
            keys.remove(27);
        }
    }

    @Override
    public void drawScene(Graphics2D g2, String shownString, int xMiddle, int yMiddle, int score) {
        Color previousColor = g2.getColor();
        g2.setFont(new Font("Monospaced", Font.BOLD, 28));
        level.handlePause(g2, previousColor);
		shownString = "Game Paused. Press Escape to Continue";
		g2.drawString(shownString, xMiddle, yMiddle);
    }
    
}
