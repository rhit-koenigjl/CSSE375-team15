package arcadeGame;

import java.awt.Color;
import java.awt.Graphics2D;

public class Spike extends Tile {
	/**
	 * ensures: calls the Tile constructor for the bouncepad
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Spike(int x, int y, int sideLength) {
		super(x, y, sideLength, sideLength);
	}

	@Override
	public void display(Graphics2D g2) {
		g2.translate(x, y);
		g2.setColor(Color.gray);
		g2.fillRect(0, 0, (int) width, (int) height);
		g2.translate(width / 2, -(height * Math.sqrt(2) / 2 - height / 2));
		g2.rotate(Math.PI / 4);
		g2.fillRect(0, 0, (int) width, (int) height);
		g2.rotate(-Math.PI / 4);
		g2.translate(-width / 2, (height * Math.sqrt(2) / 2 - height / 2));
		g2.translate(-x, -y);
	}

	@Override
	public void handleCollision(Player a, double ix, double iy) {
		if (ix > 0) {
			a.setX(x - a.getWidth());
			a.setVx(0);
			a.didCollideWithSpikes = true;
		}
		if (ix < 0) {
			a.setX(x + width);
			a.setVx(0);
			a.didCollideWithSpikes = true;
		}
		if (iy > 0) {
			a.setY(y - a.getHeight());
			a.setVy(0);
			a.didCollideWithSpikes = true;
		}
		if (iy < 0) {
			a.setY(y + height);
			a.setVy(0);
			a.didCollideWithSpikes = true;
		}
	}

	@Override
	public void handleSeekerCollision(HunterSeeker a, double ix, double iy) {
		if (ix > 0) {
			a.setX(x - a.getWidth());
			a.setVx(0);
			a.didCollideWithSpikes = true;
		}
		if (ix < 0) {
			a.setX(x + width);
			a.setVx(0);
			a.didCollideWithSpikes = true;
		}
		if (iy > 0) {
			a.setY(y - a.getHeight());
			a.setVy(0);
			a.didCollideWithSpikes = true;
		}
		if (iy < 0) {
			a.setY(y + height);
			a.setVy(0);
			a.didCollideWithSpikes = true;
		}
	}

	@Override
	public void handleEnemyCollision(Enemy a, double ix, double iy) {
		if (ix > 0) {
			a.setX(x - a.getWidth());
			a.setVx(a.getVx() * -1);
			a.didCollideWithSpikes = true;
		}
		if (ix < 0) {
			a.setX(x + width);
			a.setVx(a.getVx() * -1);
			a.didCollideWithSpikes = true;
		}
		if (iy > 0) {
			a.setY(y - a.getHeight());
			a.setVy(a.getVy() * -1);
			a.didCollideWithSpikes = true;
		}
		if (iy < 0) {
			a.setY(y + height);
			a.setVy(a.getVy() * -1);
			a.didCollideWithSpikes = true;
		}
	}

}
