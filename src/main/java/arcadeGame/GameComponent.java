package arcadeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class GameComponent extends JComponent {
	private SceneManager sceneManager;
	private int score = 0;
	private int lives = 3;

	// Fields for level management and creation
	private String levelFiles[] = { "levels/level_set_1/level_1.json",
			"levels/level_set_1/level_2.json", "levels/level_set_1/level_3.json", "levels/level_set_1/level_4.json"};
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
	 * @param frame the frame that the game is taking place in, used for resizing to
	 *              fit each level.
	 */
	public GameComponent(JFrame frame) {
		this.frame = frame;
		this.currentLevel = new Level(levelFiles[0], 0, hero);
		this.sceneManager = new SceneManager(null);
		GameUpdater g = new GameUpdater(sceneManager, currentLevel, keys, state);
		MenuUpdater m = new MenuUpdater(sceneManager, g, keys);
		this.sceneManager.switchScene(m);
	}

	public void loadLevelByIndex(int index) {
		switchLevel(levelFiles[index], index);
	}

	/**
	 * ensures: the editing of the keys HashMap to update what keys are pressed
	 * 
	 * @param keyCode: the key being pressed or released
	 * @param newVal:  the new value to be associated with that keyCode
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

		sceneManager.drawScene(g2, score);
		g2.setFont(new Font("Monospaced", Font.BOLD, 28));
		g2.setColor(new Color(200, 255, 200));
		g2.drawString("Lives: " + lives, 25, 30);
		g2.drawString("Score: " + score, 25, 60);
	}

	/**
	 * ensures: the resizing of a frame to fit the size of the level
	 * 
	 * @param frame: the JFrame to get resized
	 */
	public void sizeFrame(JFrame frame) {
		frame.setSize(currentLevel.getWidth() + 14, currentLevel.getHeight() + 37);
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
			this.currentLevel = new Level(newLevel, index, hero);
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
		lives --;
		levelReset();
		System.out.println("You Died! Lives left: " + lives);
		if (lives > 0) {
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
