package arcadeGame.gameComponents;

import arcadeGame.gameComponents.imageManagers.GameImage;

public class MossyWall extends Wall {

    public MossyWall(int x, int y, int width, int height) {
        super(x, y, width, height, GameImage.MOSSY_BRICK);
    }

}
