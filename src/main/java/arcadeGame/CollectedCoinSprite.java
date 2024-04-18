package arcadeGame;

public class CollectedCoinSprite extends DisplaySprite{
    private double animationMetric;


    public CollectedCoinSprite(double x, double y, double width, double height) {
        super(x, y, width, height, GameImage.COIN);
        animationMetric = 0;
    }

    
    @Override
    public void updatePosition() {
        animationMetric += width / 100;
        this.x += Math.cos(animationMetric / 3) * animationMetric * 2;
        this.y += Math.sin(animationMetric / 3) * animationMetric * 2;
    }
    
}
