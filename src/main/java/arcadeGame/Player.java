package arcadeGame;

import java.awt.Graphics2D;
import java.util.List;
import java.util.Map;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Player extends Actor {
	// determines how much of the distance to max speed the player moves
	// e.g. 5 means each frame the displacement between the velocity and max speed
	// will be reduced by
	// 1/5
	private double gravity = 0.5;
	private double jumpHeight = 0.75;
	private double speed = 8;

	private Image leftImage;
	private Image rightImage;
	private Direction dir;

	/**
	 * ensures the correct constructor for the Player class
	 * 
	 * @param startX the initial x location
	 * @param startY the initial y location
	 * @param width  the players width
	 * @param height the players height
	 */
	public Player(double startX, double startY, double width, double height) {
		super(startX, startY, width, height);
		this.leftImage = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("images/player-left.gif"))
				.getImage();
		this.rightImage = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("images/player_right.gif"))
				.getImage();
		this.dir = Direction.RIGHT;
	}

	/**
	 * ensures the general running of the player class including moving the player,
	 * and calling the
	 * handleKeyAction() and handleCollision() methods
	 * 
	 * @param keys
	 * @param tiles
	 */
	public void update(Map<Integer, Boolean> keys, List<Tile> tiles) {
		vy += gravity;
		super.update(tiles);
		handleKeyAction(keys);
	}

	/**
	 * Helper function to tell if a key is being pressed
	 * 
	 * @param keys a hashmap of keycodes and true or false values
	 * @param val  the keycode being examined
	 * @return the boolean value associating to whether a key is being pressed or
	 *         not
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
		setSpikeCollision(false);
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

		if (this.vx > 0) {
			this.dir = Direction.RIGHT;
		}
		if (this.vx < 0) {
			this.dir = Direction.LEFT;
		}
	}

	/**
	 * Uses java swing to display the player
	 */
	@Override
	public void drawActor(Graphics2D g2) {
		g2.drawImage(this.dir == Direction.RIGHT ? rightImage : leftImage, (int) (x - width / 2.0),
				(int) (y - height / 2.0), (int) width * 2, (int) height * 2, null);
	}

	@Override
	public boolean isHero() {
		return true;
	}

}
