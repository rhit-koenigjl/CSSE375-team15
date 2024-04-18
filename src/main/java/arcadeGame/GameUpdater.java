package arcadeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Map;

public class GameUpdater extends SceneUpdater {
    private Map<Integer, Boolean> keys;
    private UpdateState state;

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
    public void drawScene(Graphics2D g2, int score) {
        Color previousColor = g2.getColor();
        g2.setFont(new Font("Monospaced", Font.BOLD, 28));

        level.draw(g2, score);
        g2.setColor(previousColor);
    }

    public void changeLevel(Level level) {
        this.level = level;
    }

    String getSceneName() {
        return "game";
    }

}
