package arcadeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class InstructionsUpdater extends SceneUpdater{

    InstructionsUpdater(SceneManager sceneManager) {
        super(sceneManager);
    }

    @Override
    void updateScene() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateScene'");
    }

    @Override
    String getSceneName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSceneName'");
    }

    @Override
    void drawScene(Graphics2D g2) {
        super.drawScene(g2);
        Rectangle d = g2.getClipBounds();
        Image instructionScreen = MenuImage.INSTRUCTION_SCREEN.getImage();
        
        int imageXPos = (int) (d.getWidth() - instructionScreen.getWidth(null)) / 2;
        int imageYPos = (int) (d.getHeight() - instructionScreen.getHeight(null)) / 2;
        g2.drawImage(instructionScreen, imageXPos, imageYPos, null);
        
        g2.setColor(new Color(0, 0, 0));
        
        Font font = new Font("Monospaced", Font.BOLD, 23);
        g2.setFont(font);
        FontMetrics metrics = g2.getFontMetrics(font);

        int lineHeight = 25;

        String str1a = "Use the Arrow";
        String str1b = "Keys to Move";
        String str1c = "and Fly!";

        int midX1 = imageXPos + 350;
        int startY1 = imageYPos + 25;

        g2.drawString(str1a, midX1 - metrics.stringWidth(str1a) / 2, startY1);
        g2.drawString(str1b, midX1 - metrics.stringWidth(str1b) / 2, startY1 + lineHeight);
        g2.drawString(str1c, midX1 - metrics.stringWidth(str1c) / 2, startY1 + lineHeight * 2);

        String str2a = "Collect all the Gold to";
        String str2b = "Advance to the Next Level.";

        int midX2 = imageXPos + 200;
        int startY2 = imageYPos + 175;

        g2.drawString(str2a, midX2 - metrics.stringWidth(str2a) / 2, startY2);
        g2.drawString(str2b, midX2 - metrics.stringWidth(str2b) / 2, startY2 + lineHeight);

        String str3a = "Avoid the Spikes and";
        String str3b = "Enemies, Bounce on";
        String str3c = "their Heads!";

        int midX3 = imageXPos + 350;
        int startY3 = imageYPos + 275;

        g2.drawString(str3a, midX3 - metrics.stringWidth(str3a) / 2, startY3);
        g2.drawString(str3b, midX3 - metrics.stringWidth(str3b) / 2, startY3 + lineHeight);
        g2.drawString(str3c, midX3 - metrics.stringWidth(str3c) / 2, startY3 + lineHeight * 2);

        String str4a = "Use bounce pads";
        String str4b = "to fling yourself";
        String str4c = "across the dungeon!";
        
        int midX4 = imageXPos + 160;
        int startY4 = imageYPos + 400;

        g2.drawString(str4a, midX4 - metrics.stringWidth(str4a) / 2, startY4);
        g2.drawString(str4b, midX4 - metrics.stringWidth(str4b) / 2, startY4 + lineHeight);
        g2.drawString(str4c, midX4 - metrics.stringWidth(str4c) / 2, startY4 + lineHeight * 2);


        // Rectangle boundingBox = g2.getClipBounds();
        // double midX1 = boundingBox.getWidth() / 2 - metrics.stringWidth(str1) / 2;
        // double midX2 = boundingBox.getWidth() / 2 - metrics.stringWidth(str2) / 2;
        // double midY = boundingBox.getHeight() / 2 - metrics.getHeight() / 2;

        // g2.drawString(str1, (int) midX1, (int) midY - metrics.getHeight());
        // g2.drawString(str2, (int) midX2, (int) midY + metrics.getHeight());
    }
    
}
