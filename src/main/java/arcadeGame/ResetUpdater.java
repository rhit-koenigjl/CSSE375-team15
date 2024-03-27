package arcadeGame;

import java.awt.Font;
import java.awt.Graphics2D;

public class ResetUpdater extends SceneUpdater{
    private int timer;
    private SceneUpdater gameUpdater;

    public ResetUpdater(SceneManager sceneManager, SceneUpdater gameUpdater) {
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
        g2.setFont(new Font("Monospaced", Font.BOLD, 28));
        shownString = "You got hit! Restarting the level in " + (350 - timer) / 100;
		g2.drawString(shownString, xMiddle, yMiddle);
    }
    
}
