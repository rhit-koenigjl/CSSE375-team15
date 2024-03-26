package arcadeGame;

public class GameObject {
	protected double x;
	protected double y;
	protected double width;
	protected double height;

	protected double vx = 0;
	protected double vy = 0;

	public GameObject(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public GameObject(int x, int y, int width, int height) {
		this.x = (double) x;
		this.y = (double) y;
		this.width = (double) width;
		this.height = (double) height;
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

	public boolean checkCollision(GameObject o) {
		return x + width > o.x && y + height > o.y && x < o.x + o.width && y < o.y + o.height;
	}
}
