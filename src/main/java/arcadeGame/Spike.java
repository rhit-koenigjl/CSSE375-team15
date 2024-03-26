package arcadeGame;

import java.awt.Color;
import java.awt.Graphics2D;

public class Spike extends Tile {
	/**
	 * ensures: calls the Tile constructor for the bouncepad
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Spike(int x, int y, int sideLength) {
		super(x, y, sideLength, sideLength);
	}

	@Override
	public void display(Graphics2D g2) {
		g2.translate(x, y);
		g2.setColor(Color.gray);
		g2.fillRect(0, 0, (int) width, (int) height);
		g2.translate(width / 2, -(height * Math.sqrt(2) / 2 - height / 2));
		g2.rotate(Math.PI / 4);
		g2.fillRect(0, 0, (int) width, (int) height);
		g2.rotate(-Math.PI / 4);
		g2.translate(-width / 2, (height * Math.sqrt(2) / 2 - height / 2));
		g2.translate(-x, -y);
	}

	@Override
	public boolean handleCollision(Actor a, double ix, double iy) {
		if (super.handleCollision(a, ix, iy)) {
			a.didCollideWithSpikes = true;
			return true;
		}
		return false;
	}
}
