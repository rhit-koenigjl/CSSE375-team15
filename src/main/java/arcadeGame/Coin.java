package arcadeGame;

import java.awt.Graphics2D;

public class Coin extends Tile {

	Coin(int x, int y, int width, int height) {
		super(x, y, width, height, GameImage.COIN);
	}

	@Override
	protected void display(Graphics2D g) {
		drawImage(g);
	}

	@Override
	void handleCollision(Actor actor, double xPos, double yPos) {
		if (actor.isHero()) {
			setRemove();
		}
	}

}
