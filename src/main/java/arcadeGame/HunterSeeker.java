package arcadeGame;

import java.util.ArrayList;
import java.util.List;

public class HunterSeeker extends Enemy {
	private Player hero;

	/**
	 * ensures: the correct initialization of a Hunter Seeker
	 * 
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 * @param h
	 */
	public HunterSeeker(double startX, double startY, double width, double height, Player h) {
		this(startX, startY, width, height, 0, 0, h);
	}

	public HunterSeeker(double startX, double startY, double width, double height, double velocityX,
			double velocityY, Player h) {
		super(startX, startY, width, height, velocityX, velocityY, GameImage.TRACKER);
		this.x = startX;
		this.y = startY;
		this.width = width;
		this.height = height;
		this.vx = velocityX;
		this.vy = velocityY;
		hero = h;
		this.gameImage = GameImage.TRACKER;
	}

	/**
	 * ensures: that the HunterSeeker has the correct collision methods called in relation to him.
	 */
	public void handleCollisions(ArrayList<Tile> tiles, double ix, double iy) {
		for (Tile t : tiles) {
			if (collidesWith(t))
				t.handleCollision(this, ix, iy);
		}
	}

	@Override
	public void update(List<Tile> tiles) {
		control();
		super.update(tiles);
	}

	/**
	 * ensures: the HunterSeeker's velocity it set so that it follows the player.
	 */
	public void control() {
		double goalSpeed = 0;
		double goalLift = 0;
		if (x + width < hero.getX()) {
			goalSpeed = width * DEFAULT_SPEED * 0.5;
		}
		if (x > hero.getX() + hero.getWidth()) {
			goalSpeed = -width * DEFAULT_SPEED * 0.5;
		}

		if (y > hero.getY() + hero.getHeight() / 2) {
			goalLift = -width * DEFAULT_SPEED * 0.5;
		} else {
			goalLift = width * DEFAULT_SPEED * 0.5;
		}

		vx += (goalSpeed - vx) / approachFactor;
		vy += (goalLift - vy) / approachFactor;
	}

	@Override
	public boolean isNonTrackingEnemy() {
		return false;
	}

}
