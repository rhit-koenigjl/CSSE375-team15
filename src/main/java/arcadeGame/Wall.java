package arcadeGame;

import java.awt.Color;
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
		g.translate(getX(), getY());
		g.setColor(Color.gray);
		g.fillRect(0, 0, (int) width, (int) height);
		g.translate(-getX(), -getY());
	}

	@Override
	public void handleCollision(Actor a, double ix, double iy) {
		if (ix > 0) {
			a.setX(x - a.getWidth());
			a.setVx(a.isNonTrackingEnemy() ? a.getVx() * -1 : 0);
		}
		if (ix < 0) {
			a.setX(x + width);
			a.setVx(a.isNonTrackingEnemy() ? a.getVx() * -1 : 0);
		}
		if (iy > 0) {
			a.setY(y - a.getHeight());
			a.setVy(a.isNonTrackingEnemy() ? a.getVy() * -1 : 0);
		}
		if (iy < 0) {
			a.setY(y + height);
			a.setVy(a.isNonTrackingEnemy() ? a.getVy() * -1 : 0);
		}
	}
}
