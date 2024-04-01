package arcadeGame;

import java.awt.Graphics2D;
import java.util.List;

public abstract class Actor extends GameObject {
	protected double approachFactor = 4;
	protected double speed = 5;
	private boolean didCollideWithSpikes = false;

	/**
	 * ensures: the proper construction of an Actor instance
	 * 
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 */
	public Actor(double startX, double startY, double width, double height) {
		super(startX, startY, width, height);
	}

	/**
	 * ensures: the proper drawing of a basic actor
	 * 
	 * @param g2: the graphics package being used
	 */
	abstract void drawActor(Graphics2D g2);

	/**
	 * ensures: returns a true or false value whether or not this actor is colliding with a tile
	 * 
	 * @param t: the tiles
	 * @return: true if the actor is overlapping a tile, false if not
	 */

	@Override
	public String toString() {
		return "Actor [x=" + getX() + ", y=" + getY() + ", width=" + width + ", height=" + height
				+ ", vx=" + getVx() + ", vy=" + getVy() + "]";
	}

	protected void handleTileCollisions(List<Tile> tiles, double xVel, double yVel) {
		for (Tile tile : tiles) {
			if (collidesWith(tile))
				tile.handleCollision(this, xVel, yVel);
		}
	}

	protected void update(List<Tile> tiles) {
		x += vx * (isHero() ? 0.75 : 1);
		handleTileCollisions(tiles, vx, 0);
		y += vy;
		handleTileCollisions(tiles, 0, vy);
	}

	protected boolean isHero() {
		return false;
	}

	protected boolean isNonTrackingEnemy() {
		return false;
	}

	protected void setSpikeCollision(boolean didCollideWithSpikes) {
		this.didCollideWithSpikes = didCollideWithSpikes;
	}

	protected boolean getSpikeCollision() {
		return didCollideWithSpikes;
	}

}
