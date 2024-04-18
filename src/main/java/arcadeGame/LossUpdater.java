package arcadeGame;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class LossUpdater extends SceneUpdater {
    private Level level;

    LossUpdater(SceneManager sceneManager, Level level) {
        super(sceneManager);
        this.level = level;
    }

    @Override
    void updateScene() {
        return;
    }

    @Override
    void drawScene(Graphics2D g2, int score) {
        String str1 = "You ran out of lives on level " + level.getIndex();
        String str2 = "Your score was: " + score;

        Font font = new Font("Monospaced", Font.BOLD, FONT_SIZE);
        FontMetrics metrics = g2.getFontMetrics(font);
        g2.setFont(font);

        Rectangle boundingBox = g2.getClipBounds();
        double midX1 = boundingBox.getWidth() / 2 - metrics.stringWidth(str1) / 2;
        double midX2 = boundingBox.getWidth() / 2 - metrics.stringWidth(str1) / 2;
        double midY = boundingBox.getHeight() / 2 - metrics.getHeight() / 2;

        g2.drawString(str1, (int) midX1, (int) midY - metrics.getHeight() / 2);
        g2.drawString(str2, (int) midX2, (int) midY + metrics.getHeight() / 2);
    }

    String getSceneName() {
        return "loss";
    }

}
