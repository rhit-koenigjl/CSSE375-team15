package arcadeGame;

import java.util.HashMap;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    UP_LEFT,
    UP_RIGHT,
    DOWN_LEFT,
    DOWN_RIGHT,
    NONE;

    private static HashMap<String, Direction> stringMap = new HashMap<String, Direction>() {
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
    public static Direction fromString(String str) {
        return stringMap.get(str);
    }
}
