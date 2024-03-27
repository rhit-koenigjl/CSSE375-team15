package arcadeGame;

import java.util.List;

public class EnemySpawnerGenerator extends EnemySpawner {

  public EnemySpawnerGenerator(double startX, double startY, double width, double height,
      double velocityX, double velocityY, List<Enemy> enemies, Player player) {
    super(startX, startY, width, height, velocityX, velocityY, enemies, player);
  }

  @Override
  public Enemy returnNew() {
    double angle = super.getAngle();
    return new EnemyGenerator(x, y, width, height, Math.cos(angle) * 4, Math.sin(angle) * 4,
        enemies, p);
  }

}
