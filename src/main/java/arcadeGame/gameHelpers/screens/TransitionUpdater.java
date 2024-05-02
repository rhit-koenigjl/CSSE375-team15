package arcadeGame.gameHelpers.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import arcadeGame.gameHelpers.SceneManager;
import arcadeGame.gameHelpers.transitions.MessageGenerator;
import arcadeGame.gameHelpers.transitions.TextGraphics;

public class TransitionUpdater extends SceneUpdater {
    private SceneUpdater gameUpdater;
    private String message;
    private int timer;

    public TransitionUpdater(SceneManager sceneManager, MessageGenerator generator) {
        super(sceneManager);
        this.gameUpdater = sceneManager.getCurrentScene();
        this.message = generator.generateEncouragingMessage();
        this.timer = 0;
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
        Font font = new Font("Monospaced", Font.BOLD, FONT_SIZE);
        FontMetrics metrics = g2.getFontMetrics(font);
        g2.setFont(font);
        g2.setColor(Color.WHITE);

        Rectangle boundingBox = g2.getClipBounds();
        double midY = boundingBox.getHeight() / 2 - metrics.getHeight() / 2;

        String timerMessage = "Next level in " + (400 - timer) / 100;
        double midX2 = boundingBox.getWidth() / 2 - metrics.stringWidth(timerMessage) / 2;
        g2.drawString(timerMessage, (int) midX2, (int) midY - metrics.getHeight() - 10);

        TextGraphics.drawMultilineText(message, g2);
    }

    @Override
    public String getSceneName() {
        return "transition";
    }

    // For testing purposes
    public void setTimer(int timer) {
        this.timer = timer;
    }

    @Override
    public boolean displayStats() {
        return true;
    }

}
