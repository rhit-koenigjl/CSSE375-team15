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
    private static final String GAME_TITLE = "Indiana Ghost";
    private static final Color LIGHT_BLUE = new Color(206, 214, 237);

    private GameComponent component;
    private JFrame gameFrame;
    private MouseListener mouseListener;

    private void runApp() {
        createFrame();
        createGameComponent();
        addListeners();
        gameFrame.setVisible(true);
    }
    
    private void createFrame() {
        gameFrame = new JFrame(GAME_TITLE);
        gameFrame.setSize(MENU_WIDTH, MENU_HEIGHT);
        this.mouseListener = new MouseListener(gameFrame);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
    }

    private void createGameComponent() {
        component = new GameComponent(gameFrame, mouseListener);
        component.loadLevelByIndex(0);
        gameFrame.add(component, BorderLayout.CENTER);
        gameFrame.getContentPane().setBackground(LIGHT_BLUE);
    }

    private void addListeners() {
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
    }

    public static void main(String[] args) {
        MainApp mainApp = new MainApp();
        mainApp.runApp();
    }

}
