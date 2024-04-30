package arcadeGame.gameComponents;

import java.awt.Graphics2D;
import java.util.List;
import arcadeGame.gameComponents.imageManagers.Direction;
import arcadeGame.gameComponents.imageManagers.GameImage;

public abstract class EnemySpawner extends Enemy {
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
        drawImage(g2, 1.25, 0.125, false);
    }

    @Override
    public void update(List<Tile> tiles) {
        control();
        super.update(tiles);
        timeInc += 0.1;
        if (timeInc > Math.PI * 2) {
            timeInc = 0;
            this.adding = true;
        }
    }

    void control() {
        double goalSpeed = 0;
        vx += (goalSpeed - vx) / (APPROACH_FACTOR * 12);
        vy += (goalSpeed - vy) / (APPROACH_FACTOR * 12);
    }

    public abstract Enemy returnNew();

    protected double getAngle() {
        return Math.atan2(p.getY() - y, p.getX() - x);
    }

    protected boolean isNonTrackingEnemy() {
        return false;
    }

}
