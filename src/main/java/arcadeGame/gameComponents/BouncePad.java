package arcadeGame.gameComponents;

import java.awt.Graphics2D;
import arcadeGame.gameComponents.imageManagers.GameImage;

public class BouncePad extends Tile {

    public BouncePad(int x, int y, int width, int height) {
        super(x, (int) (y + 3.0 / 5.0 * height), width, height, GameImage.BOUNCE_PAD);
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
            actor.setVx(0);
        }
        if (xPos < 0) {
            actor.setX(x + width);
            actor.setVx(0);
        }
        if (yPos > 0) {
            actor.setY(y - actor.getHeight());
            actor.setVy(-30);
        }
        if (yPos < 0) {
            actor.setY(y + height);
            actor.setVy(0);
        }
    }

}
