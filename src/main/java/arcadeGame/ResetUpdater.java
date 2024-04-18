package arcadeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ResetUpdater extends SceneUpdater {
    private int timer;
    private SceneUpdater gameUpdater;

    public ResetUpdater(SceneManager sceneManager, SceneUpdater gameUpdater) {
        super(sceneManager);
        this.timer = 0;
        this.gameUpdater = gameUpdater;
    }

    @Override
    public void updateScene() {
        this.timer++;
        if (timer > 350) {
            sceneManager.switchScene(gameUpdater);
        }
    }

    @Override
    public void drawScene(Graphics2D g2, int score) {
        String str1 = "You got hit! Restarting the level in " + (350 - timer) / 100;

        Font font = new Font("Monospaced", Font.BOLD, 28);
        FontMetrics metrics = g2.getFontMetrics(font);
        g2.setFont(font);
        g2.setColor(new Color(255, 255, 255));

        Rectangle boundingBox = g2.getClipBounds();
        double midX1 = boundingBox.getWidth() / 2 - metrics.stringWidth(str1) / 2;
        double midY = boundingBox.getHeight() / 2 - metrics.getHeight() / 2;

        g2.drawString(str1, (int) midX1, (int) midY);
    }

    String getSceneName() {
        return "reset";
    }

}
