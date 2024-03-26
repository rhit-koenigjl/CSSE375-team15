package arcadeGame;

import java.awt.Graphics2D;

public class LossUpdater extends SceneUpdater{

    private Level level;

    public LossUpdater(SceneManager sceneManager, Level level) {
        super(sceneManager);
        this.level = level;
    }

    @Override
    public void updateScene() {
        return;
    }

    @Override
    public void drawScene(Graphics2D g2, String shownString, int xMiddle, int yMiddle, int score) {
        shownString = "You ran out of lives on level " + level.getIndex() + ", Your score was: " + score;
		g2.drawString(shownString, xMiddle, yMiddle);
    }
    
}
