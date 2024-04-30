package arcadeGame.gameComponents.spriteAnimations;

import arcadeGame.gameComponents.imageManagers.GameImage;

public class DeadEnemySprite extends DisplaySprite {

    public DeadEnemySprite(double x, double y, double width, double height, double vx, double vy) {
        super(x, y, width, height, GameImage.DEAD_GHOST);
        this.vx = vx;
        this.vy = vy;
    }

    @Override
    public void updatePosition() {
        this.vy += 0.2;
        this.x += this.vx;
        this.y += this.vy;
    }

}
