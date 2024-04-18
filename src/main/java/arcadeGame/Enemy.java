package arcadeGame;

import java.awt.Graphics2D;

public class Enemy extends Actor {
	protected boolean adding = false;

	Enemy(double startX, double startY, double width, double height) {
		this(startX, startY, width, height, GameImage.GHOST);
	}

	Enemy(double startX, double startY, double width, double height, double velocityX,
			double velocityY) {
		this(startX, startY, width, height, velocityX, velocityY, GameImage.GHOST);
	}

	Enemy(double startX, double startY, double width, double height, double velocityX,
			double velocityY, GameImage gameImage) {
		super(startX, startY, width, height, gameImage);
		this.dir = Direction.RIGHT;
		vx = velocityX;
		vy = velocityY;
	}

	Enemy(double startX, double startY, double width, double height, Direction dir) {
		super(startX, startY, width, height, GameImage.GHOST);
		double ang = Direction.toAngle(dir) + Math.PI / 2;
		this.vx = Math.cos(ang) * width * DEFAULT_SPEED;
		this.vy = Math.sin(ang) * height * DEFAULT_SPEED;
	}

	Enemy(double startX, double startY, double width, double height, GameImage gameImage) {
		this(startX, startY, width, height, Math.random() * SPEED, Math.random() * SPEED, gameImage);
	}

	@Override
	void drawActor(Graphics2D g2) {
		if (vx != 0 || vy != 0) {
			String horizontalDirection = (vx == 0) ? "" : (vx > 0) ? "R" : "L";
			String verticalDirection = (vy == 0) ? "" : (vy > 0) ? "D" : "U";
			String direction = String.format("%s%s", (vx != 0) ? verticalDirection : "",
					(vy != 0) ? horizontalDirection : "");
			this.dir = direction.equals("") ? this.dir : Direction.fromString(direction);
		}
		drawImage(g2);
	}

	boolean getAdding() {
		return adding;
	}

	void setAdding(boolean newAdding) {
		adding = newAdding;
	}

	protected Enemy returnNew() {
		return null;
	}

	@Override
	protected boolean isNonTrackingEnemy() {
		return true;
	}

	GameImage getImage() {
		return this.gameImage;
	}

	Direction getDir() {
		return this.dir;
	}

}
