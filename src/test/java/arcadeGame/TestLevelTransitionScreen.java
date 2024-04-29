package arcadeGame;

import static org.junit.Assert.assertEquals;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JFrame;
import org.easymock.EasyMock;
import org.junit.Test;

public class TestLevelTransitionScreen {

  @Test
  public void testTransitionScreenDisplayedOnNextLevel() {
    JFrame frame = EasyMock.createMock(JFrame.class);
    frame.setSize(EasyMock.anyInt(), EasyMock.anyInt());
    frame.repaint();

    SceneManager sceneManager = EasyMock.createMock(SceneManager.class);
    EasyMock.expect(sceneManager.getCurrentScene())
        .andReturn(EasyMock.partialMockBuilder(MenuUpdater.class).createMock());
    sceneManager.runScene();
    sceneManager.setLevel(EasyMock.anyObject(Level.class));
    EasyMock.expect(sceneManager.getCurrentScene())
        .andReturn(EasyMock.partialMockBuilder(GameUpdater.class).createMock());
    sceneManager.switchScene(EasyMock.anyObject(TransitionUpdater.class));
    EasyMock.expect(sceneManager.getCurrentScene())
        .andReturn(EasyMock.partialMockBuilder(TransitionUpdater.class).createMock()).anyTimes();
    EasyMock.replay(frame, sceneManager);

    GameComponent gameComponent = new GameComponent(frame, new MouseListener());
    gameComponent.setSceneManager(sceneManager);

    SceneUpdater menu = sceneManager.getCurrentScene();
    assertEquals("menu", menu.getSceneName());
    sceneManager.runScene();
    SceneUpdater game = sceneManager.getCurrentScene();
    assertEquals("game", game.getSceneName());

    gameComponent.nextLevel();
    SceneUpdater transition = sceneManager.getCurrentScene();
    assertEquals("transition", transition.getSceneName());
    EasyMock.verify(frame, sceneManager);
  }

  @Test
  public void testTransitionMessageGeneration() {
    MessageGenerator generator = EasyMock.createMock(MessageGenerator.class);
    EasyMock.expect(generator.generateEncouragingMessage()).andReturn("This is a test message.");

    FontMetrics metrics = EasyMock.niceMock(FontMetrics.class);
    EasyMock.expect(metrics.getHeight()).andReturn(1);

    Graphics2D g = EasyMock.niceMock(Graphics2D.class);
    EasyMock.expect(g.getFontMetrics(new Font("Monospaced", Font.BOLD, 28))).andReturn(metrics);
    EasyMock.expect(g.getClipBounds()).andReturn(new Rectangle()).anyTimes();

    SceneManager sceneManager = EasyMock.niceMock(SceneManager.class);
    EasyMock.expect(sceneManager.getCurrentScene())
        .andReturn(EasyMock.createMock(SceneUpdater.class));
    EasyMock.replay(generator, metrics, g, sceneManager);

    SceneUpdater transition = new TransitionUpdater(sceneManager, generator);
    transition.drawScene(g);
    ((TransitionUpdater) transition).setTimer(400);
    transition.updateScene();
    EasyMock.verify(generator, metrics, g, sceneManager);
  }

}
