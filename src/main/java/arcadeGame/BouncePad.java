package arcadeGame;

import java.awt.Graphics2D;

public class BouncePad extends Tile {

	/**
	 * ensures: calls the Tile constructor for the bouncepad
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public BouncePad(int x, int y, int width, int height) {
		super(x, (int) (y + 3.0/5.0 * height), width, height);
	}

	@Override
	protected void display(Graphics2D g2) {
		this.y -= height * 0.5;
		drawImage(g2, "bounce_pad.gif");
		this.y += height * 0.5;
	}

	@Override
	public void handleCollision(Actor a, double ix, double iy) {
		if (ix > 0) {
			a.setX(x - a.getWidth());
			a.setVx(0);
		}
		if (ix < 0) {
			a.setX(x + width);
			a.setVx(0);
		}
		if (iy > 0) {
			a.setY(y - a.getHeight());
			a.setVy(-30);
		}
		if (iy < 0) {
			a.setY(y + height);
			a.setVy(0);
		}
	}

}
