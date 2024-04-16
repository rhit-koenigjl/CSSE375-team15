package arcadeGame;

import java.awt.Graphics2D;

public class Spike extends Tile {

	/**
	 * ensures: calls the Tile constructor
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Spike(int x, int y, int sideLength) {
		super(x, y, sideLength, sideLength, GameImage.SPIKE);
		this.dir = Direction.UP;
	}

	@Override
	public void display(Graphics2D g2) {
		drawImage(g2);
	}

	@Override
	public void handleCollision(Actor a, double ix, double iy) {
		super.handleCollision(a, ix, iy);
		a.setSpikeCollision(true);
	}

}
