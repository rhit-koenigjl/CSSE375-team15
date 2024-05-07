package arcadeGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import arcadeGame.gameComponents.Enemy;
import arcadeGame.gameComponents.Player;
import arcadeGame.gameComponents.Wall;

class TestActorsAndTilesForBasicData {

    @Test
    void testBasicTile_testConstructor_expectGettersToGetExpectedValues() {
        Wall wall = new Wall(1, 2, 3, 4);

        assertEquals(1, wall.getX());
        assertEquals(2, wall.getY());
        assertEquals(3, wall.getWidth());
        assertEquals(4, wall.getHeight());
    }

    @Test
    void testBasicActor_testConstructor_expectGettersToGetExpectedValues() {
        Enemy enemy = new Enemy(1, 2, 3, 4);

        assertEquals(1, enemy.getX());
        assertEquals(2, enemy.getY());
        assertEquals(3, enemy.getWidth());
        assertEquals(4, enemy.getHeight());
    }

    @Test
    void testPlayerActor_testAfterMovement_expectValuesMovedFromInitialPosition() {
        Player p = new Player(0, 0, 10, 10);

        assertEquals(0, p.getX());
        assertEquals(0, p.getY());
        assertEquals(10, p.getWidth());
        assertEquals(10, p.getHeight());

        p.setVx(10);
        p.setVy(7);
        p.update(new HashMap<>(), new ArrayList<>(),
                new ArrayList<>());

        assertEquals(7.5, p.getX());
        assertEquals(7, p.getY());
        assertEquals(10, p.getWidth());
        assertEquals(10, p.getHeight());
    }

    @Test
    void testTwoObjectCollisions_haveNoCollision_expectNoCollision() {
        Player p = new Player(100, 100, 50, 50);
        Wall t = new Wall(200, 200, 40, 40);

        assertFalse(p.collidesWith(t));
    }

    @Test
    void testTwoObjectCollisions_hasCollision_expectCollision() {
        Player p = new Player(0, 0, 50, 50);
        Wall t = new Wall(10, 30, 40, 40);

        assertTrue(p.collidesWith(t));
    }

}
