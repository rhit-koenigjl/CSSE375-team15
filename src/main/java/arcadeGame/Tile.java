package arcadeGame;

import java.awt.Graphics2D;

public abstract class Tile extends GameObject {
	protected boolean remove = false;

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

	public void setRemove() {
		remove = true;
	}

	public boolean shouldRemove() {
		return remove;
	}

	/**
	 * ensures: the Tile is displayed correctly
	 * 
	 * @param g2
	 */
	public abstract void display(Graphics2D g2);

	/**
	 * ensures: how the player should collide with this tile.
	 * 
	 * @param a: the Player
	 * @param ix
	 * @param iy
	 */
	public abstract void handleCollision(Player a, double ix, double iy);

	/**
	 * ensures: how HunterSeekers collide with this tile
	 * 
	 * @param a: the HunterSeeker
	 * @param ix
	 * @param iy
	 */
	public abstract void handleSeekerCollision(HunterSeeker a, double ix, double iy);

	/**
	 * ensures: how Enemies collide with this tile
	 * 
	 * @param a: the enemy colliding with this tile
	 * @param ix
	 * @param iy
	 */
	public abstract void handleEnemyCollision(Enemy a, double ix, double iy);
}
