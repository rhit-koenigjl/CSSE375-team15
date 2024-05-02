package arcadeGame.gameHelpers.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import arcadeGame.gameHelpers.DeathType;
import arcadeGame.gameHelpers.SceneManager;

public class ResetUpdater extends SceneUpdater {
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
        double midY = boundingBox.getHeight() / 2 - metrics.getHeight() / 2;

        g2.drawString(str1, (int) midX1, (int) midY - FONT_SIZE / 2);
        g2.drawString(str2, (int) midX2, (int) midY + FONT_SIZE / 2);

        midY = boundingBox.getHeight() / 2 - metrics.getHeight() / 2;

        String[] words = deathType.getEncouragementString().split(" ");
        String currentLine = words[0];
        int y = (int) midY + 60;
        for (int i = 1; i < words.length; i++) {
            if (metrics.stringWidth(currentLine + words[i]) < boundingBox.getWidth()) {
                currentLine += " " + words[i];
            } else {
                double midX = boundingBox.getWidth() / 2 - metrics.stringWidth(currentLine) / 2;
                g2.drawString(currentLine, (int) midX, y);
                y += metrics.getHeight();
                currentLine = words[i];
            }
        }
        if (currentLine.trim().length() > 0) {
            double midX = boundingBox.getWidth() / 2 - metrics.stringWidth(currentLine) / 2;
            g2.drawString(currentLine, (int) midX, y);
        }
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
