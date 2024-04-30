package arcadeGame.gameHelpers;

import arcadeGame.GameComponent;

public class UpdateState {
    private GameComponent gameComponent;

    public UpdateState(GameComponent gameComponent) {
        this.gameComponent = gameComponent;
    }

    public void incrementScore(int score) {
        gameComponent.incrementScore(score);
    }

    public void heroLostLife() {
        gameComponent.loseLife();
    }

    public void setNextLevel(int level) {
        gameComponent.loadLevelByIndex(level);
    }

    public int getLevelCount() {
        return gameComponent.getLevelCount();
    }

    public void transitionNextLevel() {
        gameComponent.nextLevel();
    }

    public void handleWinGame() {
        gameComponent.winGame();
    }

    public void resizeLevel() {
        gameComponent.resize();
    }

}
