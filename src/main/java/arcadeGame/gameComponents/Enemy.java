package arcadeGame.gameComponents;

import java.awt.Graphics2D;
import arcadeGame.gameComponents.imageManagers.Direction;
import arcadeGame.gameComponents.imageManagers.GameImage;

public class Enemy extends Actor {
    private static final double FLOAT_ERROR = 0.0001;

    protected boolean adding = false;

    public Enemy(double startX, double startY, double width, double height) {
        this(startX, startY, width, height, GameImage.GHOST);
    }

    Enemy(double startX, double startY, double width, double height, double velocityX,
            double velocityY) {
        this(startX, startY, width, height, velocityX, velocityY, GameImage.GHOST);
    }

    Enemy(double startX, double startY, double width, double height, double velocityX,
            double velocityY, GameImage gameImage) {
        super(startX, startY, width, height, gameImage);
        this.dir = Direction.RIGHT;
        vx = velocityX;
        vy = velocityY;
    }

    public Enemy(double startX, double startY, double width, double height, Direction dir) {
        super(startX, startY, width, height, GameImage.GHOST);
        double ang = Direction.toAngle(dir) + Math.PI / 2;
        this.vx = Math.cos(ang) * width * DEFAULT_SPEED;
        this.vy = Math.sin(ang) * height * DEFAULT_SPEED;
    }

    Enemy(double startX, double startY, double width, double height, GameImage gameImage) {
        this(startX, startY, width, height, Math.random() * SPEED, Math.random() * SPEED,
                gameImage);
    }

    @Override
    public void drawActor(Graphics2D g2) {
        if (vx != 0 || vy != 0) {
            String horizontalDirection = closeToZero(vx) ? "" : (vx > 0) ? "R" : "L";
            String verticalDirection = closeToZero(vy) ? "" : (vy > 0) ? "D" : "U";
            String direction = String.format("%s%s", closeToZero(vx) ? verticalDirection : "",
                    closeToZero(vy) ? horizontalDirection : "");
            this.dir = direction.equals("") ? this.dir : Direction.fromString(direction);
        }
        drawImage(g2);
    }

    private boolean closeToZero(double num) {
        return Math.abs(num) < FLOAT_ERROR;
    }

    public boolean getAdding() {
        return adding;
    }

    public void setAdding(boolean newAdding) {
        adding = newAdding;
    }

    public Enemy returnNew() {
        return null;
    }

    @Override
    public boolean isNonTrackingEnemy() {
        return true;
    }

    GameImage getImage() {
        return this.gameImage;
    }

    Direction getDir() {
        return this.dir;
    }

}
