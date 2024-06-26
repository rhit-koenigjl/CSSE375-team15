package arcadeGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import arcadeGame.gameComponents.Enemy;
import arcadeGame.gameComponents.EnemyGenerator;
import arcadeGame.gameComponents.EnemySpawner;
import arcadeGame.gameComponents.EnemySpawnerGenerator;
import arcadeGame.gameComponents.HunterSeeker;
import arcadeGame.gameComponents.HunterSeekerGenerator;
import arcadeGame.gameComponents.Player;
import arcadeGame.gameComponents.RecursiveEnemySpawnerGenerator;

class TestEnemySpawnerCreation {
    private static final double STARTING_POSITION = 0;
    private static final int STARTING_VELOCITY = 0;
    private static final int ACTOR_SIZE = 10;
    private static final List<Enemy> ENEMIES = new ArrayList<>();
    private static final Player PLAYER = EasyMock.createMock(Player.class);

    @BeforeEach
    void setUp() {
        EasyMock.expect(PLAYER.getX()).andReturn(STARTING_POSITION);
        EasyMock.expect(PLAYER.getY()).andReturn(STARTING_POSITION);
        EasyMock.replay(PLAYER);
    }

    @AfterEach
    void tearDown() {
        EasyMock.verify(PLAYER);
        EasyMock.reset(PLAYER);
    }

    private void matchesEnemyType(Class<?> enemyType, EnemySpawner enemySpawner) {
        Enemy enemy = enemySpawner.returnNew();
        assertEquals(STARTING_POSITION, enemy.getX());
        assertEquals(STARTING_POSITION, enemy.getY());
        assertEquals(enemyType, enemy.getClass());
    }

    @Test
    void testEnemyGenerator_createsNewEnemy() {
        EnemySpawner enemySpawner = new EnemyGenerator(STARTING_POSITION, STARTING_POSITION,
                ACTOR_SIZE, ACTOR_SIZE, STARTING_VELOCITY, STARTING_VELOCITY, ENEMIES, PLAYER);
        matchesEnemyType(Enemy.class, enemySpawner);
    }

    @Test
    void testEnemySpawnerGenerator_createsNewEnemyGenerator() {
        EnemySpawner enemySpawner = new EnemySpawnerGenerator(STARTING_POSITION, STARTING_POSITION,
                ACTOR_SIZE, ACTOR_SIZE, STARTING_VELOCITY, STARTING_VELOCITY, ENEMIES, PLAYER);
        matchesEnemyType(EnemyGenerator.class, enemySpawner);
    }

    @Test
    void testHunterSeekerGenerator_createsNewHunterSeeker() {
        EnemySpawner enemySpawner = new HunterSeekerGenerator(STARTING_POSITION, STARTING_POSITION,
                ACTOR_SIZE, ACTOR_SIZE, STARTING_VELOCITY, STARTING_VELOCITY, ENEMIES, PLAYER);
        matchesEnemyType(HunterSeeker.class, enemySpawner);
    }

    @Test
    void testRecursiveEnemySpawnerGenerator_createsNewRecursiveEnemySpawnerGenerator() {
        EnemySpawner enemySpawner =
                new RecursiveEnemySpawnerGenerator(STARTING_POSITION, STARTING_POSITION, ACTOR_SIZE,
                        ACTOR_SIZE, STARTING_VELOCITY, STARTING_VELOCITY, ENEMIES, PLAYER);
        matchesEnemyType(RecursiveEnemySpawnerGenerator.class, enemySpawner);
    }

}
