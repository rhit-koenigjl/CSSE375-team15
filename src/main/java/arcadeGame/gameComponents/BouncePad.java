package arcadeGame.gameComponents;

import java.awt.Graphics2D;

import arcadeGame.gameComponents.imageManagers.Direction;
import arcadeGame.gameComponents.imageManagers.GameImage;

public class BouncePad extends Tile {
    private static double BOUNCE_VALUE = 2f/5f;

    public BouncePad(int x, int y, int width, int height, Direction dir) {
        super(x, (int) (y + 3.0 / 5.0 * height), width, height, GameImage.BOUNCE_PAD);
        this.dir = dir;
    }

    @Override
    public void display(Graphics2D g2) {
        this.y -= height * 0.5;
        drawImage(g2);
        this.y += height * 0.5;
    }

    @Override
    void handleCollision(Actor actor, double xPos, double yPos) {
        if (xPos > 0) {
            actor.setX(x - actor.getWidth());
            actor.setVx(dir == Direction.LEFT ? -width * BOUNCE_VALUE : 0);
        }
        if (xPos < 0) {
            actor.setX(x + width);
            actor.setVx(dir == Direction.RIGHT ? width * BOUNCE_VALUE : 0);
        }
        if (yPos > 0) {
            actor.setY(y - actor.getHeight());
            actor.setVy(dir == Direction.UP ? -width * BOUNCE_VALUE : 0);
        }
        if (yPos < 0) {
            actor.setY(y + height);
            actor.setVy(dir == Direction.DOWN ? width * BOUNCE_VALUE : 0);
        }
    }

}
