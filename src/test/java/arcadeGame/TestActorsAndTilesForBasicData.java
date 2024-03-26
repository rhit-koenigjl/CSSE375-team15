package arcadeGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

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

    @Test
    public void testPlayerActor_testAfterMovement_expectValuesMovedFromInitialPosition() {
        Player p = new Player(0, 0, 10, 10);

        assertEquals(0, p.getX());
        assertEquals(0, p.getY());
        assertEquals(10, p.getWidth());
        assertEquals(10, p.getHeight());

        p.setVx(10);
        p.setVy(7);
        p.update(new HashMap<Integer, Boolean>(), new ArrayList<Tile>());

        assertEquals(7.5, p.getX()); // vx will be minimized by 1/4 since the key isn't being held
        assertEquals(7.5, p.getX()); // vy will be increased by 0.5 because of gravity
        assertEquals(10, p.getWidth());
        assertEquals(10, p.getHeight());
    }


    @Test
    public void testTwoObjectCollisions_haveNoCollision_expectNoCollision() {
        Player p = new Player(100, 100, 50, 50);
        Wall t = new Wall(200, 200, 40, 40);

        assertFalse(p.checkCollision(t));
    }

    @Test
    public void testTwoObjectCollisions_hasCollision_expectCollision() {
        Player p = new Player(0, 0, 50, 50);
        Wall t = new Wall(10, 30, 40, 40);

        assertTrue(p.checkCollision(t));
    }
}