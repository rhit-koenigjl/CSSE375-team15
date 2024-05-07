package arcadeGame.gameComponents.spriteAnimations;

import arcadeGame.gameComponents.imageManagers.GameImage;

public class PlayerJumpSprite extends DisplaySprite {
    private static final int VELOCITY_DIVISOR = 17;
    private static final int WIDTH_DIVISOR = 30;
    private static final int X_DIVISOR = 60;
    private static final int Y_DIVISOR = 120;

    private final double origWidth;

    public PlayerJumpSprite(double x, double y, double width) {
        super(x, y, width, width / 2, GameImage.JUMP_WIND);
        vy = width / VELOCITY_DIVISOR;
        origWidth = width;
    }

    @Override
    public void updatePosition() {
        y += vy;
        if (width > 0) {
            width -= origWidth / WIDTH_DIVISOR;
            x += origWidth / X_DIVISOR;

            y += origWidth / Y_DIVISOR;
            height -= origWidth / X_DIVISOR;
        } else {
            width = 0;
        }

    }

}
