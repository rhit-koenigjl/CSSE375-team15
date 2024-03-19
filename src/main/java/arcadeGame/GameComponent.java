package arcadeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.*;


public class GameComponent extends JComponent {
	private String scene = "menu";
	private int transitionTimer = 0;
	private int score = 0;

	// Fields for level management and creation
	String levelFiles[] = {"levels/level00", "levels/level01", "levels/level02", "levels/level03",
			"levels/level04", "levels/level05", "levels/level06", "levels/level07", "levels/level08",
			"levels/level09", "levels/level10", "levels/level11", "levels/level12",};
	private int levelIndex = 0;
	private String level;
	private ArrayList<String> levelLayout = new ArrayList<String>();
	private int levelHeight;
	private int levelWidth;

	// fields pertaining the current level
	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private int numBombs = 0;

	// User fields
	private Player hero = new Player(0, 0, 0, 0);
	private HashMap<Integer, Boolean> keys = new HashMap<Integer, Boolean>();

	// the containing frame
	private JFrame frame;

	/**
	 * Ensures the creation of the Game Component and initializes the first level
	 * 
	 * @param frame the frame that the game is taking place in, used for resizing to fit each level.
	 */
	public GameComponent(JFrame frame) {
		this.frame = frame;
		generateLevel(levelFiles[levelIndex]);
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
				boolean heroHurt = false;
				// code that creates a level at the game's start
				if (tiles.size() == 0) {
					generateLevel(level);
				}

				// handling of the hero
				hero.update(keys, tiles);

				if (hero.didCollideWithSpikes) {
					heroHurt = true;
				}

				// handles the updating and removing of enemies
				ArrayList<Enemy> enemiesRemove = new ArrayList<Enemy>();
				ArrayList<Enemy> enemiesToAdd = new ArrayList<Enemy>();
				for (Enemy enemy : enemies) {
					int collisionType = hero.handleCollisions(enemy);
					if (collisionType == 1) {
						heroHurt = true;
					}
					if (collisionType == 2) {
						enemiesRemove.add(enemy);
					}
					enemy.update(tiles);
					if (enemy.getAdding()) {
						enemy.setAdding(false);
						enemiesToAdd.add(enemy.returnNew());
					}
					if (enemy.didCollideWithSpikes) {
						enemiesRemove.add(enemy);
					}
				}
				for (Enemy e : enemiesRemove) {
					enemies.remove(e);
					score += 50;
				}
				for (Enemy e : enemiesToAdd) {
					enemies.add(e);
				}

				// handles the updating of the tiles and the removal of bombs
				ArrayList<Tile> toRemove = new ArrayList<Tile>();
				for (Tile t : tiles) {
					if (t.shouldRemove()) {
						toRemove.add(t);
					}
				}
				for (Tile t : toRemove) {
					tiles.remove(t);
					score += 25;
					numBombs--;
				}

				// handles the switching of a level when all bombs are collected,
				// and moves you to the win screen if there are no more levels
				if (numBombs == 0) {
					score += 100;
					if (levelIndex == levelFiles.length - 1) {
						// switch to endscreen
						switchScene("win");
					} else {
						levelIndex++;
						switchLevel(levelFiles[levelIndex]);
					}
				}

				// manual keystroke level change code
				if (keys.getOrDefault(85, false) && levelIndex < levelFiles.length - 1) {
					levelIndex++;
					switchLevel(levelFiles[levelIndex]);
					keys.put(85, false);
				}
				if (keys.getOrDefault(68, false) && levelIndex > 0) {
					levelIndex--;
					switchLevel(levelFiles[levelIndex]);
					keys.put(68, false);
				}
				if (keys.getOrDefault(27, false)) {
					switchScene("pause");
					keys.remove(27);
				}


				// switch to screen if you lose
				if (!hero.checkLives()) {
					switchScene("loss");
				}
				if (heroHurt) {
					heroLosedLife();
				}
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
	 * @param inputFilename: the text file the level is pulling data from
	 * @return an ArrayList<String> of text that represents a level
	 */
	public ArrayList<String> loadLevel(String inputFilename) {
		System.out.println(inputFilename);
		level = inputFilename;
		Scanner scanner = null;
		try {
			scanner =
					new Scanner(ClassLoader.getSystemClassLoader().getResource(inputFilename).openStream());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		} // end try-catch

		String title = scanner.nextLine();
		System.out.println("Title: " + title);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			System.out.println(line);
			levelLayout.add(line);
		} // end while

		scanner.close();
		return levelLayout;
	} // readFile

