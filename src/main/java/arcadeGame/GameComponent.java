package arcadeGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class GameComponent extends JComponent {
	private SceneManager sceneManager;
	private int score = 0;

	// Fields for level management and creation
	private String levelFiles[] = {"levels/level00", "levels/level01", "levels/level02",
			"levels/level03", "levels/level04", "levels/level05", "levels/level06", "levels/level07",
			"levels/level08", "levels/level09", "levels/level10", "levels/level11", "levels/level12",};
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
		this.sceneManager = new SceneManager(null);
		SceneUpdater s = new GameUpdater(sceneManager, currentLevel, keys, state);
		this.sceneManager.switchScene(s);
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
		sceneManager.runScene();
	}

	/**
	 * ensures: the drawing of the screen that must take place every frame.
	 */
	public void drawScreen() {
		this.repaint();
	}

	/**
	 * ensures: the drawing of all the objects onto the JFrame
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		int xMiddle = 50;
		int yMiddle = (currentLevel.getHeight() * 50 + 37) / 2;

		String shownString = "";
		sceneManager.drawScene(g2, shownString, xMiddle, yMiddle, score);
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
			this.sceneManager.setLevel(currentLevel);
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
			sceneManager.switchScene(new ResetUpdater(sceneManager, sceneManager.getCurrentScene()));
		} else {
			sceneManager.switchScene(new LossUpdater(sceneManager, currentLevel));
		}
	}

	public int getLevelCount() {
		return levelFiles.length;
	}

	public void incrementScore(int score) {
		this.score += score;
	}

}
