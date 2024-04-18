package arcadeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TransitionUpdater extends SceneUpdater {
    private SceneUpdater gameUpdater;
    private MessageGenerator generator;
    private int timer;

    public TransitionUpdater(SceneManager sceneManager, MessageGenerator generator) {
        super(sceneManager);
        this.gameUpdater = sceneManager.getCurrentScene();
        this.generator = generator;
        this.timer = 0;
    }

    @Override
    public void updateScene() {
        this.timer++;
        if (timer > 300) {
            sceneManager.switchScene(gameUpdater);
            generator.requestMessage();
        }
    }

    @Override
    public void drawScene(Graphics2D g2, int score) {
        Font font = new Font("Monospaced", Font.BOLD, 28);
        FontMetrics metrics = g2.getFontMetrics(font);
        g2.setFont(font);
        g2.setColor(new Color(255, 255, 255));
        
        Rectangle boundingBox = g2.getClipBounds();
        double midY = boundingBox.getHeight() / 2 - metrics.getHeight() / 2;

        String timerMessage = "Next level in " + (400 - timer) / 100;
        double midX2 = boundingBox.getWidth() / 2 - metrics.stringWidth(timerMessage) / 2;
        g2.drawString(timerMessage, (int) midX2, (int) midY - metrics.getHeight() - 10);
        
        String[] words = generator.generateEncouragingMessage().split(" ");
        String currentLine = words[0];
        int y = (int) midY;
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

    String getSceneName() {
        return "transition";
    }

    // For testing purposes
    void setTimer(int timer) {
        this.timer = timer;
    }

}
