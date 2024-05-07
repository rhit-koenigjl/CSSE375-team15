package arcadeGame.gameHelpers.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Map;
import arcadeGame.gameHelpers.SceneManager;
import arcadeGame.gameHelpers.UpdateState;
import arcadeGame.levelManagers.Level;

public class GameUpdater extends SceneUpdater {
    private final Map<Integer, Boolean> keys;
    private final UpdateState state;

    public GameUpdater(SceneManager sm, Level level, Map<Integer, Boolean> k, UpdateState state) {
        super(sm);
        this.keys = k;
        this.level = level;
        this.state = state;
    }

    @Override
    public void updateScene() {
        this.level.update(keys, state, sceneManager);
    }

    @Override
    public void drawScene(Graphics2D g2) {
        super.drawScene(g2);
        Color previousColor = g2.getColor();
        g2.setFont(new Font("Monospaced", Font.BOLD, FONT_SIZE));

        level.draw(g2);
        g2.setColor(previousColor);
    }

    @Override
    public void onFirstLoad() {
        this.state.resizeLevel();
    }

    @Override
    public String getSceneName() {
        return "game";
    }

    @Override
    public boolean displayStats() {
        return true;
    }

}
