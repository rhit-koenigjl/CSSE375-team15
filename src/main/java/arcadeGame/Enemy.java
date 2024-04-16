package arcadeGame;

import java.awt.Graphics2D;

public class Enemy extends Actor {
	private static final int STARTING_X = 100;
	private static final int STARTING_Y = 100;
	private static final int WIDTH = 40;
	private static final int HEIGHT = 40;

	protected boolean adding = false;

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
		this(startX, startY, width, height, GameImage.GHOST);
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
		super(startX, startY, width, height, GameImage.GHOST);
		vx = velocityX;
		vy = velocityY;
	}

	public Enemy(double startX, double startY, double width, double height, GameImage gameImage) {
		super(startX, startY, width, height, gameImage);
		this.dir = Direction.RIGHT;
		vx = Math.random() * SPEED;
		vy = Math.random() * SPEED;
	}

	@Override
	void drawActor(Graphics2D g2) {
		if (vx != 0 || vy != 0) {
			String horizontalDirection = (vx == 0) ? "" : (vx > 0) ? "R" : "L";
			String verticalDirection = (vy == 0) ? "" : (vy > 0) ? "D" : "U";
			String direction = String.format("%s%s", (vx != 0) ? verticalDirection : "",
					(vy != 0) ? horizontalDirection : "");
			this.dir = Direction.fromString(direction);
		}
		drawImage(g2);
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
