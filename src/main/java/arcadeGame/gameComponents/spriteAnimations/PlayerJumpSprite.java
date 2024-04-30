package arcadeGame.gameComponents.spriteAnimations;

import arcadeGame.gameComponents.imageManagers.GameImage;

public class PlayerJumpSprite extends DisplaySprite {
    private double origWidth;

    public PlayerJumpSprite(double x, double y, double width) {
        super(x, y, width, width / 2, GameImage.JUMP_WIND);
        vy = width / 17;
        origWidth = width;
    }

    @Override
    public void updatePosition() {
        y += vy;
        if (width > 0) {
            width -= origWidth / 30;
            x += origWidth / 60;

            y += origWidth / 120;
            height -= origWidth / 60;
        } else {
            width = 0;
        }

    }

}
