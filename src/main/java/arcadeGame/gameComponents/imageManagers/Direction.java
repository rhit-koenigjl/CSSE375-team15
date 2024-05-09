package arcadeGame.gameComponents.imageManagers;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT, NONE;

    private static final Map<String, Direction> stringMap = new HashMap<>() {
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

    private static final HashMap<Direction, Double> angleMap = new HashMap<>() {
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

    public static Direction fromVector(double vx, double vy, double error) {
        double x = Math.abs(vx) < error ? 0 : vx;
        double y = Math.abs(vy) < error ? 0 : vy;

        if (x < 0) {
            return (y < 0) ? UP_LEFT : (y > 0) ? DOWN_LEFT : LEFT;
        } else if (x > 0) {
            return (y < 0) ? UP_RIGHT : (y > 0) ? DOWN_RIGHT : RIGHT;
        }
        return (y < 0) ? UP : (y > 0) ? DOWN : NONE;
    }

    public static double toAngle(Direction dir) {
        return angleMap.get(dir);
    }

}
