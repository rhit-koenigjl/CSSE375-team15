package arcadeGame;

import java.awt.Graphics2D;

public class Wall extends Tile {

	/**
	 * ensures: the correct initialization of a Wall
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Wall(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	/**
	 * ensures: the drawing of the Wall
	 */
	public void display(Graphics2D g) {
		drawImage(g, "brick.png");
	}

}
