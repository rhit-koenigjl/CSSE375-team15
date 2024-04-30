package arcadeGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import arcadeGame.stateComponents.GameAdvanceListener;
import arcadeGame.stateComponents.MouseListener;

class MainApp {
    private static final int DELAY = 10;
    private static final int MENU_WIDTH = 900;
    private static final int MENU_HEIGHT = 550;

    private GameComponent component;
    private JFrame gameFrame;
    private MouseListener mouseListener;

    private void runApp() {
        gameFrame = new JFrame("Indiana Ghost");
        gameFrame.setSize(MENU_WIDTH, MENU_HEIGHT);
        this.mouseListener = new MouseListener(gameFrame);

        component = new GameComponent(gameFrame, mouseListener);
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
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
    }

    public static void main(String[] args) {
        MainApp mainApp = new MainApp();
        mainApp.runApp();
    }

}
