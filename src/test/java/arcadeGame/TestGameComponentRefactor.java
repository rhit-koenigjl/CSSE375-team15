package arcadeGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import arcadeGame.gameComponents.Enemy;
import arcadeGame.gameComponents.Player;
import arcadeGame.gameComponents.Tile;
import arcadeGame.levelManagers.Level;
import arcadeGame.levelManagers.LevelLoader;

class TestGameComponentRefactor {
    private static final String LEVEL_DIRECTORY = "levels/testLevels/";

    @Test
    void testLoadLevel_withValidFilePath_returnsLayoutObject() {
        String filePath = LEVEL_DIRECTORY + "test_level_0.json";

        LevelLoader loader = new LevelLoader(filePath);
        loader.loadLevel();
        assertEquals(15, loader.getTiles().size());
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGenerateLevel_withValidFilePath_returnsGameObjects() {
        String filePath = (LEVEL_DIRECTORY + "test_level_0.json");

        Player hero = EasyMock.createMock(Player.class);
        Level level = new Level(filePath, 0, hero);
        Object[] gameObjects = level.generateLevel();

        assertEquals(3, gameObjects.length);

        List<Tile> tiles = (List<Tile>) gameObjects[0];
        Player player = (Player) gameObjects[1];
        List<Enemy> enemies = (List<Enemy>) gameObjects[2];

        assertEquals(15, tiles.size());
        assertEquals(55, player.getX());
        assertEquals(0, enemies.size());
    }

}
