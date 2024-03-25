package arcadeGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestActorsAndTilesForBasicData {
    @Test
    public void testBasicTile_testConstructor_expectGettersToGetExpectedValues() {
        Wall wall = new Wall(1, 2, 3, 4);

        assertEquals(1, wall.getX());
        assertEquals(2, wall.getY());
        assertEquals(3, wall.getWidth());
        assertEquals(4, wall.getHeight());
    }

    @Test
    public void testBasicActor_testConstructor_expectGettersToGetExpectedValues() {
        Enemy enemy = new Enemy(1, 2, 3, 4);

        assertEquals(1, enemy.getX());
        assertEquals(2, enemy.getY());
        assertEquals(3, enemy.getWidth());
        assertEquals(4, enemy.getHeight());
    }
}
