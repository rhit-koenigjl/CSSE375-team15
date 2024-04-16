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
		super(x, y, width, height, GameImage.COIN);
	}

	/**
	 * ensures: the correct drawing of the coin tile
	 */
	@Override
	void display(Graphics2D g) {
		drawImage(g, 0.75, 0.125, false);
	}

	/**
	 * ensures: that the coin is set for removal whenever a player touches it
	 */
	@Override
	void handleCollision(Actor actor, double xPos, double yPos) {
		if (actor.isHero()) {
			setRemove();
		}
	}

}
