package arcadeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class GameComponent extends JComponent {
	private static final String LEVEL_DIRECTORY = "levels/level_set_1/";
	private final MessageGenerator generator = new AiMessageGenerator();
	private SceneManager sceneManager;
	private int score = 0;
	private int lives = 3;
	private String levelFiles[];
	private Level currentLevel;
	private UpdateState state = new UpdateState(this);
	private Player hero = new Player(0, 0, 0, 0);
	private Map<Integer, Boolean> keys = new HashMap<Integer, Boolean>();
	private JFrame frame;

	GameComponent(JFrame frame) {
		buildLevelsList();
		this.frame = frame;
		this.currentLevel = new Level(levelFiles[0], 0, hero);
		this.sceneManager = new SceneManager(null);
		GameUpdater g = new GameUpdater(sceneManager, currentLevel, keys, state);
		MenuUpdater m = new MenuUpdater(sceneManager, g, keys);
		this.sceneManager.switchScene(m);
	}

	private void buildLevelsList() {
		try {
			Path levelDir =
					Path.of(ClassLoader.getSystemClassLoader().getResource(LEVEL_DIRECTORY).toURI());
			levelFiles = Arrays.asList(levelDir.toFile().listFiles()).stream().map(File::getPath)
					.toArray(String[]::new);
		} catch (Exception e) {
			System.err.println("Could not load levels");
			e.printStackTrace();
		}
	}

	void loadLevelByIndex(int index) {
		switchLevel(levelFiles[index], index);
	}

	void handleKey(int keyCode, boolean newVal) {
		keys.put(keyCode, newVal);
	}

	void updateState() {
		sceneManager.runScene();
	}

	void drawScreen() {
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		sceneManager.drawScene(g2, score);
		g2.setFont(new Font("Monospaced", Font.BOLD, 28));
		g2.setColor(new Color(200, 255, 200));
		g2.drawString("Lives: " + lives, 25, 30);
		g2.drawString("Score: " + score, 25, 60);
	}

	void sizeFrame(JFrame frame) {
		frame.setSize(currentLevel.getWidth() + 14, currentLevel.getHeight() + 37);
	}

	void switchLevel(String newLevel, int index) {
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

	void levelReset() {
		loadLevelByIndex(currentLevel.getIndex());
	}

	void loseLife() {
		hero.loseLife();
		lives--;
		levelReset();
		System.out.println("You Died! Lives left: " + lives);
		if (lives > 0) {
			sceneManager.switchScene(new ResetUpdater(sceneManager, sceneManager.getCurrentScene()));
		} else {
			sceneManager.switchScene(new LossUpdater(sceneManager, currentLevel));
		}
	}

	int getLevelCount() {
		return levelFiles.length;
	}

	void incrementScore(int score) {
		this.score += score;
	}

	void nextLevel() {
		loadLevelByIndex(currentLevel.getIndex() + 1);
		sceneManager.switchScene(new TransitionUpdater(sceneManager, generator));
	}

	// For testing purposes
	SceneManager getSceneManager() {
		return sceneManager;
	}

}
