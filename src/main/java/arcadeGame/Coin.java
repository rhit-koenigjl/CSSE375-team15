package arcadeGame;

import java.awt.Graphics2D;

public class Coin extends Tile {
	private double floatManager;

	/**
	 * ensures: the correct initialization of the Coin class
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Coin(int x, int y, int width, int height) {
		super(x, y, width, height);
		floatManager = Math.random() * 360;
	}

	/**
	 * ensures: the correct drawing of the coin tile
	 */
	@Override
	public void display(Graphics2D g) {
		double originalY = y;
		y += Math.sin(floatManager) * 10 + 10;
		drawImage(g, "coin.gif");
		y = originalY;

		floatManager += 0.05;
		floatManager %= 360;
	}

	/**
	 * ensures: that the coin is set for removal whenever a player touches it
	 */
	@Override
	public void handleCollision(Actor a, double ix, double iy) {
		if (a.isHero()) {
			setRemove();
		}
	}

}
