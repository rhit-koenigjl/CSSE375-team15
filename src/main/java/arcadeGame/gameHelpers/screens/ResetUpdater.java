package arcadeGame.gameHelpers.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import arcadeGame.gameHelpers.DeathType;
import arcadeGame.gameHelpers.SceneManager;
import arcadeGame.gameHelpers.transitions.TextGraphics;

public class ResetUpdater extends SceneUpdater {
    private static final int Y_POS_OFFSET = 80;

    private int timer;
    private SceneUpdater gameUpdater;
    private DeathType deathType;

    public ResetUpdater(SceneManager sceneManager, SceneUpdater gameUpdater, DeathType deathType) {
        super(sceneManager);
        this.timer = 0;
        this.gameUpdater = gameUpdater;
        this.deathType = deathType;
    }

    @Override
    public void updateScene() {
        this.timer++;
        if (timer > 300) {
            sceneManager.switchScene(gameUpdater);
        }
    }

    @Override
    public void drawScene(Graphics2D g2) {
        super.drawScene(g2);
        String str1 = "You got hit!";
        String str2 = "Restarting the level in " + (400 - timer) / 100;

        Font font = new Font("Monospaced", Font.BOLD, FONT_SIZE);
        FontMetrics metrics = g2.getFontMetrics(font);
        g2.setFont(font);
        g2.setColor(new Color(255, 255, 255));

        Rectangle boundingBox = g2.getClipBounds();
        double midX1 = boundingBox.getWidth() / 2 - metrics.stringWidth(str1) / 2;
        double midX2 = boundingBox.getWidth() / 2 - metrics.stringWidth(str2) / 2;
        double midY = (boundingBox.getHeight() / 2 - metrics.getHeight() / 2) - Y_POS_OFFSET;

        g2.drawString(str1, (int) midX1, (int) midY - FONT_SIZE / 2);
        g2.drawString(str2, (int) midX2, (int) midY + FONT_SIZE / 2);

        TextGraphics.drawMultilineText(deathType.getEncouragementString(), g2);
    }

    @Override
    public String getSceneName() {
        return "reset";
    }

    @Override
    public boolean displayStats() {
        return true;
    }

}
