package arcadeGame;

import static org.junit.Assert.assertEquals;
import java.util.Map;
import javax.swing.JFrame;
import org.easymock.EasyMock;
import org.junit.Test;

public class TestLevelTransitionScreen {

  @Test
  public void testTransitionScreenDisplayedOnNextLevel() {
    JFrame frame = EasyMock.createMock(JFrame.class);
    GameComponent gameComponent = new GameComponent(frame);
    SceneManager sceneManager = gameComponent.getSceneManager();

    SceneUpdater menu = sceneManager.getCurrentScene();
    assertEquals("menu", menu.getSceneName());
    ((MenuUpdater) menu).setKeys(Map.of(32, true));
    sceneManager.runScene();
    SceneUpdater game = sceneManager.getCurrentScene();
    assertEquals("game", game.getSceneName());

    gameComponent.nextLevel();
    SceneUpdater transition = sceneManager.getCurrentScene();
    assertEquals("transition", transition.getSceneName());
  }

}
