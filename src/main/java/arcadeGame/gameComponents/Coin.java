package arcadeGame.gameComponents;

import java.awt.Graphics2D;
import arcadeGame.gameComponents.imageManagers.GameImage;

public class Coin extends Tile {

    public Coin(int x, int y, int width, int height) {
        super(x, y, width, height, GameImage.COIN);
    }

    @Override
    public void display(Graphics2D g) {
        drawImage(g);
    }

    @Override
    void handleCollision(Actor actor, double xPos, double yPos) {
        if (actor.isHero()) {
            setRemove();
        }
    }

}
