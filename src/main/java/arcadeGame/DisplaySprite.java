package arcadeGame;
import java.awt.Rectangle;
import java.awt.Graphics2D;



public abstract class DisplaySprite extends GameObject{
    public DisplaySprite(double x, double y, double width, double height, GameImage gameImage) {
        super(x, y, width, height, gameImage);
    }
    
    public boolean canBeRemoved(Rectangle screenSize) {
        return x + width < 0 || y + height < 0 || x > screenSize.getWidth() || y > screenSize.getHeight();
    }

    public void display (Graphics2D g2) {
        drawImage(g2);
    }

    public abstract void updatePosition();
}
