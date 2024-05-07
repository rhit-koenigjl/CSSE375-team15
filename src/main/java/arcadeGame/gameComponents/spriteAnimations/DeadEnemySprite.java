package arcadeGame.gameComponents.spriteAnimations;

import arcadeGame.gameComponents.imageManagers.GameImage;

public class DeadEnemySprite extends DisplaySprite {
    private static final double Y_VELOCITY = 0.2;

    public DeadEnemySprite(double x, double y, double width, double height, double vx, double vy) {
        super(x, y, width, height, GameImage.DEAD_GHOST);
        this.vx = vx;
        this.vy = vy;
    }

    @Override
    public void updatePosition() {
        this.vy += Y_VELOCITY;
        this.x += this.vx;
        this.y += this.vy;
    }

}
