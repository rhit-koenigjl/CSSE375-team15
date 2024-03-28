package arcadeGame;

import java.awt.Color;
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
		g.translate(getX(), getY());
		g.setColor(Color.gray);
		g.fillRect(0, 0, (int) width, (int) height);
		g.translate(-getX(), -getY());
	}

}
