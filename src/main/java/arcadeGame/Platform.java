package arcadeGame;

import java.awt.Graphics2D;

public class Platform extends Tile {

	/**
	 * ensures: the correct initialization of the platform instance
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Platform(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	/**
	 * ensures: the platform is correctly drawn
	 */
	public void display(Graphics2D g) {
		drawImage(g, "mossy_brick.png");
	}

}
