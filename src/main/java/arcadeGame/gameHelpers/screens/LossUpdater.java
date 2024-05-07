package arcadeGame.gameHelpers.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Map;
import arcadeGame.gameHelpers.SceneManager;

public class LossUpdater extends SceneUpdater {
    private final Map<Integer, Boolean> keys;
    private final SceneUpdater gameUpdater;
    private final int score;

    public LossUpdater(SceneManager sceneManager, Map<Integer, Boolean> keys, int score) {
        super(sceneManager);
        this.keys = keys;
        this.gameUpdater = sceneManager.getCurrentScene();
        this.score = score;
    }

    @Override
    public void updateScene() {
        if (keys.getOrDefault(KeyEvent.VK_SPACE, false)) {
            sceneManager.switchScene(gameUpdater);
        }
    }

    @Override
    public void drawScene(Graphics2D g2) {
        super.drawScene(g2);
        String str1 = "You ran out of lives!";
        String str2 = "Your score was: " + score;
        String str3 = "Press space to play again";

        Font font = new Font("Monospaced", Font.BOLD, FONT_SIZE);
        FontMetrics metrics = g2.getFontMetrics(font);
        g2.setFont(font);

        Rectangle boundingBox = g2.getClipBounds();
        double midX1 = boundingBox.getWidth() / 2 - (double) metrics.stringWidth(str1) / 2;
        double midX2 = boundingBox.getWidth() / 2 - (double) metrics.stringWidth(str2) / 2;
        double midX3 = boundingBox.getWidth() / 2 - (double) metrics.stringWidth(str3) / 2;
        double midY = boundingBox.getHeight() / 2 - (double) metrics.getHeight() / 2;

        g2.setColor(Color.WHITE);
        g2.drawString(str1, (int) midX1, (int) midY - metrics.getHeight() / 2);
        g2.drawString(str2, (int) midX2, (int) midY + metrics.getHeight() / 2);
        g2.drawString(str3, (int) midX3, (int) midY + metrics.getHeight() * 2);
    }

    @Override
    public String getSceneName() {
        return "loss";
    }

}
