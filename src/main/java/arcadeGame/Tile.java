package arcadeGame;

import java.awt.Graphics2D;

public abstract class Tile extends GameObject {
	private boolean remove = false;

	/**
	 * ensures the correct creation of a Tile
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Tile(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	protected void setRemove() {
		remove = true;
	}

	boolean shouldRemove() {
		return remove;
	}

	/**
	 * ensures: the Tile is displayed correctly
	 * 
	 * @param g2
	 */
	abstract void display(Graphics2D g2);

	/**
	 * ensures: how a default movable object should collide with this tile.
	 * 
	 * @param a: the Actor
	 * @param ix
	 * @param iy
	 */
	void handleCollision(Actor a, double ix, double iy) {
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
