package arcadeGame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class EnemySpawner extends Enemy {
	private ArrayList<Enemy> enemies;
	private Player p;
	private double timeInc = 0;
	private int spawnType = 0;
	
	/**
	 * ensures: the correct initialization of the EnemySpawner
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 * @param enemies
	 * @param player
	 */
	public EnemySpawner(double startX, double startY, double width, double height, ArrayList<Enemy> enemies, Player player) {
		super(startX, startY, width, height);
		this.enemies = enemies;
		this.p = player;
		this.vx = 0;
		this.vy = 0;
	}
	
	public EnemySpawner(double startX, double startY, double width, double height, ArrayList<Enemy> enemies, Player player, int enemySpawnType) {
		super(startX, startY, width, height);
		this.enemies = enemies;
		this.p = player;
		this.vx = 0;
		this.vy = 0;
		this.spawnType = enemySpawnType;
	}
	
	public EnemySpawner(double startX, double startY, double width, double height, double velocityX, double velocityY, ArrayList<Enemy> enemies, Player player, int enemySpawnType) {
		super(startX, startY, width, height);
		this.enemies = enemies;
		this.p = player;
		this.vx = velocityX;
		this.vy = velocityY;
		this.spawnType = enemySpawnType;
	}
	
	/**
	 *ensures: the drawing of hte EnemySpawner
	 */
	public void drawActor(Graphics2D g2) {
		g2.setColor(Color.red);
		g2.translate((int)(x + width/2), (int) (y + height/2));
		g2.rotate(timeInc);
		g2.drawRect((int)(-width/2), (int)(-height/2), (int) width, (int) height);
		g2.rotate(-timeInc);
		g2.translate((int)(-x - width/2), (int) (-y - height/2));
		
	}
	
	/**
	 * ensures: the addition of new enemies into the game
	 */
	public void update(ArrayList<Tile> tiles) {
		super.update(tiles);
		timeInc += 0.1;
		if(timeInc > Math.PI * 2) {
			timeInc = 0;
			this.adding = true;
		}
		
	}
	
	@Override
	public void controll() {
		double goalSpeed = 0;
		vx += (goalSpeed - vx)/(approachFactor*12);
		vy += (goalSpeed - vy)/(approachFactor*12);
	}
	
	/**
	 * ensures: returns a new enemy that runs towards the players current position
	 */
	public Enemy returnNew() {
		if(spawnType==0) {
			double angle = Math.atan2(p.getY() - y, p.getX() - x);
			return new Enemy(x, y, width, height, Math.cos(angle) * 4, Math.sin(angle) * 4);
		} else if(spawnType==1) {
			double angle = Math.atan2(p.getY() - y, p.getX() - x);
			return new HunterSeeker(x, y, width, height, Math.cos(angle) * 4, Math.sin(angle) * 4, p);
		} else if(spawnType==2) {
			double angle = Math.atan2(p.getY() - y, p.getX() - x);
			return new EnemySpawner(x, y, width, height, Math.cos(angle) * 4, Math.sin(angle) * 4, enemies, p, 0);
		} else if(spawnType==3) {
			double angle = Math.atan2(p.getY() - y, p.getX() - x);
			return new EnemySpawner(x, y, width, height, Math.cos(angle) * 4, Math.sin(angle) * 4, enemies, p, 3);
		} else {
			return new Enemy();
		}
	} 
}
