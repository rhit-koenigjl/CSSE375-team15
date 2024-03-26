package arcadeGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

    Player hero = EasyMock.createMock(Player.class);
    Level level = new Level(new File(filePath), 0, hero);
    List<String> actual = level.loadLevel();
    assertEquals(expected, actual);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testGenerateLevel_withValidFilePath_returnsGameObjects() {
    String filePath = "levels/level00";

    Player hero =
        EasyMock.partialMockBuilder(Player.class).addMockedMethod("getNumberOfLives").createMock();
    Level level = new Level(new File(filePath), 0, hero);
    Object[] gameObjects = level.generateLevel();

    assertEquals(3, gameObjects.length);

    List<Tile> tiles = (List<Tile>) gameObjects[0];
    Player player = (Player) gameObjects[1];
    List<Enemy> enemies = (List<Enemy>) gameObjects[2];

    assertEquals(92, tiles.size());
    assertEquals(410, player.getX());
    assertEquals(6, enemies.size());
  }
}
