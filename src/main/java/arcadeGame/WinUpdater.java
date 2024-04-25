package arcadeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Map;

public class WinUpdater extends SceneUpdater {
    private Map<Integer, Boolean> keys;
    private SceneUpdater gameUpdater;
    private int score;

    WinUpdater(SceneManager sceneManager, Map<Integer, Boolean> keys, int score) {
        super(sceneManager);
        this.keys = keys;
        this.gameUpdater = sceneManager.getCurrentScene();
        this.score = score;
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
        String str1 = "Congrats! You Won!";
        String str2 = "Your score was " + score;
        String str3 = "Press space to play again";

        Font font = new Font("Monospaced", Font.BOLD, FONT_SIZE);
        FontMetrics metrics = g2.getFontMetrics(font);
        g2.setFont(font);

        Rectangle boundingBox = g2.getClipBounds();
        double midX1 = boundingBox.getWidth() / 2 - metrics.stringWidth(str1) / 2;
        double midX2 = boundingBox.getWidth() / 2 - metrics.stringWidth(str2) / 2;
        double midX3 = boundingBox.getWidth() / 2 - metrics.stringWidth(str3) / 2;
        double midY = boundingBox.getHeight() / 2 - metrics.getHeight() / 2;

        g2.setColor(Color.WHITE);
        g2.drawString(str1, (int) midX1, (int) midY - FONT_SIZE / 2);
        g2.drawString(str2, (int) midX2, (int) midY + FONT_SIZE / 2);
        g2.drawString(str3, (int) midX3, (int) midY + metrics.getHeight() * 2);
    }

    String getSceneName() {
        return "win";
    }

}
