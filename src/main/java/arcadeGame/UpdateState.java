package arcadeGame;

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

  public void setScene(String scene) {
    gameComponent.switchScene(scene);
  }

  public int getLevelCount() {
    return gameComponent.getLevelCount();
  }
}
