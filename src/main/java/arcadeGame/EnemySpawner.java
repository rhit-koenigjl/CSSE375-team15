package arcadeGame;

import java.awt.Graphics2D;
import java.util.List;

public abstract class EnemySpawner extends Enemy {
	protected List<Enemy> enemies;
	protected Player p;
	private double timeInc = 0;

	/**
	 * ensures: the correct initialization of the EnemySpawner
	 * 
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 * @param enemies
	 * @param player
	 */
	public EnemySpawner(double startX, double startY, double width, double height, double velocityX,
			double velocityY, List<Enemy> enemies, Player player) {
		super(startX, startY, width, height, GameImage.SPAWNER);
		this.enemies = enemies;
		this.p = player;
		this.vx = velocityX;
		this.vy = velocityY;
	}

	/**
	 * ensures: the drawing of the EnemySpawner
	 */
	public void drawActor(Graphics2D g2) {
		drawImage(g2, 1.25, 0.125, true);
	}

	/**
	 * ensures: the addition of new enemies into the game
	 */
	public void update(List<Tile> tiles) {
		control();
		super.update(tiles);
		timeInc += 0.1;
		if (timeInc > Math.PI * 2) {
			timeInc = 0;
			this.adding = true;
		}
	}

	public void control() {
		double goalSpeed = 0;
		vx += (goalSpeed - vx) / (APPROACH_FACTOR * 12);
		vy += (goalSpeed - vy) / (APPROACH_FACTOR * 12);
	}

	/**
	 * ensures: returns a new enemy that runs towards the players current position
	 */
	public abstract Enemy returnNew();

	protected double getAngle() {
		return Math.atan2(p.getY() - y, p.getX() - x);
	}

	public boolean isNonTrackingEnemy() {
		return false;
	}

}
