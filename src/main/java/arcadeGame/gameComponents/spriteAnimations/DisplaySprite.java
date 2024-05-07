package arcadeGame.gameComponents.spriteAnimations;

import java.awt.Graphics2D;

import arcadeGame.gameComponents.GameObject;
import arcadeGame.gameComponents.imageManagers.GameImage;

public abstract class DisplaySprite extends GameObject {

    DisplaySprite(double x, double y, double width, double height, GameImage gameImage) {
        super(x, y, width, height, gameImage);
    }

    public void display(Graphics2D g2) {
        drawImage(g2);
    }

    public abstract void updatePosition();

}
