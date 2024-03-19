package arcadeGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Class: MainApp
 * 
 * @author Team 103 | JL Koenig & Tommy Welch <br>
 *         Purpose: Top level class for CSSE220 Project containing main method <br>
 *         Restrictions: None
 */
public class MainApp {
	public static final int DELAY = 10;
	private GameComponent component;
	private JFrame gameFrame;

	/**
	 * ensures: runs the app
	 */
	private void runApp() {
		gameFrame = new JFrame("Bomber Jack");
		gameFrame.setSize(800, 600);

		component = new GameComponent(gameFrame);
		gameFrame.add(component, BorderLayout.CENTER);
		gameFrame.getContentPane().setBackground(new Color(206, 214, 237));
		GameAdvanceListener advanceListener = new GameAdvanceListener(component);

		Timer timer = new Timer(DELAY, advanceListener);
		timer.start();

		gameFrame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				return;
			}

			@Override
			public void keyPressed(KeyEvent e) {
				component.handleKey(e.getKeyCode(), true);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				component.handleKey(e.getKeyCode(), false);
			}

		});

		gameFrame.setVisible(true);
		component.sizeFrame(gameFrame);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setResizable(false);
	} // runApp

	/**
	 * ensures: creates and runs the app
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {
		MainApp mainApp = new MainApp();
		mainApp.runApp();

	} // main app

}
