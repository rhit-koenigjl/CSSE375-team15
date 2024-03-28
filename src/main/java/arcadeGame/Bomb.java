package arcadeGame;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bomb extends Tile {
	private double floatManager;

	/**
	 * ensures: the correct initialization of the Bomb class
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Bomb(int x, int y, int width, int height) {
		super(x, y, width, height);
		floatManager = Math.random() * 360;
	}

	/**
	 * ensures: the correct drawing of the bomb tile
	 */
	@Override
	public void display(Graphics2D g) {
		double currentY = y + Math.sin(floatManager) * 10 + 10;

		g.setColor(Color.black);
		g.translate(x + width / 2, currentY);
		g.rotate(0.5);
		g.fillOval(0, 0, (int) width, (int) width);
		g.fillRect((int) width / 2 - 5, -7, 10, 20);
		g.rotate(-0.5);
		g.translate(-(x + width / 2), -currentY);

		floatManager += 0.05;
		floatManager %= 360;
	}

	/**
	 * ensures: that the bomb is set for removal whenever a player touches it
	 */
	@Override
	public void handleCollision(Actor a, double ix, double iy) {
		if (a.isHero()) {
			setRemove();
		}
	}

}
