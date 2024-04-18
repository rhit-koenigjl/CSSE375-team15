package arcadeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Map;

public class GameUpdater extends SceneUpdater {
    private Map<Integer, Boolean> keys;
    private UpdateState state;

    GameUpdater(SceneManager sm, Level level, Map<Integer, Boolean> k, UpdateState state) {
        super(sm);
        this.keys = k;
        this.level = level;
        this.state = state;
    }

    @Override
    void updateScene() {
        this.level.update(keys, state, sceneManager);
    }

    @Override
    void drawScene(Graphics2D g2, int score) {
        Color previousColor = g2.getColor();
        g2.setFont(new Font("Monospaced", Font.BOLD, FONT_SIZE));

        level.draw(g2, score);
        g2.setColor(previousColor);
    }

    void changeLevel(Level level) {
        this.level = level;
    }

    String getSceneName() {
        return "game";
    }

}
