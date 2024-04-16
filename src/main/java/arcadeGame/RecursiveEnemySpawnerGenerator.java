package arcadeGame;

import java.util.List;

public class RecursiveEnemySpawnerGenerator extends EnemySpawner {

  public RecursiveEnemySpawnerGenerator(double startX, double startY, double width, double height,
      List<Enemy> enemies, Player player) {
    this(startX, startY, width, height, 0, 0, enemies, player);
  }

  public RecursiveEnemySpawnerGenerator(double startX, double startY, double width, double height,
      double velocityX, double velocityY, List<Enemy> enemies, Player player) {
    super(startX, startY, width, height, velocityX, velocityY, enemies, player);
  }

  @Override
  public Enemy returnNew() {
    double angle = super.getAngle();
    return new RecursiveEnemySpawnerGenerator(x, y, width, height, Math.cos(angle) * 4,
        Math.sin(angle) * 4, enemies, p);
  }

}
