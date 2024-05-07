package arcadeGame.gameComponents;

import java.awt.Graphics2D;

import arcadeGame.gameComponents.imageManagers.Direction;
import arcadeGame.gameComponents.imageManagers.GameImage;

public class BouncePad extends Tile {
    private static double VERTICAL_BOUNCE_VALUE = 2f / 5f;
    private static double HORIZONTAL_BOUNCE_VALUE = 7f / 10f;

    public BouncePad(int x, int y, int width, int height, Direction dir) {
        super(dir == Direction.LEFT ? x + width / 2 : x,
                dir == Direction.UP || dir == Direction.NONE ? y + height / 2 : y,
                dir == Direction.LEFT || dir == Direction.RIGHT ? width / 2 : width,
                dir == Direction.UP || dir == Direction.DOWN || dir == Direction.NONE ? height / 2
                        : height,
                GameImage.BOUNCE_PAD);
        this.dir = dir;
        if (dir == Direction.NONE) {
            this.dir = Direction.UP;
        }
    }

    @Override
    public void display(Graphics2D g2) {
        if (dir == Direction.UP || dir == Direction.NONE) {
            y -= height;
            height *= 2;
        }
        if (dir == Direction.LEFT) {
            x -= this.width;
            width *= 2;
        }
        if (dir == Direction.RIGHT) {
            width *= 2;
        }
        if (dir == Direction.DOWN) {
            height *= 2;
        }
        drawImage(g2);
        if (dir == Direction.UP || dir == Direction.NONE) {
            height /= 2;
            y += height;
        }
        if (dir == Direction.LEFT) {
            width /= 2;
            x += this.width;
        }
        if (dir == Direction.RIGHT) {
            width /= 2;
        }
        if (dir == Direction.DOWN) {
            height /= 2;
        }
    }

    @Override
    void handleCollision(Actor actor, double xPos, double yPos) {
        if (xPos > 0) {
            actor.setX(x - actor.getWidth());
            actor.setVx(dir == Direction.LEFT ? -width * HORIZONTAL_BOUNCE_VALUE : 0);
        }
        if (xPos < 0) {
            actor.setX(x + width);
            actor.setVx(dir == Direction.RIGHT ? width * HORIZONTAL_BOUNCE_VALUE : 0);
        }
        if (yPos > 0) {
            actor.setY(y - actor.getHeight());
            actor.setVy(dir == Direction.UP ? -width * VERTICAL_BOUNCE_VALUE : 0);
        }
        if (yPos < 0) {
            actor.setY(y + height);
            actor.setVy(dir == Direction.DOWN ? width * VERTICAL_BOUNCE_VALUE : 0);
        }
    }

}
