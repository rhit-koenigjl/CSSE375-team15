package arcadeGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

public class TestGameComponentRefactor {
  @Test
  public void testLoadLevel_withValidFilePath_returnsLayoutObject() throws IOException {
    String filePath = "levels/level00";
    List<String> expected = new ArrayList<String>();
    Scanner sc = new Scanner(ClassLoader.getSystemClassLoader().getResource(filePath).openStream());
    sc.nextLine();
    while (sc.hasNextLine()) {
      expected.add(sc.nextLine());
    }
    sc.close();

    JFrame frame = EasyMock.createMock(JFrame.class);
    GameComponent component = new GameComponent(frame);
    List<String> actual = component.loadLevel(filePath);
    assertEquals(expected, actual);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testGenerateLevel_withValidFilePath_returnsGameObjects() {
    String filePath = "levels/level00";

    JFrame frame = EasyMock.createMock(JFrame.class);
    GameComponent component = new GameComponent(frame);
    Object[] gameObjects = component.generateLevel(filePath);

    assertEquals(3, gameObjects.length);

    List<Tile> tiles = (List<Tile>) gameObjects[0];
    Player player = (Player) gameObjects[1];
    List<Enemy> enemies = (List<Enemy>) gameObjects[2];

    assertEquals(92, tiles.size());
    assertEquals(410, player.getX());
    assertEquals(6, enemies.size());
  }

  @Test
  public void testGenerateLevel_withValidFilePath_callsLoadLevel() throws IOException {
    String filePath = "levels/level00";

    JFrame frame = EasyMock.createMock(JFrame.class);
    GameComponent component = EasyMock.partialMockBuilder(GameComponent.class)
        .addMockedMethod("loadLevel").withConstructor(frame).createMock();
    EasyMock.expect(component.loadLevel(filePath)).andReturn(new ArrayList<String>() {{ add(""); }});

    EasyMock.replay(component);
    component.generateLevel(filePath);
    EasyMock.verify(component);
  }

  @Test
  public void testSwitchLevel_withValidFilePath_callsGenerateLevel() {
    String filePath = "levels/level00";

    JFrame frame = EasyMock.createMock(JFrame.class);
    GameComponent component = EasyMock.partialMockBuilder(GameComponent.class)
        .addMockedMethod("generateLevel").withConstructor(frame).createMock();
    EasyMock.expect(component.generateLevel(filePath)).andReturn(new Object[0]);

    EasyMock.replay(component);
    component.switchLevel(filePath);
    EasyMock.verify(component);
  }
}
