package arcadeGame;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class MenuUpdater extends SceneUpdater {
    private static final int MENU_Y_OFFSET = 20;
    private static final int BUTTON_Y_OFFSET = 320;
    private static final int BUTTON_X_OFFSET = 100;

    private GameUpdater gameUpdater;
    private InstructionsUpdater instructionsUpdater;
    private MouseListener mouseListener;


    MenuUpdater(SceneManager sceneManager, GameUpdater gameUpdater, MouseListener mouseListener) {
        super(sceneManager);
        this.gameUpdater = gameUpdater;
        this.mouseListener = mouseListener;
        mouseListener.setSceneManager(sceneManager);
        this.instructionsUpdater = new InstructionsUpdater(sceneManager, this, mouseListener);
    }

    @Override
    void updateScene() {}

    @Override
    void drawScene(Graphics2D g2) {
        super.drawScene(g2);
        Dimension screenSize = g2.getClipBounds().getSize();
        Image logo = MenuImage.LOGO.getImage();
        Image play = MenuImage.PLAY.getImage();
        Image help = MenuImage.HELP.getImage();
        Image credits = MenuImage.CREDITS.getImage();

        int xPosLogo = ((int) screenSize.getWidth() - logo.getWidth(null)) / 2;
        int xPosPlayButton = ((int) screenSize.getWidth() - play.getWidth(null)) / 2;
        int xPosHelpButton = xPosPlayButton - help.getWidth(null) - BUTTON_X_OFFSET;
        int xPosCreditsButton = xPosPlayButton + play.getWidth(null) + BUTTON_X_OFFSET;
        int yPosOffset = (play.getHeight(null) - help.getHeight(null)) / 2;

        g2.drawImage(logo, xPosLogo, MENU_Y_OFFSET, null);
        g2.drawImage(play, xPosPlayButton, BUTTON_Y_OFFSET, null);
        g2.drawImage(help, xPosHelpButton, BUTTON_Y_OFFSET + yPosOffset, null);
        g2.drawImage(credits, xPosCreditsButton, BUTTON_Y_OFFSET + yPosOffset, null);

        mouseListener.addClickAction(new Rectangle(xPosPlayButton, BUTTON_Y_OFFSET,
                play.getWidth(null), play.getHeight(null)), gameUpdater);

        mouseListener.addClickAction(new Rectangle(xPosHelpButton, BUTTON_Y_OFFSET + yPosOffset,
                help.getWidth(null), help.getHeight(null)), instructionsUpdater);
    }

    String getSceneName() {
        return "menu";
    }

}
