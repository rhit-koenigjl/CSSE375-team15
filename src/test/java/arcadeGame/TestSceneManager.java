package arcadeGame;

import static org.junit.Assert.assertEquals;
import java.awt.Graphics2D;
import org.junit.Test;
import arcadeGame.gameHelpers.SceneManager;
import arcadeGame.gameHelpers.screens.SceneUpdater;

class TestSceneManager {
    private int drawnScene1 = 0;
    private int updatedScene1 = 0;

    private int drawnScene2 = 0;
    private int updatedScene2 = 0;

    private class DummyScene1 extends SceneUpdater {
        DummyScene1(SceneManager sceneManager) {
            super(sceneManager);
        }

        @Override
        public void updateScene() {
            updatedScene1++;
            if (updatedScene1 >= 5) {
                sceneManager.switchScene(new DummyScene2(this.sceneManager));
            }
        }

        @Override
        public void drawScene(Graphics2D g) {
            drawnScene1++;
        }

        @Override
        public String getSceneName() {
            return "DummyScene1";
        }

    }

    private class DummyScene2 extends SceneUpdater {

        DummyScene2(SceneManager sceneManager) {
            super(sceneManager);
        }

        @Override
        public void updateScene() {
            updatedScene2++;
        }

        @Override
        public void drawScene(Graphics2D g) {
            drawnScene2++;
        }

        @Override
        public String getSceneName() {
            return "DummyScene2";
        }

    }

    @Test
    void testSceneManager_drawScene_expectSingleDraw() {
        drawnScene1 = 0;
        SceneManager sm = new SceneManager(null);
        sm.switchScene(new DummyScene1(sm));

        sm.drawScene(null);

        assertEquals(1, drawnScene1);
    }

    @Test
    void testSceneManager_drawSceneMultipleTimes_expectMultipleDraws() {
        drawnScene1 = 0;
        SceneManager sm = new SceneManager(null);
        sm.switchScene(new DummyScene1(sm));
        for (int i = 0; i < 5; i++) {
            sm.drawScene(null);
        }

        assertEquals(5, drawnScene1);
    }

    @Test
    void testSceneManager_runScene_expectUpdate() {
        updatedScene1 = 0;
        SceneManager sm = new SceneManager(null);
        sm.switchScene(new DummyScene1(sm));
        sm.runScene();

        assertEquals(1, updatedScene1);
    }

    @Test
    void testSceneManager_runSceneMultipleTimes_expectMultipleUpdates() {
        updatedScene1 = 0;
        SceneManager sm = new SceneManager(null);
        sm.switchScene(new DummyScene1(sm));
        for (int i = 0; i < 3; i++) {
            sm.runScene();
        }

        assertEquals(3, updatedScene1);
    }

    @Test
    void testSceneManager_testSwitchScene_expectSceneSwitched() {
        SceneManager sm = new SceneManager(null);
        sm.switchScene(new DummyScene1(sm));
        assertEquals("DummyScene1", sm.getCurrentScene().getSceneName());
        sm.switchScene(new DummyScene2(sm));
        assertEquals("DummyScene2", sm.getCurrentScene().getSceneName());
    }

    @Test
    void testSceneMAnager_testSwitchSceneAtRuntime_expectRuntimeSwitch() {
        drawnScene1 = 0;
        updatedScene1 = 0;

        drawnScene2 = 0;
        updatedScene2 = 0;

        SceneManager sm = new SceneManager(null);
        sm.switchScene(new DummyScene1(sm));

        assertEquals("DummyScene1", sm.getCurrentScene().getSceneName());

        for (int i = 0; i < 12; i++) {
            sm.runScene();
            sm.drawScene(null);
        }

        assertEquals("DummyScene2", sm.getCurrentScene().getSceneName());
        assertEquals(4, drawnScene1);
        assertEquals(5, updatedScene1);
        assertEquals(8, drawnScene2);
        assertEquals(7, updatedScene2);
    }

}
