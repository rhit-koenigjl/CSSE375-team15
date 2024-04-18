package arcadeGame;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class WinUpdater extends SceneUpdater {

    WinUpdater(SceneManager sceneManager) {
        super(sceneManager);
    }

    @Override
    void updateScene() {
        return;
    }

    @Override
    void drawScene(Graphics2D g2, int score) {
        String str1 = "Congrats! You Won!";
        String str2 = "Your score was " + score;

        Font font = new Font("Monospaced", Font.BOLD, FONT_SIZE);
        FontMetrics metrics = g2.getFontMetrics(font);
        g2.setFont(font);

        Rectangle boundingBox = g2.getClipBounds();
        double midX1 = boundingBox.getWidth() / 2 - metrics.stringWidth(str1) / 2;
        double midX2 = boundingBox.getWidth() / 2 - metrics.stringWidth(str2) / 2;
        double midY = boundingBox.getHeight() / 2 - metrics.getHeight() / 2;

        g2.drawString(str1, (int) midX1, (int) midY - FONT_SIZE / 2);
        g2.drawString(str2, (int) midX2, (int) midY + FONT_SIZE / 2);
    }

    String getSceneName() {
        return "win";
    }

}
