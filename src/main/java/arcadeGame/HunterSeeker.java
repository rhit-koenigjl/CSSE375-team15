package arcadeGame;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class HunterSeeker extends Enemy {
	private Player hero;
	private double gravity = 0.5;

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
		this.x = startX;
		this.y = startY;
		this.width = width;
		this.height = height;
		this.vx = 0;
		this.vy = 0;
		hero = h;
	}

	public HunterSeeker(double startX, double startY, double width, double height, double velocityX,
			double velocityY, Player h) {
		this.x = startX;
		this.y = startY;
		this.width = width;
		this.height = height;
		this.vx = velocityX;
		this.vy = velocityY;
		hero = h;
	}

	@Override
	public void drawActor(Graphics2D g2) {
		drawImage(g2, "angry_ghost_right.png");
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
		if (x + width < hero.getX()) {
			goalSpeed = speed;
		}
		if (x > hero.getX() + hero.getWidth()) {
			goalSpeed = -speed;
		}

		if (y > hero.getY() + hero.getHeight() / 2) {
			this.vy -= 0.75;
		}

		vx += (goalSpeed - vx) / approachFactor;
		vy += gravity;
	}

	@Override
	public boolean isNonTrackingEnemy() {
		return false;
	}

}
