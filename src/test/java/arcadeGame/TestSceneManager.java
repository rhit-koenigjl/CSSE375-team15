package arcadeGame;

import static org.junit.Assert.assertEquals;

import java.awt.Graphics2D;

import org.junit.Test;

public class TestSceneManager {
    private int drawnScene1 = 0;
    private int updatedScene1 = 0;
    
    private int drawnScene2 = 0;
    private int updatedScene2 = 0;


    
    private class DummyScene1 extends SceneUpdater {
        DummyScene1(SceneManager sceneManager) {
            super(sceneManager);
        }

        @Override
        void updateScene() {
            updatedScene1 ++;
            if (updatedScene1 >= 5) {
                sceneManager.switchScene(new DummyScene2(this.sceneManager));
            }
        }

        @Override
        void drawScene(Graphics2D g, int score) {
            drawnScene1 ++;
        }

        @Override
        String getSceneName() {
            return "DummyScene1";
        }
    }

    private class DummyScene2 extends SceneUpdater {

        DummyScene2(SceneManager sceneManager) {
            super(sceneManager);
        }

        @Override
        void updateScene() {
            updatedScene2 ++;
        }

        @Override
        void drawScene(Graphics2D g, int score) {
            drawnScene2 ++;
        }

        @Override
        String getSceneName() {
            return "DummyScene2";
        }
    }

    @Test
    public void testSceneManager_drawScene_expectSingleDraw() {
        drawnScene1 = 0;
        SceneManager sm = new SceneManager(null);
        sm.switchScene(new DummyScene1(sm));

        sm.drawScene(null, 0);

        assertEquals(1, drawnScene1);
    }

    @Test
    public void testSceneManager_drawSceneMultipleTimes_expectMultipleDraws() {
        drawnScene1 = 0;
        SceneManager sm = new SceneManager(null);
        sm.switchScene(new DummyScene1(sm));
        for (int i = 0;i < 5; i ++) {
            sm.drawScene(null, 0);
        }

        assertEquals(5, drawnScene1);
    }
}
