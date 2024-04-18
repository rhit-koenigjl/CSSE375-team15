package arcadeGame;

import java.awt.Rectangle;
import java.awt.Graphics2D;

public abstract class DisplaySprite extends GameObject {
    DisplaySprite(double x, double y, double width, double height, GameImage gameImage) {
        super(x, y, width, height, gameImage);
    }

    boolean canBeRemoved(Rectangle screenSize) {
        return x + width < 0 || y + height < 0 || x > screenSize.getWidth()
                || y > screenSize.getHeight();
    }

    void display(Graphics2D g2) {
        drawImage(g2);
    }

    protected abstract void updatePosition();
}
