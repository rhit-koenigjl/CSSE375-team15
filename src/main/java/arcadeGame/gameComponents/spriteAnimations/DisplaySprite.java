package arcadeGame.gameComponents.spriteAnimations;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import arcadeGame.gameComponents.GameObject;
import arcadeGame.gameComponents.imageManagers.GameImage;

public abstract class DisplaySprite extends GameObject {

    DisplaySprite(double x, double y, double width, double height, GameImage gameImage) {
        super(x, y, width, height, gameImage);
    }

    boolean canBeRemoved(Rectangle screenSize) {
        return x + width < 0 || y + height < 0 || x > screenSize.getWidth()
                || y > screenSize.getHeight();
    }

    public void display(Graphics2D g2) {
        drawImage(g2);
    }

    public abstract void updatePosition();

}
