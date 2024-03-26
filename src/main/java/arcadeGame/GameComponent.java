package arcadeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class GameComponent extends JComponent {
	private String scene = "menu";
	private int transitionTimer = 0;
	private int score = 0;

	// Fields for level management and creation
	private String levelFiles[] = {"levels/level00", "levels/level01", "levels/level02", "levels/level03",
			"levels/level04", "levels/level05", "levels/level06", "levels/level07", "levels/level08",
			"levels/level09", "levels/level10", "levels/level11", "levels/level12",};
	private Level currentLevel;
	private UpdateState state = new UpdateState(this);

	// User fields
	private Player hero = new Player(0, 0, 0, 0);
	private Map<Integer, Boolean> keys = new HashMap<Integer, Boolean>();

	// the containing frame
	private JFrame frame;

	/**
	 * Ensures the creation of the Game Component and initializes the first level
	 * 
	 * @param frame the frame that the game is taking place in, used for resizing to fit each level.
	 */
	public GameComponent(JFrame frame) {
		this.frame = frame;
		this.currentLevel = new Level(new File(levelFiles[0]), 0, hero);
	}

	public void loadLevelByIndex(int index) {
		switchLevel(levelFiles[index], index);
	}

	/**
	 * ensures: the editing of the keys HashMap to update what keys are pressed
	 * 
	 * @param keyCode: the key being pressed or released
	 * @param newVal: the new value to be associated with that keyCode
	 */
	public void handleKey(int keyCode, boolean newVal) {
		keys.put(keyCode, newVal);
	}

	/**
	 * ensures: the actions of the game that must take place every frame
	 */
	public void updateState() {
		switch (scene) {
			case "game":
				currentLevel.update(this.keys, state);
				break;
			case "menu":
				// ensures you can start the game from the menu
				if (keys.getOrDefault(32, false)) {
					switchScene("game");
				}
				break;
			case "reset":
				transitionTimer++;
				if (transitionTimer > 350) {
					transitionTimer = 0;
					switchScene("game");
					break;
				}
			case "pause":
				if (keys.getOrDefault(27, false)) {
					transitionTimer = 0;
					switchScene("game");
					keys.remove(27);
				}
				break;
		}
	}

	/**
	 * ensures: the drawing of the screen that must take place every frame.
	 */
	public void drawScreen() {
		this.repaint();
	}

	/**
	 * allows the switching of states
	 * 
	 * @param newState
	 */
	public void switchScene(String newState) {
		scene = newState;
	}

	/**
	 * ensures: the drawing of all the objects onto the JFrame
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Color previousColor = g2.getColor();
		int fontSize = 28;
		g2.setFont(new Font("Monospaced", Font.BOLD, fontSize));
		int xMiddle = 50;
		int yMiddle = (currentLevel.getHeight() * 50 + 37) / 2;
		String shownString;
		switch (scene) {
			case "game":
				currentLevel.draw(g2, score);
				g2.setColor(previousColor);
				break;
			case "menu":
				shownString = "Press Spacebar to Start";
				g2.drawString(shownString, xMiddle, yMiddle - 50);
				g2.drawString("Use the Arrow Keys to move", xMiddle, yMiddle);
				g2.drawString("Use Escape to pause the game", xMiddle, yMiddle + 50);
				break;
			case "win":
				shownString = "Congrats! You Won! Your score was: " + score;
				g2.drawString(shownString, xMiddle, yMiddle);
				break;
			case "loss":
				shownString = "You ran out of lives on level " + currentLevel.getIndex() + ", Your score was: " + score;
				g2.drawString(shownString, xMiddle, yMiddle);
				break;
			case "reset":
				shownString = "You got hit! Restarting the level in " + (350 - transitionTimer) / 100;
				g2.drawString(shownString, xMiddle, yMiddle);
				break;
			case "pause":
				currentLevel.handlePause(g2, previousColor);
				shownString = "Game Paused. Press Escape to Continue";
				g2.drawString(shownString, xMiddle, yMiddle);
				break;
		}
	}

	/**
	 * ensures: the resizing of a frame to fit the size of the level
	 * 
	 * @param frame: the JFrame to get resized
	 */
	public void sizeFrame(JFrame frame) {
		frame.setSize(currentLevel.getWidth() * 50 + 14, currentLevel.getHeight() * 50 + 37);
	}

	/**
	 * ensures: the switching of levels
	 * 
	 * @param newLevel The level to go to.
	 */
	public void switchLevel(String newLevel, int index) {
		if (index == currentLevel.getIndex()) {
			currentLevel.reset();
			return;
		} else {
			this.currentLevel = new Level(new File(newLevel), index, hero);
			currentLevel.generateLevel();
		}
		sizeFrame(this.frame);
		frame.repaint();
	}

	public void levelReset() {
		loadLevelByIndex(currentLevel.getIndex());
		System.out.println("Level Reset");
	}

	public void loseLife() {
		hero.loseLife();
		levelReset();
		System.out.println("You Died! Lives left: " + hero.getNumberOfLives());
		if (hero.checkLives()) {
			switchScene("reset");
		}
	}

	public int getLevelCount() {
		return levelFiles.length;
	}

	public void incrementScore(int score) {
		this.score += score;
	}
}
