package arcadeGame;

import java.awt.Graphics2D;

public abstract class Actor extends GameObject{
	protected double approachFactor = 4;
	protected double speed = 5;

	protected boolean didCollideWithSpikes = false;

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
	public void drawActor(Graphics2D g2) {
		g2.translate((int) getX(), (int) getY());
		g2.fillRect(0, 0, (int) width, (int) height);
		g2.translate((int) -getX(), (int) -getY());
	}

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
}
