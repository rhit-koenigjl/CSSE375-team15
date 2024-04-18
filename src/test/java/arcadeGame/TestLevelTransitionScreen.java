package arcadeGame;

import static org.junit.Assert.assertEquals;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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

  @Test
  public void testTransitionMessageGeneration() {
    MessageGenerator generator = EasyMock.createMock(MessageGenerator.class);
    EasyMock.expect(generator.generateEncouragingMessage()).andReturn("This is a test message.");
    generator.requestMessage();

    FontMetrics metrics = EasyMock.niceMock(FontMetrics.class);
    EasyMock.expect(metrics.getHeight()).andReturn(1);

    Graphics2D g = EasyMock.niceMock(Graphics2D.class);
    EasyMock.expect(g.getFontMetrics(new Font("Monospaced", Font.BOLD, 28))).andReturn(metrics);
    EasyMock.expect(g.getClipBounds()).andReturn(new Rectangle());

    SceneManager sceneManager = EasyMock.niceMock(SceneManager.class);
    EasyMock.expect(sceneManager.getCurrentScene())
        .andReturn(EasyMock.createMock(SceneUpdater.class));
    EasyMock.replay(generator, metrics, g, sceneManager);

    SceneUpdater transition = new TransitionUpdater(sceneManager, generator);
    transition.drawScene(g, 0);
    ((TransitionUpdater) transition).setTimer(400);
    transition.updateScene();
    EasyMock.verify(generator, metrics, g, sceneManager);
  }

}
