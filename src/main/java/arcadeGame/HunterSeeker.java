package arcadeGame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class HunterSeeker extends Enemy {
	private Player hero;
	private double gravity = 0.5;
	private Color c = new Color(130, 34, 21);
	
	/**
	 * ensures: the correct initialization of a Hunter Seeker
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
	
	public HunterSeeker(double startX, double startY, double width, double height, double velocityX, double velocityY, Player h) {
		this.x = startX;
		this.y = startY;
		this.width = width;
		this.height = height;
		this.vx = velocityX;
		this.vy = velocityY;
		hero = h;
	}
	
	
	@Override 
	public void drawActor(Graphics2D g2){
		g2.setColor(c);
		g2.translate((int) getX(), (int) getY());
		g2.fillRect(0, 0, (int) width, (int) height);
		g2.translate((int) -getX(), (int) -getY());
	}
	

	
	/**
	 * ensures: that the HunterSeeker has the correct collision methods called in relation to him.
	 */
	public void handleCollisions(ArrayList<Tile> tiles, double ix, double iy) {
		for(Tile t: tiles) {
			if(checkCollision(t))
				t.handleSeekerCollision(this, ix, iy);
		}
	}
	
	
	/**
	 * ensures: the HunterSeeker's velocity it set so that it follows the player.
	 */
	@Override
	public void controll() {
		double goalSpeed = 0;
		if(x + width < hero.getX()) {
			goalSpeed = speed;
		}
		if(x > hero.getX() + hero.getWidth()) {
			goalSpeed = -speed;
		}
		
		if(y > hero.getY() + hero.getHeight() / 2) {
			this.vy -= 0.75;
		}
		
		vx += (goalSpeed - vx)/approachFactor;
		vy += gravity;
	}

}

