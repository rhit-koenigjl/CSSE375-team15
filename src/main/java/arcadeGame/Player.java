package arcadeGame;

import java.awt.Graphics2D;
import java.util.List;
import java.util.Map;

public class Player extends Actor {
	private double gravity;
	private double flyVelocity;

	private double speed = 8;

	public Player(double startX, double startY, double width, double height) {
		super(startX, startY, width, height, GameImage.PLAYER);
		this.dir = Direction.RIGHT;
		this.speed = this.width * DEFAULT_SPEED * 8.0 / 5.0;

		this.flyVelocity = this.width * (0.75/50f);
		this.gravity = this.width / 100f;
	}

	void update(Map<Integer, Boolean> keys, List<Tile> tiles) {
		vy += gravity;
		super.update(tiles);
		handleKeyAction(keys);
	}

	boolean findKey(Map<Integer, Boolean> keys, int val) {
		return keys.getOrDefault(val, false);
	}

	int handleCollisions(Enemy enemy) {
		int collisionType = 0; // 0 = No Collision, 1 = Enemy Won Collision, 2 = Player Won Collision
		if (collidesWith(enemy)) {
			System.out.println("Here");
			if (y + height * 3 / 4 - vy < enemy.getY() - enemy.getVy()) {
				collisionType = 2;
			} else {
				collisionType = 1;
			}
		}
		return collisionType;
	}

	void loseLife() {
		setSpikeCollision(false);
	}

	void clearMovementSpeed() {
		vx = 0;
		vy = 0;
	}

	void handleKeyAction(Map<Integer, Boolean> keys) {
		int desiredVelocity = 0;
		desiredVelocity = 0;
		if (findKey(keys, 39))
			desiredVelocity += speed;
		if (findKey(keys, 37))
			desiredVelocity -= speed;
		if (findKey(keys, 38))
			vy -= flyVelocity;
		if (findKey(keys, 40)) {
			vy += flyVelocity;
		}

		this.setVx(this.getVx() + (desiredVelocity - this.getVx()) / APPROACH_FACTOR);

		if (this.vx > 0) {
			this.dir = Direction.RIGHT;
		}
		if (this.vx < 0) {
			this.dir = Direction.LEFT;
		}
	}

	@Override
	void drawActor(Graphics2D g2) {
		drawImage(g2, 2.0, 0.5, true);
	}

	@Override
	protected boolean isHero() {
		return true;
	}

}
