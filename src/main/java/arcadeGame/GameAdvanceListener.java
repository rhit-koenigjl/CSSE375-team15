package arcadeGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameAdvanceListener implements ActionListener {
	private GameComponent gameComponent;

	GameAdvanceListener(GameComponent gameComponent) {
		this.gameComponent = gameComponent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		advanceOneTick();
	}

	void advanceOneTick() {
		this.gameComponent.updateState();
		this.gameComponent.drawScreen();
	}

}
