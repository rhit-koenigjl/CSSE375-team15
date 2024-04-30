package arcadeGame.gameComponents;

import java.awt.Graphics2D;
import arcadeGame.gameComponents.imageManagers.GameImage;

public class Wall extends Tile {

    public Wall(int x, int y, int width, int height) {
        this(x, y, width, height, GameImage.BRICK);
    }

    Wall(int x, int y, int width, int height, GameImage gameImage) {
        super(x, y, width, height, gameImage);
    }

    @Override
    public void display(Graphics2D g) {
        drawImage(g);
    }

}
