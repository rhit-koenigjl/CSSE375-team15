package arcadeGame;

public class UpdateState {
  private GameComponent gameComponent;

  UpdateState(GameComponent gameComponent) {
    this.gameComponent = gameComponent;
  }

  void incrementScore(int score) {
    gameComponent.incrementScore(score);
  }

  int getScore() {
    return gameComponent.getScore();
  }

  void heroLostLife() {
    gameComponent.loseLife();
  }

  void setNextLevel(int level) {
    gameComponent.loadLevelByIndex(level);
  }

  int getLevelCount() {
    return gameComponent.getLevelCount();
  }

  void transitionNextLevel() {
    gameComponent.nextLevel();
  }

}
