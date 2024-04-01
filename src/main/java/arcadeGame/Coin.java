package arcadeGame;

import java.awt.Graphics2D;

public class Coin extends Tile {

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
	}

	/**
	 * ensures: the correct drawing of the coin tile
	 */
	@Override
	public void display(Graphics2D g) {
		performImageOffset(0.75, 0.125, false);
		drawImage(g, "coin.gif");
		resetImageOffset(0.75, 0.125, true);
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
