package arcadeGame.gameComponents;

import java.util.ArrayList;
import java.util.List;
import arcadeGame.gameComponents.imageManagers.GameImage;

public class HunterSeeker extends Enemy {
    private Player hero;

    public HunterSeeker(double startX, double startY, double width, double height, Player h) {
        this(startX, startY, width, height, 0, 0, h);
    }

    HunterSeeker(double startX, double startY, double width, double height, double velocityX,
            double velocityY, Player h) {
        super(startX, startY, width, height, velocityX, velocityY, GameImage.TRACKER);
        this.x = startX;
        this.y = startY;
        this.width = width;
        this.height = height;
        this.vx = velocityX;
        this.vy = velocityY;
        hero = h;
        this.gameImage = GameImage.TRACKER;
    }

    void handleCollisions(ArrayList<Tile> tiles, double ix, double iy) {
        for (Tile t : tiles) {
            if (collidesWith(t))
                t.handleCollision(this, ix, iy);
        }
    }

    @Override
    public void update(List<Tile> tiles) {
        control();
        super.update(tiles);
    }

    void control() {
        double goalSpeed = 0;
        double goalLift = 0;
        if (x + width < hero.getX()) {
            goalSpeed = width * DEFAULT_SPEED / 2;
        }
        if (x > hero.getX() + hero.getWidth()) {
            goalSpeed = -width * DEFAULT_SPEED / 2;
        }

        if (y > hero.getY() + hero.getHeight() / 2) {
            goalLift = -width * DEFAULT_SPEED / 2;
        } else {
            goalLift = width * DEFAULT_SPEED / 2;
        }

        vx += (goalSpeed - vx) / APPROACH_FACTOR;
        vy += (goalLift - vy) / APPROACH_FACTOR;
    }

    @Override
    public boolean isNonTrackingEnemy() {
        return false;
    }

}