	/**
	 * ensures: the creation of new levels from a string filename
	 * 
	 * @param inputFilename: the text file the level is taking data from
	 */
	public void generateLevel(String inputFilename) {
		tiles.clear();
		enemies.clear();
		ArrayList<int[]> hunterSeekersToAdd = new ArrayList<int[]>(); // HunterSeekers to be added after
																																	// hero added
		ArrayList<String> l = loadLevel(inputFilename);
		for (int y = 0; y < l.size(); y++) { // reads line number
			for (int x = 0; x < l.get(y).length(); x++) { // reads line length
				char blockChar = l.get(y).charAt(x);
				switch (blockChar) {
					case '-':
						tiles.add(new Platform(x * 50, y * 50, 50, 20));
						// System.out.println("Tile printed at x: " + x*50 + " y: " + y*50);
						break;
					case '|':
						tiles.add(new Wall(x * 50, y * 50, 50, 50));
						break;
					case '^':
						tiles.add(new Spike(x * 50, y * 50, 50));
						break;
					case 'P':
						hero.setX(x * 50 + 10);
						hero.setY(y * 50);
						hero.setWidth(30);
						hero.setHeight(40);
						hero.clearMovementSpeed();
						// System.out.println("Hero");
						break;
					case 'E':
						Enemy enemy = new Enemy(x * 50 + 10, y * 50, 40, 40);
						enemies.add(enemy);
						// System.out.println("Enemy Created");
						break;
					case 'V':
						enemy = new Enemy(x * 50 + 10, y * 50, 40, 40, 0, 5);
						enemies.add(enemy);
						// System.out.println("Enemy Created");
						break;
					case 'H':
						enemy = new Enemy(x * 50 + 10, y * 50, 40, 40, 5, 0);
						enemies.add(enemy);
						// System.out.println("Enemy Created");
						break;
					case 'S':
						int[] addedHunterSeeker = {x * 50 + 10, y * 50, 40, 40};
						hunterSeekersToAdd.add(addedHunterSeeker); // adds HunterSeeker info to a ArrayList to
																												// be created after everything else
						break;
					case 'B':
						tiles.add(new Bomb(x * 50 + 10, y * 50, 30, 50));
						numBombs++;
						break;
					case 'M':
						tiles.add(new BouncePad(x * 50, y * 50 + 30, 50, 20));
						break;
					case 'C':
						enemies.add(new EnemySpawner(x * 50 + 10, y * 50 + 10, 30, 30, enemies, hero, 0));
						break;
					case 'D':
						enemies.add(new EnemySpawner(x * 50 + 10, y * 50 + 10, 30, 30, enemies, hero, 3));
						break;
				}
			}
		}
		for (int[] HSInfo : hunterSeekersToAdd) {
			// HunterSeeker needs a hero to track, so it cannot be created before the hero
			// Therefore, the HunterSeekers are added after everything else to garuntee the hero has been
			// created beforehand
			HunterSeeker h = new HunterSeeker(HSInfo[0], HSInfo[1], HSInfo[2], HSInfo[3], hero);
			enemies.add(h);
		}
		levelHeight = levelLayout.size();
		levelWidth = levelLayout.get(0).length();
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
		int yMiddle = (levelHeight * 50 + 37) / 2;
		String shownString;
		switch (scene) {
			case "game":
				for (Tile t : tiles) {
					t.display(g2);
				}
				for (Enemy e : enemies) {
					e.drawActor(g2);
				}
				hero.drawActor(g2);
				g2.setColor(Color.blue);
				g2.drawString("Lives: " + hero.getNumberOfLives() + "      Level: " + levelIndex
						+ "         Score: " + score, 25, 30);
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
				shownString = "You ran out of lives on level " + levelIndex + ", Your score was: " + score;
				g2.drawString(shownString, xMiddle, yMiddle);
				break;
			case "reset":
				shownString = "You got hit! Restarting the level in " + (350 - transitionTimer) / 100;
				g2.drawString(shownString, xMiddle, yMiddle);
				break;
			case "pause":
				Color pauseColor = new Color(0, 0, 0, 75);
				for (Tile t : tiles) {
					t.display(g2);
				}
				for (Enemy e : enemies) {
					e.drawActor(g2);
				}
				hero.drawActor(g2);
				g2.setColor(pauseColor);
				g2.fillRect(0, 0, levelWidth * 50 + 14, levelHeight * 50 + 37);
				g2.setColor(previousColor);
				g2.setColor(Color.blue);
				g2.drawString("Lives: " + hero.getNumberOfLives() + "      Level: " + levelIndex, 25, 30);
				g2.setColor(previousColor);
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
		frame.setSize(levelWidth * 50 + 14, levelHeight * 50 + 37);
	}

	/**
	 * ensures: the switching of levels
	 * 
	 * @param newLevel The level to go to.
	 */
	public void switchLevel(String newLevel) {
		numBombs = 0;
		enemies.clear();
		tiles.clear();
		levelLayout.clear();
		generateLevel(newLevel);
		sizeFrame(this.frame);
		frame.repaint();
	}

	public void levelReset() {
		switchLevel(level);
		System.out.println("Level Reset");
	}

	public void heroLosedLife() {
		hero.loseLife();
		levelReset();
		System.out.println("You Died! Lives left: " + hero.getNumberOfLives());
		if (hero.checkLives()) {
			switchScene("reset");
		}
	}

}
