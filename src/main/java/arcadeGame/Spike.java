package arcadeGame;

import java.awt.Graphics2D;

public class Spike extends Tile {

	Spike(int x, int y, int sideLength, Direction d) {
		super(d == Direction.LEFT ? x + sideLength / 4 : x, d == Direction.UP ? y + sideLength / 4 : y,
				d == Direction.RIGHT || d == Direction.LEFT ? sideLength * 3 / 4 : sideLength,
				d == Direction.DOWN || d == Direction.UP ? sideLength * 3 / 4 : sideLength,
				GameImage.SPIKE);
		this.dir = d;
	}

	@Override
	void display(Graphics2D g2) {
		drawImage(g2);
	}

	@Override
	void handleCollision(Actor a, double ix, double iy) {
		if (ix > 0) {
			if (this.dir == Direction.LEFT) {
				a.setSpikeCollision(true);
			}
			a.setX(this.x - a.getWidth());
			a.setVx(0);
		}

		if (iy > 0) {
			if (this.dir == Direction.UP) {
				a.setSpikeCollision(true);
			}
			a.setY(this.y - a.getHeight());
			a.setVy(0);
		}

		if (ix < 0) {
			if (this.dir == Direction.RIGHT) {
				a.setSpikeCollision(true);
			}
			a.setX(this.x + this.width);
			a.setVx(0);
		}

		if (iy < 0) {
			if (this.dir == Direction.DOWN) {
				a.setSpikeCollision(true);
			}
			a.setY(this.y + this.height);
			a.setVy(0);
		}
	}

}
