package arcadeGame.gameHelpers.screens;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import arcadeGame.gameComponents.imageManagers.MenuImage;
import arcadeGame.gameHelpers.SceneManager;
import arcadeGame.gameHelpers.transitions.TextGraphics;
import arcadeGame.stateComponents.MouseListener;

public class CreditsUpdater extends SceneUpdater {
    private static final String CREDITS_MESSAGE = """
            This game was originally created in W21 for the CSSE220 final project
             by JL Koenig and Tommy Welch. It has been modified by JL Koenig and
             Canon Maranda in S24 for the CSSE375 term project.
            """;
    private static final int BACK_BUTTON_X = 10;
    private static final int BACK_BUTTON_Y = 10;
    private static final int BACK_BUTTON_WIDTH = 50;
    private static final int BACK_BUTTON_HEIGHT = 75;

    private final SceneUpdater menuScene;
    private final MouseListener mouseListener;

    protected CreditsUpdater(SceneManager sceneManager, SceneUpdater menuScene,
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
    public void drawScene(Graphics2D g2) {
        super.drawScene(g2);
        Image backButtonImage = MenuImage.BACK.getImage();
        g2.drawImage(backButtonImage, BACK_BUTTON_X, BACK_BUTTON_Y, null);

        TextGraphics.drawMultilineText(CREDITS_MESSAGE, g2);
    }

    @Override
    public String getSceneName() {
        return "Credits";
    }

}
