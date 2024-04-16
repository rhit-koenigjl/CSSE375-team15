package arcadeGame;

import java.util.List;

public class EnemyGenerator extends EnemySpawner {

  public EnemyGenerator(double startX, double startY, double width, double height,
      List<Enemy> enemies, Player player) {
    this(startX, startY, width, height, 0, 0, enemies, player);
  }

  public EnemyGenerator(double startX, double startY, double width, double height, double velocityX,
      double velocityY, List<Enemy> enemies, Player player) {
    super(startX, startY, width, height, velocityX, velocityY, enemies, player);
  }

  @Override
  public Enemy returnNew() {
    double angle = super.getAngle();
    return new Enemy(x, y, width, height, Math.cos(angle) * 4, Math.sin(angle) * 4);
  }

}
