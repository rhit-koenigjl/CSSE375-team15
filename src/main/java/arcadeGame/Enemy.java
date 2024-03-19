package arcadeGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * @author koenigjl
 *
 */
public class Enemy extends Actor {

	protected int patrolDistance = 300;
	protected double initialX;
	protected double initialY;
	protected Color enemyColor = new Color(255, 0, 0);
	protected boolean adding = false;

	/**
	 * ensures: the correct initialization of an Enemy
	 */
	public Enemy() {
		super(100, 100, 40, 40); // viable arbitrary starting values
		initialX = 100;
		initialY = 100;
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
		initialX = startX;
		initialY = startY;
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
		initialX = startX;
		initialY = startY;
		vx = velocityX;
		vy = velocityY;
	}

	@Override
	public void drawActor(Graphics2D g2) {
		// Draws the enemy, not much more to say
		g2.setColor(enemyColor);
		super.drawActor(g2);
	}

	/**
	 * ensures: the handling of the movement in relation to it's velocity and calls all collision code
	 * 
	 * @param tiles
	 */
	public void update(ArrayList<Tile> tiles) {
		// updates position based on vx and vy, is basically the same code as hero but simplified
		controll();
		x += vx;
		handleCollisions(tiles, vx, 0);
		y += vy;
		handleCollisions(tiles, 0, vy);

	}

	/**
	 * ensures: the running of the collision code related to an enemy class
	 * 
	 * @param tiles
	 * @param ix
	 * @param iy
	 */
	public void handleCollisions(ArrayList<Tile> tiles, double ix, double iy) {
		// same as hero code but with different actions taken after collision
		for (Tile t : tiles) {
			if (checkCollision(t))
				t.handleEnemyCollision(this, ix, iy);
		}

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

	public void controll() {}

}
