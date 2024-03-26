package arcadeGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Map;

/**
 * @author welchtj
 *
 */
public class Player extends Actor {
	// determines how much of the distance to max speed the player moves
	// e.g. 5 means each frame the displacement between the velocity and max speed will be reduced by
	// 1/5

	private double gravity = 0.5;
	private double jumpHeight = 0.75;

	private double speed = 8;
	private int lives = 3;

	/**
	 * ensures the correct constructor for the Player class
	 * 
	 * @param startX the initial x location
	 * @param startY the initial y location
	 * @param width the players width
	 * @param height the players height
	 */
	public Player(double startX, double startY, double width, double height) {
		super(startX, startY, width, height);
	}

	/**
	 * ensures the general running of the player class including moving the player, and calling the
	 * handleKeyAction() and handleCollision() methods
	 * 
	 * @param keys
	 * @param tiles
	 */
	public void update(Map<Integer, Boolean> keys, List<Tile> tiles) {
		super.update(tiles);
		handleKeyAction(keys);
		vy += gravity;
	}

	/**
	 * Helper function to tell if a key is being pressed
	 * 
	 * @param keys a hashmap of keycodes and true or false values
	 * @param val the keycode being examined
	 * @return the boolean value associating to whether a key is being pressed or not
	 */
	public boolean findKey(Map<Integer, Boolean> keys, int val) {
		return keys.getOrDefault(val, false);
	}

	/**
	 * @param enemy
	 * @param ix
	 * @param iy
	 * @return
	 */
	public int handleCollisions(Enemy enemy) {
		int collisionType = 0; // 0 = No Collision, 1 = Enemy Won Collision, 2 = Player Won Collision
		if (collidesWith(enemy)) {
			if (y + height * 3 / 4 - vy < enemy.getY() - enemy.getVy()) {
				collisionType = 2;
			} else {
				collisionType = 1;
			}
		}
		return collisionType;
	}

	public void loseLife() {
		System.out.println(lives);
		lives--;
		didCollideWithSpikes = false;
		// System.out.println("Spike Collision Reset");
	}

	public boolean checkLives() {
		return lives > 0;
	}

	public int getNumberOfLives() {
		return lives;
	}

	public void clearMovementSpeed() {
		vx = 0;
		vy = 0;
	}

	/**
	 * Takes in keycodes and determines the correct velocity of the player
	 * 
	 * @param keys
	 */
	public void handleKeyAction(Map<Integer, Boolean> keys) {
		int desiredVelocity = 0;
		desiredVelocity = 0;
		if (findKey(keys, 39))
			desiredVelocity += speed;
		if (findKey(keys, 37))
			desiredVelocity -= speed;
		if (findKey(keys, 38))
			vy -= jumpHeight;
		if (findKey(keys, 40)) {
			vy += jumpHeight;
		}

		this.setVx(this.getVx() + (desiredVelocity - this.getVx()) / approachFactor);
	}

	/**
	 * Uses java swing to display the player
	 */
	@Override
	public void drawActor(Graphics2D g2) {
		g2.setColor(new Color(0, 150, 0));
		g2.translate((int) getX(), (int) getY());
		g2.fillRect(0, 0, (int) width, (int) height);
		g2.setColor(Color.black);
		g2.fillRect(0, (int) ((height / 4) * 3), (int) width, (int) (height / 4));
		g2.translate((int) -getX(), (int) -getY());
	}

	@Override
	public boolean isHero() {
		return true;
	}
}
