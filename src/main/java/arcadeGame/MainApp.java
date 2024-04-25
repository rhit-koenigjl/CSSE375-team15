package arcadeGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class MainApp {
	private static final int DELAY = 10;
	private static final int MENU_WIDTH = 900;
	private static final int MENU_HEIGHT = 550;

	private GameComponent component;
	private JFrame gameFrame;

	private void runApp() {
		gameFrame = new JFrame("Bomber Jack");
		gameFrame.setSize(MENU_WIDTH, MENU_HEIGHT);

		component = new GameComponent(gameFrame);
		component.loadLevelByIndex(0);
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
		// component.sizeFrame(gameFrame);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setResizable(false);
	}

	public static void main(String[] args) {
		MainApp mainApp = new MainApp();
		mainApp.runApp();
	}

}
