package arcadeGame;

import java.awt.Graphics2D;
import java.util.List;
import java.util.Map;

public class Player extends Actor {

	private double horizontalSpeed;

	private final int FLY_COOLDOWN = 30;
	private int flyCooldownTimer = 0;

	private double downWardPushAcceleration;

	private double naturalFallMaxSpeed;
	private double naturalFallAcceleration;

	private double flyJumpSpeed;
	private double flyPassiveSpeed;
	private double flyMaxSpeed;

	public Player(double startX, double startY, double width, double height) {
		super(startX, startY, width, height, GameImage.PLAYER);

		dir = Direction.RIGHT;

		horizontalSpeed = width * DEFAULT_SPEED * 8f / 5f;

		downWardPushAcceleration = width / 75;

		naturalFallMaxSpeed = width / 7;
		naturalFallAcceleration = width / 300;

		flyJumpSpeed = width / 17;
		flyPassiveSpeed = width / 250;
		flyMaxSpeed = width/7;
	}

	void update(Map<Integer, Boolean> keys, List<Tile> tiles) {
		flyCooldownTimer--;
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
		handleXControls(keys);
		handleYControls(keys);
	}

	private void handleYControls(Map<Integer, Boolean> keys) {
		if (findKey(keys, 38) && !findKey(keys, 40)) {
			upEffect();
		} else if (findKey(keys, 40) && !findKey(keys, 38)) {
			downEffect();
		} else {
			passiveEffect();
		}
	}

	private void passiveEffect() {
		if (vy < naturalFallMaxSpeed) {
			vy += Math.min(naturalFallMaxSpeed - vy, naturalFallAcceleration);
		}
	}

	private void upEffect() {
		if (flyCooldownTimer <= 0 && vy >= 0) {
			vy = -flyJumpSpeed;
			flyCooldownTimer = FLY_COOLDOWN;
		} else if (vy > -flyMaxSpeed) {
			vy += Math.max(-flyPassiveSpeed, -flyMaxSpeed - vy);
		}
	}

	private void downEffect() {
		vy += downWardPushAcceleration;
	}

	private void handleXControls(Map<Integer, Boolean> keys) {
		int desiredVelocity = 0;

		if (findKey(keys, 39))
			desiredVelocity += this.horizontalSpeed;

		if (findKey(keys, 37))
			desiredVelocity -= this.horizontalSpeed;

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
