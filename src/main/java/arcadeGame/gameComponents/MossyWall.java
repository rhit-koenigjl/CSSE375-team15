package arcadeGame.gameComponents;

import java.awt.Graphics2D;
import arcadeGame.gameComponents.imageManagers.GameImage;

public class MossyWall extends Wall {

    public MossyWall(int x, int y, int width, int height) {
        super(x, y, width, height, GameImage.MOSSY_BRICK);
    }

    public void display(Graphics2D g) {
        drawImage(g);
    }

}
