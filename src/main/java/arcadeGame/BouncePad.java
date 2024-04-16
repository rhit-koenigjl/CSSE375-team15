package arcadeGame;

import java.awt.Graphics2D;

public class BouncePad extends Tile {

	/**
	 * ensures: calls the Tile constructor for the bounce pad
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public BouncePad(int x, int y, int width, int height) {
		super(x, y, width, height, GameImage.BOUNCE_PAD);
	}

	@Override
	protected void display(Graphics2D g2) {
		this.y -= 30;
		this.height = 50;
		drawImage(g2);
		this.height = 20;
		this.y += 30;
	}

	@Override
	public void handleCollision(Actor actor, double xPos, double yPos) {
		if (xPos > 0) {
			actor.setX(x - actor.getWidth());
			actor.setVx(0);
		}
		if (xPos < 0) {
			actor.setX(x + width);
			actor.setVx(0);
		}
		if (yPos > 0) {
			actor.setY(y - actor.getHeight());
			actor.setVy(-30);
		}
		if (yPos < 0) {
			actor.setY(y + height);
			actor.setVy(0);
		}
	}

}
