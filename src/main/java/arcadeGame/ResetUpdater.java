package arcadeGame;

import java.awt.Graphics2D;

public class ResetUpdater extends SceneUpdater{
    private int timer;
    private GameUpdater gameUpdater;

    public ResetUpdater(SceneManager sceneManager, GameUpdater gameUpdater) {
        super(sceneManager);
        this.timer = 0;
        this.gameUpdater = gameUpdater;
    }

    @Override
    public void updateScene() {
        this.timer ++;
        if (timer > 350) {
            sceneManager.switchScene(gameUpdater);
        }
    }

    @Override
    public void drawScene(Graphics2D g2, String shownString, int xMiddle, int yMiddle, int score) {
        shownString = "You got hit! Restarting the level in " + (350 - timer) / 100;
		g2.drawString(shownString, xMiddle, yMiddle);
    }
    
}
