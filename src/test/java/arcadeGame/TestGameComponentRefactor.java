package arcadeGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

public class TestGameComponentRefactor {
  @Test
  public void testLoadLevel_withValidFilePath_returnsLayoutObject() throws IOException {
    String filePath = "levels/testLevels/test_level_0.json";

    LevelLoader loader = new LevelLoader(filePath);
    loader.loadLevel();
    assertEquals(15, loader.getTiles().size());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testGenerateLevel_withValidFilePath_returnsGameObjects() {
    String filePath = "levels/testLevels/test_level_0.json";

    Player hero = EasyMock.createMock(Player.class);
    Level level = new Level(filePath, 0, hero);
    Object[] gameObjects = level.generateLevel();

    assertEquals(3, gameObjects.length);

    List<Tile> tiles = (List<Tile>) gameObjects[0];
    Player player = (Player) gameObjects[1];
    List<Enemy> enemies = (List<Enemy>) gameObjects[2];

    assertEquals(15, tiles.size());
    assertEquals(50, player.getX());
    assertEquals(0, enemies.size());
  }
}
