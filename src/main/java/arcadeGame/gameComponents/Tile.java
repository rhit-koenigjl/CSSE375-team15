package arcadeGame.gameComponents;

import java.awt.Graphics2D;
import arcadeGame.gameComponents.imageManagers.GameImage;

public abstract class Tile extends GameObject {
    private boolean remove = false;

    Tile(int x, int y, int width, int height, GameImage gameImage) {
        super(x, y, width, height, gameImage);
    }

    protected void setRemove() {
        remove = true;
    }

    public boolean shouldRemove() {
        return remove;
    }

    public abstract void display(Graphics2D g2);

    void handleCollision(Actor a, double ix, double iy) {
        if (ix > 0) {
            a.setX(x - a.getWidth());
            a.setVx(a.isNonTrackingEnemy() ? a.getVx() * -1 : 0);
        }
        if (ix < 0) {
            a.setX(x + width);
            a.setVx(a.isNonTrackingEnemy() ? a.getVx() * -1 : 0);
        }
        if (iy > 0) {
            a.setY(y - a.getHeight());
            a.setVy(a.isNonTrackingEnemy() ? a.getVy() * -1 : 0);
        }
        if (iy < 0) {
            a.setY(y + height);
            a.setVy(a.isNonTrackingEnemy() ? a.getVy() * -1 : 0);
        }
    }

}
