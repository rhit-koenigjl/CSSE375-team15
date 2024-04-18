package arcadeGame;

import java.awt.Graphics2D;

public class MossyWall extends Wall {

	MossyWall(int x, int y, int width, int height) {
		super(x, y, width, height, GameImage.MOSSY_BRICK);
	}

	void display(Graphics2D g) {
		drawImage(g);
	}

}
