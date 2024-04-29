package arcadeGame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestLevelLoader {

    @Test
    public void testLevelLoader_testConstructor_expectInitialValues() {
        LevelLoader testLoader = new LevelLoader("");

        assertEquals(0, testLoader.getEnemies().size());
        assertEquals(0, testLoader.getTiles().size());
        assertEquals(null, testLoader.getPlayer());

        assertEquals(-1, testLoader.getSize());
        assertEquals(1, testLoader.getWidth());
        assertEquals(1, testLoader.getHeight());
    }

    @Test
    public void testLevelLoader_createLevel_expectTenByFive() {
        LevelLoader testLoader =
                new LevelLoader("src\\main\\resources\\levels\\testLevels\\test_level_3.json");
        testLoader.loadLevel();

        assertEquals(500, testLoader.getWidth());
        assertEquals(250, testLoader.getHeight());
        assertEquals(50, testLoader.getSize());
        assertEquals("b-11|none-1|S#D-6|c-1|b-2|P-1|none-6|c-1|b-2|none-1|S#U-6|c-1|b-11",
                testLoader.getDataString());
    }

    @Test
    public void testLevelLoader_createLevel_expectFiveBlocksFiveEnemies() {
        LevelLoader testLoader =
                new LevelLoader("src\\main\\resources\\levels\\testLevels\\test55.json");
        testLoader.loadLevel();

        assertEquals(5, testLoader.getTiles().size());
        assertEquals(5, testLoader.getEnemies().size());
    }

    @Test
    public void testLevelLoader_createLevel_expectSpikes() {
        LevelLoader testLoader =
                new LevelLoader("src\\main\\resources\\levels\\testLevels\\spike_test.json");
        assertEquals(0, testLoader.getTiles().size());
        testLoader.loadLevel();
        assertEquals(7, testLoader.getTiles().size());
    }

    @Test
    public void testLevelLoader_createLevel_expectHunterSeekers() {
        LevelLoader testLoader = new LevelLoader(
                "src\\main\\resources\\levels\\testLevels\\hunter_seeker_test.json");
        assertEquals(0, testLoader.getEnemies().size());
        testLoader.loadLevel();
        assertEquals(8, testLoader.getEnemies().size());
    }

    @Test
    public void testLevelLoader_createLevel_expectCoins() {
        LevelLoader testLoader =
                new LevelLoader("src\\main\\resources\\levels\\testLevels\\coin_test.json");
        assertEquals(0, testLoader.getTiles().size());
        testLoader.loadLevel();
        assertEquals(15, testLoader.getTiles().size());
    }

    @Test
    public void testLevelLoader_createLevel_expectDesiredPlayer() {
        LevelLoader testLoader =
                new LevelLoader("src\\main\\resources\\levels\\testLevels\\player_test.json");
        assertEquals(null, testLoader.getPlayer());
        testLoader.loadLevel();
        assertEquals(73, (int) testLoader.getPlayer().getX());
        assertEquals(283, (int) testLoader.getPlayer().getY());
        assertEquals(28, (int) testLoader.getPlayer().getWidth());
        assertEquals(28, (int) testLoader.getPlayer().getHeight());
    }

}
