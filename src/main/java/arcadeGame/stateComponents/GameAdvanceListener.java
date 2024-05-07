package arcadeGame.stateComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import arcadeGame.GameComponent;

public class GameAdvanceListener implements ActionListener {
    private final GameComponent gameComponent;

    public GameAdvanceListener(GameComponent gameComponent) {
        this.gameComponent = gameComponent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        advanceOneTick();
    }

    private void advanceOneTick() {
        this.gameComponent.updateState();
        this.gameComponent.drawScreen();
    }

}
