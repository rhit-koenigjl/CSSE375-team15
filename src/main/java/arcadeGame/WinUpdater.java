package arcadeGame;

import java.awt.Graphics2D;

public class WinUpdater extends SceneUpdater {

    public WinUpdater(SceneManager sceneManager) {
        super(sceneManager);
    }

    @Override
    public void updateScene() {
        return;
    }

    @Override
    public void drawScene(Graphics2D g2, String shownString, int xMiddle, int yMiddle, int score) {
        shownString = "Congrats! You Won! Your score was: " + score;
        g2.drawString(shownString, xMiddle, yMiddle);
    }

}
