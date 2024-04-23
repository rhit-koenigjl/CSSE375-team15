package arcadeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
        String str1 = "Press the Space Bar to start";
        String str2 = "Use the Arrow Keys to move";
        String str3 = "Use Escape to pause the game";

        Font font = new Font("Monospaced", Font.BOLD, FONT_SIZE);
        FontMetrics metrics = g2.getFontMetrics(font);
        g2.setFont(font);
        g2.setColor(new Color(0, 0, 0));

        Rectangle boundingBox = g2.getClipBounds();
        double midX1 = boundingBox.getWidth() / 2 - metrics.stringWidth(str1) / 2;
        double midX2 = boundingBox.getWidth() / 2 - metrics.stringWidth(str2) / 2;
        double midX3 = boundingBox.getWidth() / 2 - metrics.stringWidth(str3) / 2;
        double midY = boundingBox.getHeight() / 2 - metrics.getHeight() / 2;

        g2.drawString(str1, (int) midX1, (int) midY - metrics.getHeight());
        g2.drawString(str2, (int) midX2, (int) midY);
        g2.drawString(str3, (int) midX3, (int) midY + metrics.getHeight());
    }

    String getSceneName() {
        return "menu";
    }

    // For testing purposes
    void setKeys(Map<Integer, Boolean> keys) {
        this.keys = keys;
    }

}
