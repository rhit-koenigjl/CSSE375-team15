package arcadeGame.gameHelpers.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import arcadeGame.gameComponents.imageManagers.MenuImage;
import arcadeGame.gameHelpers.SceneManager;
import arcadeGame.stateComponents.MouseListener;

public class InstructionsUpdater extends SceneUpdater {
    private static final int BACK_BUTTON_X = 10;
    private static final int BACK_BUTTON_Y = 10;
    private static final int BACK_BUTTON_WIDTH = 50;
    private static final int BACK_BUTTON_HEIGHT = 75;
    private static final int LINE_HEIGHT = 25;
    private static final int FONT_SIZE = 23;
    private static final int ONE_THREE_X_POS = 350;
    private static final int TWO_X_POS = 200;
    private static final int FOUR_X_POS = 160;
    private static final int ONE_Y_POS = 25;
    private static final int TWO_Y_POS = 175;
    private static final int THREE_Y_POS = 275;
    private static final int FOUR_Y_POS = 400;

    private SceneUpdater menuScene;
    private MouseListener mouseListener;

    InstructionsUpdater(SceneManager sceneManager, SceneUpdater menuScene,
            MouseListener mouseListener) {
        super(sceneManager);
        this.menuScene = menuScene;
        this.mouseListener = mouseListener;
    }

    @Override
    public void updateScene() {
        mouseListener.addClickAction(
                new Rectangle(BACK_BUTTON_X, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT),
                menuScene);
    }

    @Override
    public String getSceneName() {
        return "Instruction Screen";
    }

    @Override
    public void drawScene(Graphics2D g2) {
        super.drawScene(g2);
        drawBackButton(g2);
        int[] imagePos = drawInstructionScreen(g2);

        g2.setColor(Color.BLACK);
        Font font = new Font("Monospaced", Font.BOLD, FONT_SIZE);
        g2.setFont(font);
        FontMetrics metrics = g2.getFontMetrics(font);

        drawFirstTextBox(g2, imagePos, metrics);
        drawSecondTextBox(g2, imagePos, metrics);
        drawThirdTextBox(g2, imagePos, metrics);
        drawFourthTextBox(g2, imagePos, metrics);
    }

    private void drawBackButton(Graphics2D g2) {
        Image backButtonImage = MenuImage.BACK.getImage();
        g2.drawImage(backButtonImage, BACK_BUTTON_X, BACK_BUTTON_Y, null);
    }

    private int[] drawInstructionScreen(Graphics2D g2) {
        Rectangle d = g2.getClipBounds();
        Image instructionScreen = MenuImage.INSTRUCTION_SCREEN.getImage();
        int imageXPos = (int) (d.getWidth() - instructionScreen.getWidth(null)) / 2;
        int imageYPos = (int) (d.getHeight() - instructionScreen.getHeight(null)) / 2;
        g2.drawImage(instructionScreen, imageXPos, imageYPos, null);
        return new int[] {imageXPos, imageYPos};
    }

    private void drawFirstTextBox(Graphics2D g2, int[] imagePos, FontMetrics metrics) {
        String str1a = "Use the Arrow";
        String str1b = "Keys to Move";
        String str1c = "and Fly!";

        int midX1 = imagePos[0] + ONE_THREE_X_POS;
        int startY1 = imagePos[1] + ONE_Y_POS;

        g2.drawString(str1a, midX1 - metrics.stringWidth(str1a) / 2, startY1);
        g2.drawString(str1b, midX1 - metrics.stringWidth(str1b) / 2, startY1 + LINE_HEIGHT);
        g2.drawString(str1c, midX1 - metrics.stringWidth(str1c) / 2, startY1 + LINE_HEIGHT * 2);
    }

    private void drawSecondTextBox(Graphics2D g2, int[] imagePos, FontMetrics metrics) {
        String str2a = "Collect all the Gold to";
        String str2b = "Advance to the Next Level.";

        int midX2 = imagePos[0] + TWO_X_POS;
        int startY2 = imagePos[1] + TWO_Y_POS;

        g2.drawString(str2a, midX2 - metrics.stringWidth(str2a) / 2, startY2);
        g2.drawString(str2b, midX2 - metrics.stringWidth(str2b) / 2, startY2 + LINE_HEIGHT);
    }

    private void drawThirdTextBox(Graphics2D g2, int[] imagePos, FontMetrics metrics) {
        String str3a = "Avoid the Spikes and";
        String str3b = "Enemies, Bounce on";
        String str3c = "their Heads!";

        int midX3 = imagePos[0] + ONE_THREE_X_POS;
        int startY3 = imagePos[1] + THREE_Y_POS;

        g2.drawString(str3a, midX3 - metrics.stringWidth(str3a) / 2, startY3);
        g2.drawString(str3b, midX3 - metrics.stringWidth(str3b) / 2, startY3 + LINE_HEIGHT);
        g2.drawString(str3c, midX3 - metrics.stringWidth(str3c) / 2, startY3 + LINE_HEIGHT * 2);
    }

    private void drawFourthTextBox(Graphics2D g2, int[] imagePos, FontMetrics metrics) {
        String str4a = "Use bounce pads";
        String str4b = "to fling yourself";
        String str4c = "across the dungeon!";

        int midX4 = imagePos[0] + FOUR_X_POS;
        int startY4 = imagePos[1] + FOUR_Y_POS;

        g2.drawString(str4a, midX4 - metrics.stringWidth(str4a) / 2, startY4);
        g2.drawString(str4b, midX4 - metrics.stringWidth(str4b) / 2, startY4 + LINE_HEIGHT);
        g2.drawString(str4c, midX4 - metrics.stringWidth(str4c) / 2, startY4 + LINE_HEIGHT * 2);
    }

}
