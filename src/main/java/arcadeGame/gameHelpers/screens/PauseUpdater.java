package arcadeGame.gameHelpers.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Map;
import arcadeGame.gameHelpers.SceneManager;
import arcadeGame.levelManagers.Level;

public class PauseUpdater extends SceneUpdater {
    private static final Color BACKGROUND_COLOR = new Color(0, 0, 0, 75);
    private static final int WIDTH_OFFSET = 14;
    private static final int HEIGHT_OFFSET = 37;

    private SceneUpdater gameUpdater;
    private Map<Integer, Boolean> keys;
    private Level level;

    public PauseUpdater(SceneManager sceneManager, SceneUpdater gameUpdater,
            Map<Integer, Boolean> keys, Level level) {
        super(sceneManager);
        this.gameUpdater = gameUpdater;
        this.keys = keys;
        this.level = level;
    }

    @Override
    public void updateScene() {
        if (keys.getOrDefault(KeyEvent.VK_ESCAPE, false)) {
            sceneManager.switchScene(gameUpdater);
            keys.remove(KeyEvent.VK_ESCAPE);
        }
    }

    @Override
    public void drawScene(Graphics2D g2) {
        super.drawScene(g2);
        level.draw(g2);

        g2.setColor(BACKGROUND_COLOR);
        g2.fillRect(0, 0, level.getWidth() + WIDTH_OFFSET, level.getHeight() + HEIGHT_OFFSET);
        g2.setColor(Color.RED);

        String str1 = "Game Paused.";
        String str2 = "Press Escape to Continue";

        Font font = new Font("Monospaced", Font.BOLD, FONT_SIZE);
        FontMetrics metrics = g2.getFontMetrics(font);
        g2.setFont(font);

        Rectangle boundingBox = g2.getClipBounds();
        double midX1 = boundingBox.getWidth() / 2 - metrics.stringWidth(str1) / 2;
        double midX2 = boundingBox.getWidth() / 2 - metrics.stringWidth(str2) / 2;
        double midY = boundingBox.getHeight() / 2 - metrics.getHeight() / 2;

        g2.drawString(str1, (int) midX1, (int) midY - metrics.getHeight());
        g2.drawString(str2, (int) midX2, (int) midY + metrics.getHeight());
    }

    @Override
    public String getSceneName() {
        return "pause";
    }

    @Override
    public boolean displayStats() {
        return true;
    }

}
