package arcadeGame.gameComponents.spriteAnimations;

import arcadeGame.gameComponents.imageManagers.GameImage;

public class CollectedCoinSprite extends DisplaySprite {
    private static final int COIN_FINAL_X = 10;
    private static final int COIN_FINAL_Y = 10;
    private static final int ONE_SECOND = 100;
    private static final int TIME_OFFSET = 8;
    private static final int TIME_DIVISOR = 20;

    private double animationTime;

    public CollectedCoinSprite(double x, double y, double width, double height) {
        super(x, y, width, height, GameImage.COIN);
        animationTime = 0;
    }

    @Override
    public void updatePosition() {
        x += (COIN_FINAL_X - x) / Math.max(1, ONE_SECOND - animationTime / 3);
        y += (COIN_FINAL_Y - y) / Math.max(1, ONE_SECOND - animationTime / 3);
        animationTime += TIME_OFFSET;
        this.y += Math.max(TIME_OFFSET - animationTime / TIME_DIVISOR, 0);
    }

}
