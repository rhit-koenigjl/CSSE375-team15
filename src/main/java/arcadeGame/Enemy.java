package arcadeGame;

import java.awt.Graphics2D;

public class Enemy extends Actor {
	private static final int STARTING_X = 100;
	private static final int STARTING_Y = 100;
	private static final int WIDTH = 40;
	private static final int HEIGHT = 40;

	protected boolean adding = false;
	private String direction = "right";

	/**
	 * ensures: the correct initialization of an Enemy
	 */
	public Enemy() {
		this(STARTING_X, STARTING_Y, WIDTH, HEIGHT); // viable arbitrary starting values
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
		vx = Math.random() * SPEED;
		vy = Math.random() * SPEED;
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
	void drawActor(Graphics2D g2) {
		drawDirectedImage(g2, "ghost");
	}

	protected void drawDirectedImage(Graphics2D g2, String filename) {
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

	protected Enemy returnNew() {
		return null;
	}

	@Override
	public boolean isNonTrackingEnemy() {
		return true;
	}

}
