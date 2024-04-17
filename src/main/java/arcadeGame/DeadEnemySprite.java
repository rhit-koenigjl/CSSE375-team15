package arcadeGame;

public class DeadEnemySprite extends DisplaySprite{

    public DeadEnemySprite(double x, double y, double width, double height, double vx, double vy, Direction dir, GameImage gameImage) {
        super(x, y, width, height, gameImage);
        this.vx = vx;
        this.vy = vy;
        this.dir = dir;
        this.gameImage = gameImage;
    }

    @Override
    public void updatePosition() {
        this.vy += 0.2;
        this.x += this.vx;
        this.y += this.vy;
    }
    
}
