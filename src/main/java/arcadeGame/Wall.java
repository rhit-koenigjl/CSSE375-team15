package arcadeGame;

import java.awt.Graphics2D;

public class Wall extends Tile {

	Wall(int x, int y, int width, int height) {
		this(x, y, width, height, GameImage.BRICK);
	}

	Wall(int x, int y, int width, int height, GameImage gameImage) {
		super(x, y, width, height, gameImage);
	}

	void display(Graphics2D g) {
		drawImage(g);
	}

}
