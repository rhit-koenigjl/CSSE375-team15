package arcadeGame.gameComponents;

import java.awt.Graphics2D;
import java.util.List;
import arcadeGame.gameComponents.imageManagers.Direction;
import arcadeGame.gameComponents.imageManagers.GameImage;

public abstract class EnemySpawner extends Enemy {
    protected static final double VELOCITY_MULTIPLIER = 4;
    private static final double IMAGE_SCALE = 1.25;
    private static final double IMAGE_OFFSET = 0.125;
    private static final double TIME_INCREMENT = 0.1;
    private static final int THIS_VELOCITY_MULTIPLIER = 12;

    protected List<Enemy> enemies;
    protected Player p;
    private double timeInc = 0;

    EnemySpawner(double startX, double startY, double width, double height, double velocityX,
            double velocityY, List<Enemy> enemies, Player player) {
        super(startX, startY, width, height, GameImage.SPAWNER);
        this.enemies = enemies;
        this.p = player;
        this.vx = velocityX;
        this.vy = velocityY;
        this.dir = Direction.NONE;
    }

    @Override
    public void drawActor(Graphics2D g2) {
        drawImage(g2, IMAGE_SCALE, IMAGE_OFFSET, false);
    }

    @Override
    public void update(List<Tile> tiles) {
        control();
        super.update(tiles);
        timeInc += TIME_INCREMENT;
        if (timeInc > Math.PI * 2) {
            timeInc = 0;
            this.adding = true;
        }
    }

    void control() {
        double goalSpeed = 0;
        vx += (goalSpeed - vx) / (APPROACH_FACTOR * THIS_VELOCITY_MULTIPLIER);
        vy += (goalSpeed - vy) / (APPROACH_FACTOR * THIS_VELOCITY_MULTIPLIER);
    }

    public abstract Enemy returnNew();

    protected double getAngle() {
        return Math.atan2(p.getY() - y, p.getX() - x);
    }

    public boolean isNonTrackingEnemy() {
        return false;
    }

}
