package arcadeGame.gameComponents;

import java.awt.Graphics2D;
import java.util.List;
import arcadeGame.gameComponents.imageManagers.GameImage;

abstract class Actor extends GameObject {
    protected static final double APPROACH_FACTOR = 4;
    protected static final double SPEED = 5;
    protected static final double DEFAULT_SPEED = 1.0 / 10.0;

    private boolean didCollideWithSpikes = false;

    Actor(double startX, double startY, double width, double height, GameImage gameImage) {
        super(startX, startY, width, height, gameImage);
    }

    abstract void drawActor(Graphics2D g2);

    private void handleTileCollisions(List<Tile> tiles, double xVel, double yVel) {
        for (Tile tile : tiles) {
            if (collidesWith(tile))
                tile.handleCollision(this, xVel, yVel);
        }
    }

    public void update(List<Tile> tiles) {
        x += vx * (isHero() ? 0.75 : 1);
        handleTileCollisions(tiles, vx, 0);
        y += vy;
        handleTileCollisions(tiles, 0, vy);
    }

    protected boolean isHero() {
        return false;
    }

    protected boolean isNonTrackingEnemy() {
        return false;
    }

    protected void setSpikeCollision(boolean didCollideWithSpikes) {
        this.didCollideWithSpikes = didCollideWithSpikes;
    }

    public boolean getSpikeCollision() {
        return didCollideWithSpikes;
    }

}
