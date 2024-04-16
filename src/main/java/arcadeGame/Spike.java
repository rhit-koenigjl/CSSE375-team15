package arcadeGame;

import java.awt.Graphics2D;
import java.util.HashMap;

public class Spike extends Tile {
	private Direction dir;
	private static final HashMap <Direction, String> imageMap = new HashMap<Direction, String>() {
		{
			put(Direction.DOWN, "downward_spike.png");
			put(Direction.UP, "upward_spike.png");
			put(Direction.LEFT, "leftward_spike.png");
			put(Direction.RIGHT, "rightward_spike.png");
		}
	};

	/**
	 * ensures: calls the Tile constructor
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Spike(int x, int y, int sideLength, Direction d) {
		super(d == Direction.LEFT ? x + sideLength / 4 : x, 
		      d == Direction.UP ? y + sideLength / 4 : y,
			  d == Direction.RIGHT || d == Direction.LEFT ? sideLength * 3 / 4 : sideLength,
			  d == Direction.DOWN || d == Direction.UP ? sideLength * 3 / 4 : sideLength);
		this.dir = d;
	}

	@Override
	public void display(Graphics2D g2) {
		drawImage(g2);
	}

	@Override
	public void handleCollision(Actor a, double ix, double iy) {
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
