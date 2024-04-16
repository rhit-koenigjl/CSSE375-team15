package arcadeGame;

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

    public Direction fromString(String keyString) {
        return stringMap.get(keyString);
    }
}
