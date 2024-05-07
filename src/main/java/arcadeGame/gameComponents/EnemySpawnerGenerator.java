package arcadeGame.gameComponents;

import java.util.List;

public class EnemySpawnerGenerator extends EnemySpawner {

    EnemySpawnerGenerator(double startX, double startY, double width, double height,
            List<Enemy> enemies, Player player) {
        this(startX, startY, width, height, 0, 0, enemies, player);
    }

    public EnemySpawnerGenerator(double startX, double startY, double width, double height,
            double velocityX, double velocityY, List<Enemy> enemies, Player player) {
        super(startX, startY, width, height, velocityX, velocityY, enemies, player);
    }

    @Override
    public Enemy returnNew() {
        double angle = super.getAngle();
        return new EnemyGenerator(x, y, width, height, Math.cos(angle) * VELOCITY_MULTIPLIER,
                Math.sin(angle) * VELOCITY_MULTIPLIER, enemies, p);
    }

}
