package arcadeGame.gameComponents.imageManagers;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT, NONE;

    private static Map<String, Direction> stringMap = new HashMap<String, Direction>() {
        {
            put("U", UP);
            put("UR", UP_RIGHT);
            put("UL", UP_LEFT);
            put("L", LEFT);
            put("R", RIGHT);
            put("DL", DOWN_LEFT);
            put("DR", DOWN_RIGHT);
            put("D", DOWN);
            put("", NONE);
        }
    };

    private static HashMap<Direction, Double> angleMap = new HashMap<Direction, Double>() {
        {
            put(UP, 0.0);
            put(UP_RIGHT, Math.PI / 4.0);
            put(UP_LEFT, -Math.PI / 4.0);
            put(LEFT, Math.PI / -2.0);
            put(RIGHT, Math.PI / 2.0);
            put(DOWN_LEFT, Math.PI * -3.0 / 4.0);
            put(DOWN_RIGHT, Math.PI * 3.0 / 4.0);
            put(DOWN, Math.PI);
            put(NONE, 0.0);
        }
    };

    public static Direction fromString(String str) {
        return stringMap.get(str);
    }

    public static double toAngle(Direction d) {
        return angleMap.get(d);
    }

}
