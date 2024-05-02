package arcadeGame.gameHelpers.transitions;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import arcadeGame.gameHelpers.screens.SceneUpdater;

public class TextGraphics {
    private static final int WIDTH_BUFFER = 100;

    public static void drawMultilineText(String text, Graphics2D g2) {
        Font font = new Font("Monospaced", Font.BOLD, SceneUpdater.FONT_SIZE);
        FontMetrics metrics = g2.getFontMetrics(font);
        g2.setFont(font);
        g2.setColor(Color.WHITE);

        Rectangle boundingBox = g2.getClipBounds();
        double midY = boundingBox.getHeight() / 2 - metrics.getHeight() / 2;

        String[] words = text.split(" ");
        String currentLine = words[0];
        int y = (int) midY;
        for (int i = 1; i < words.length; i++) {
            if (metrics.stringWidth(currentLine + words[i]) + WIDTH_BUFFER < boundingBox
                    .getWidth()) {
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
}
