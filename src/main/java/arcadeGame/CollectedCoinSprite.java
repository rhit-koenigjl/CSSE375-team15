package arcadeGame;

public class CollectedCoinSprite extends DisplaySprite {
    private static int COIN_FINAL_X = 10;
    private static int COIN_FINAL_Y = 10;
    private double animationTime;

    CollectedCoinSprite(double x, double y, double width, double height) {
        super(x, y, width, height, GameImage.COIN);
        animationTime = 0;
    }

    @Override
    protected void updatePosition() {
        x += (COIN_FINAL_X - x) / Math.max(1, 100 - animationTime / 3);
        y += (COIN_FINAL_Y - y) / Math.max(1, 100 - animationTime / 3);
        animationTime += 8;
        this.y += Math.max(8 - animationTime/20 , 0);
    }

}
