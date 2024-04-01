package arcadeGame;

import java.awt.Graphics2D;

public class Enemy extends Actor {
	protected boolean adding = false;
	private String direction;

	/**
	 * ensures: the correct initialization of an Enemy
	 */
	public Enemy() {
		super(100, 100, 40, 40); // viable arbitrary starting values
		vx = Math.random() * speed;
		vy = Math.random() * speed;
	}

	/**
	 * ensures: the creation of a new enemy with specified coordinates and size
	 * 
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 */
	public Enemy(double startX, double startY, double width, double height) {
		super(startX, startY, width, height);
		vx = Math.random() * speed;
		vy = Math.random() * speed;
	}

	/**
	 * ensures: the correct creation of an enemy with a specified velocity
	 * 
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 * @param velocityX
	 * @param velocityY
	 */
	public Enemy(double startX, double startY, double width, double height, double velocityX,
			double velocityY) {
		super(startX, startY, width, height);
		vx = velocityX;
		vy = velocityY;
	}

	@Override
	public void drawActor(Graphics2D g2) {
		drawImage(g2, "ghost");
	}

	@Override
	protected void drawImage(Graphics2D g2, String filename) {
		String directedFile = String.format("%s_%s.png", filename, getDirection());
		super.drawImage(g2, directedFile);
	}

	private String getDirection() {
		if (vx != 0 || vy != 0) {
			if (vx == 0) {
				direction = getVerticalDirection();
			} else if (vy == 0) {
				direction = getHorizontalDirection();
			} else {
				direction = String.format("%s_%s", getVerticalDirection(), getHorizontalDirection());
			}
		}
		return direction;
	}

	private String getHorizontalDirection() {
		return (vx > 0) ? "right" : "left";
	}

	private String getVerticalDirection() {
		return (vy > 0) ? "down" : "up";
	}

	public boolean getAdding() {
		return adding;
	}

	public void setAdding(boolean newAdding) {
		adding = newAdding;
	}

	public Enemy returnNew() {
		return null;
	}

	@Override
	public boolean isNonTrackingEnemy() {
		return true;
	}

}
