package arcadeGame.gameHelpers.screens;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Map;
import javax.swing.JOptionPane;
import arcadeGame.GameComponent;
import arcadeGame.gameComponents.imageManagers.MenuImage;
import arcadeGame.gameHelpers.SceneManager;
import arcadeGame.stateComponents.MouseListener;

public class MenuUpdater extends SceneUpdater {
    private static final int MENU_Y_OFFSET = 20;
    private static final int BUTTON_Y_OFFSET = 320;
    private static final int BUTTON_X_OFFSET = 100;

    private final GameUpdater gameUpdater;
    private final InstructionsUpdater instructionsUpdater;
    private final CreditsUpdater creditsUpdater;
    private final MouseListener mouseListener;
    private final Map<Integer, Boolean> keys;
    private final GameComponent game;

    public MenuUpdater(SceneManager sceneManager, GameUpdater gameUpdater,
            MouseListener mouseListener, Map<Integer, Boolean> keys, GameComponent game) {
        super(sceneManager);
        this.gameUpdater = gameUpdater;
        this.mouseListener = mouseListener;
        mouseListener.setSceneManager(sceneManager);
        this.instructionsUpdater = new InstructionsUpdater(sceneManager, this, mouseListener);
        this.creditsUpdater = new CreditsUpdater(sceneManager, this, mouseListener);
        this.keys = keys;
        this.game = game;
    }

    @Override
    public void updateScene() {
        if (keys.getOrDefault(KeyEvent.VK_0, false)) {
            displayCustomEndpointPrompt();
            keys.put(KeyEvent.VK_0, false);
        }
    }

    private void displayCustomEndpointPrompt() {
        String url = JOptionPane
                .showInputDialog("Provide a custom endpoint URL or leave blank for default");
        if (url.isEmpty()) {
            game.setDefaultAiEndpoint();
        } else {
            game.setCustomAiEndpoint(url);
        }
    }

    @Override
    public void drawScene(Graphics2D g2) {
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

        mouseListener.addClickAction(new Rectangle(xPosCreditsButton, BUTTON_Y_OFFSET + yPosOffset,
                credits.getWidth(null), credits.getHeight(null)), creditsUpdater);
    }

    @Override
    public String getSceneName() {
        return "menu";
    }

}
