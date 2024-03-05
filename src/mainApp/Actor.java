package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class Actor {	
	
	//bounding box of the actor
	protected double x;
	protected double y;
	protected double width;
	protected double height;
	
	//velocity variables
	protected double vx;
	protected double vy = 0;
	protected double approachFactor = 4;
	protected double speed = 5;
	
	protected boolean didCollideWithSpikes = false;
	
	
	/**
	 * ensures: the proper construction of an Actor instance
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 */
	public Actor(double startX, double startY, double width, double height){
		this.setX(startX);
		this.setY(startY);
		this.width = width;
		this.height = height;
	}
	
	/**
	 * ensures: the proper drawing of a basic actor
	 * @param g2: the graphics package being used
	 */
	public void drawActor(Graphics2D g2) {
		g2.translate((int) getX(), (int) getY());
		g2.fillRect(0, 0, (int) width, (int) height);
		g2.translate((int) -getX(), (int) -getY());
	}
	
	/**
	 * ensures: returns a true or false value whether or not this actor is colliding with a tile
	 * @param t: the tiles
	 * @return: true if the actor is overlapping a tile, false if not
	 */
	public boolean checkCollision (Tile t) {
		return x + width > t.getX() && 
			   y + height > t.getY() &&
			   x < t.getX() + t.getWidth() &&
			   y < t.getY() + t.getHeight();
	}
	


	@Override
	public String toString() {
		return "Actor [x=" + getX() + ", y=" + getY() + ", width=" + width + ", height=" + height + ", vx=" + getVx() + ", vy=" + getVy()
				+ "]";
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
	
	public double getWidth() {
		return width;
	}
	
	public void setHeight(double newHeight) {
		height = newHeight;
	}
	
	public void setWidth(double newWidth) {
		width = newWidth;
	}

}
