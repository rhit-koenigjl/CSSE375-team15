package arcadeGame;
import java.awt.Color;
import java.awt.Graphics2D;

public class BouncePad extends Tile {

	/**
	 * ensures: calls the Tile constructor for the bouncepad
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public BouncePad(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void display(Graphics2D g2) {
		g2.translate(x, y);
		g2.setColor(Color.pink);
		g2.fillRect(0, 0, width, height);
		g2.translate(-x,-y);
	}

	@Override
	public void handleCollision(Player a, double ix, double iy) {
		if (ix > 0) {
			a.setX(x - a.getWidth());
			a.setVx(0);
		}
		if (ix < 0) {
			a.setX(x + width);
			a.setVx(0);
		}
		if(iy > 0) {
			a.setY(y - a.getHeight());
			a.setVy(-30);
		}
		if(iy < 0) {
			a.setY(y + height);
			a.setVy(0);
		}
	}

	@Override
	public void handleSeekerCollision(HunterSeeker a, double ix, double iy) {
		if (ix > 0) {
			a.setX(x - a.getWidth());
			a.setVx(0);
		}
		if (ix < 0) {
			a.setX(x + width);
			a.setVx(0);
		}
		if(iy > 0) {
			a.setY(y - a.getHeight());
			a.setVy(-30);
		}
		if(iy < 0) {
			a.setY(y + height);
			a.setVy(0);
		}
	}
	
	@Override
	public void handleEnemyCollision(Enemy a, double ix, double iy) {
		// TODO Auto-generated method stub
		if (ix > 0) {
			a.setX(x - a.getWidth());
			a.setVx(a.getVx() * -1);
		}
		if (ix < 0) {
			a.setX(x + width);
			a.setVx(a.getVx() * -1);
		}
		if(iy > 0) {
			a.setY(y - a.getHeight());
			a.setVy(a.getVy() * -1);
		}
		if(iy < 0) {
			a.setY(y + height);
			a.setVy(a.getVy() * -1);
		}
	}

}
