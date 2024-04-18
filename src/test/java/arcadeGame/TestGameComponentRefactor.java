package arcadeGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

public class TestGameComponentRefactor {
  private static final String LEVEL_DIRECTORY = "levels/testLevels/";

  @Test
  public void testLoadLevel_withValidFilePath_returnsLayoutObject() throws IOException, URISyntaxException {
    String filePath = Path.of(ClassLoader.getSystemClassLoader()
        .getResource(LEVEL_DIRECTORY + "test_level_0.json").toURI()).toFile().getPath();

    LevelLoader loader = new LevelLoader(filePath);
    loader.loadLevel();
    assertEquals(15, loader.getTiles().size());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testGenerateLevel_withValidFilePath_returnsGameObjects() throws URISyntaxException {
    String filePath = Path.of(ClassLoader.getSystemClassLoader()
        .getResource(LEVEL_DIRECTORY + "test_level_0.json").toURI()).toFile().getPath();

    Player hero = EasyMock.createMock(Player.class);
    Level level = new Level(filePath, 0, hero);
    Object[] gameObjects = level.generateLevel();

    assertEquals(3, gameObjects.length);

    List<Tile> tiles = (List<Tile>) gameObjects[0];
    Player player = (Player) gameObjects[1];
    List<Enemy> enemies = (List<Enemy>) gameObjects[2];

    assertEquals(15, tiles.size());
    assertEquals(60, player.getX());
    assertEquals(0, enemies.size());
  }
}
