package arcadeGame;

import java.awt.Graphics2D;

public class GameObject {
	protected double x;
	protected double y;
	protected double width;
	protected double height;
	protected double vx = 0;
	protected double vy = 0;
	protected GameImage gameImage;
	protected Direction dir = Direction.NONE;

	public GameObject(double x, double y, double width, double height, GameImage gameImage) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gameImage = gameImage;
	}

	public GameObject(int x, int y, int width, int height, GameImage gameImage) {
		this.x = (double) x;
		this.y = (double) y;
		this.width = (double) width;
		this.height = (double) height;
		this.gameImage = gameImage;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public boolean collidesWith(GameObject o) {
		return x + width > o.x && y + height > o.y && x < o.x + o.width && y < o.y + o.height;
	}

	protected void drawImage(Graphics2D g) {
		g.drawImage(gameImage.getImage(dir), (int) x, (int) y, (int) width, (int) height, null);
	}

	protected void performImageOffset(double scale, double offset, boolean negativeDirection) {
		x += offset * width * (negativeDirection ? -1.0 : 1.0);
		y += offset * height * (negativeDirection ? -1.0 : 1.0);
		height *= scale;
		width *= scale;
	}

	protected void resetImageOffset(double scale, double offset, boolean negativeDirection) {
		height /= scale;
		width /= scale;
		x += offset * width * (negativeDirection ? -1.0 : 1.0);
		y += offset * height * (negativeDirection ? -1.0 : 1.0);
	}


}
