package arcadeGame;

import java.awt.Color;
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
		super(startX, startY, width, height);
		this.enemies = enemies;
		this.p = player;
		this.vx = velocityX;
		this.vy = velocityY;
	}

	/**
	 * ensures: the drawing of hte EnemySpawner
	 */
	public void drawActor(Graphics2D g2) {
		g2.setColor(Color.red);
		g2.translate((int) (x + width / 2), (int) (y + height / 2));
		g2.rotate(timeInc);
		g2.drawRect((int) (-width / 2), (int) (-height / 2), (int) width, (int) height);
		g2.rotate(-timeInc);
		g2.translate((int) (-x - width / 2), (int) (-y - height / 2));
	}

	/**
	 * ensures: the addition of new enemies into the game
	 */
	public void update(List<Tile> tiles) {
		super.update(tiles);
		timeInc += 0.1;
		if (timeInc > Math.PI * 2) {
			timeInc = 0;
			this.adding = true;
		}
	}

	public void control() {
		double goalSpeed = 0;
		vx += (goalSpeed - vx) / (approachFactor * 12);
		vy += (goalSpeed - vy) / (approachFactor * 12);
	}

	/**
	 * ensures: returns a new enemy that runs towards the players current position
	 */
	public abstract Enemy returnNew();
	// public Enemy returnNew() {
	// 	if (spawnType == 0) {
	// 		EnemyGenerator
	// 	} else if (spawnType == 1) {
	// 		HunterSeekerGenerator
	// 	} else if (spawnType == 2) {
	// 		EnemySpawnerGenerator
	// 	} else if (spawnType == 3) {
	// 		RecursiveEnemySpawnerGenerator
	// 	} else {
	// 		return new Enemy();
	// 	}
	// }

	protected double getAngle() {
		return Math.atan2(p.getY() - y, p.getX() - x);
	}

	public boolean isNonTrackingEnemy() {
		return false;
	}
}
