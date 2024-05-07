package arcadeGame.gameComponents;

import java.awt.Graphics2D;
import arcadeGame.gameComponents.imageManagers.Direction;
import arcadeGame.gameComponents.imageManagers.GameImage;

public class Spike extends Tile {
    private static final int SIDE_SPLIT = 4;

    public Spike(int x, int y, int sideLength, Direction d) {
        super(d == Direction.LEFT ? x + sideLength / SIDE_SPLIT : x,
                d == Direction.UP ? y + sideLength / SIDE_SPLIT : y,
                d == Direction.RIGHT || d == Direction.LEFT ? sideLength * 3 / SIDE_SPLIT
                        : sideLength,
                d == Direction.DOWN || d == Direction.UP ? sideLength * 3 / SIDE_SPLIT : sideLength,
                GameImage.SPIKE);
        this.dir = d;
    }

    @Override
    public void display(Graphics2D g2) {
        drawImage(g2);
    }

    @Override
    void handleCollision(Actor a, double ix, double iy) {
        if (ix > 0) {
            if (this.dir == Direction.LEFT) {
                a.setSpikeCollision(true);
            }
            a.setX(this.x - a.getWidth());
            a.setVx(0);
        }

        if (iy > 0) {
            if (this.dir == Direction.UP) {
                a.setSpikeCollision(true);
            }
            a.setY(this.y - a.getHeight());
            a.setVy(0);
        }

        if (ix < 0) {
            if (this.dir == Direction.RIGHT) {
                a.setSpikeCollision(true);
            }
            a.setX(this.x + this.width);
            a.setVx(0);
        }

        if (iy < 0) {
            if (this.dir == Direction.DOWN) {
                a.setSpikeCollision(true);
            }
            a.setY(this.y + this.height);
            a.setVy(0);
        }
    }

}
