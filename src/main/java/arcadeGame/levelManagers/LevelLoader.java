package arcadeGame.levelManagers;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import arcadeGame.gameComponents.BouncePad;
import arcadeGame.gameComponents.Coin;
import arcadeGame.gameComponents.Enemy;
import arcadeGame.gameComponents.EnemyGenerator;
import arcadeGame.gameComponents.HunterSeeker;
import arcadeGame.gameComponents.MossyWall;
import arcadeGame.gameComponents.Player;
import arcadeGame.gameComponents.Spike;
import arcadeGame.gameComponents.Tile;
import arcadeGame.gameComponents.Wall;
import arcadeGame.gameComponents.imageManagers.Direction;

public class LevelLoader {
    private static final int EMPTY = -1;
    private static final double ACTOR_SIZE_MULTIPLIER = 4.0 / 5.0;
    private static final double ACTOR_POSITION_OFFSET = 0.1;

    private List<Tile> tiles;
    private List<Enemy> enemies;
    private Player player;
    private InputStream file;
    private String dataString;
    private int size;
    private int levelWidth;
    private int levelHeight;
    private int numCoins;

    public LevelLoader(String path) {
        this.tiles = new ArrayList<Tile>();
        this.enemies = new ArrayList<Enemy>();
        this.player = null;
        this.size = EMPTY;
        this.levelWidth = EMPTY;
        this.levelHeight = EMPTY;
        this.numCoins = 0;

        try {
            this.file = ClassLoader.getSystemClassLoader().getResourceAsStream(path.trim());
        } catch (Exception e) {
            System.err.println("Could not load level " + path);
            e.printStackTrace();
        }
    }

    JSONObject getJsonObject() {
        Object jsonObject = null;

        try {
            jsonObject = new JSONParser().parse(new InputStreamReader(this.file));
        } catch (Exception e) {
            System.err.println("Could not load level");
            e.printStackTrace();
        }

        return (JSONObject) jsonObject;
    }

    void setupInternalValues(JSONObject jsonObject) {
        String height = (String) jsonObject.get("height");
        String width = (String) jsonObject.get("width");
        String blockSize = (String) jsonObject.get("block_size");
        String blockStream = (String) jsonObject.get("data");
        this.levelHeight = Integer.parseInt(height);
        this.levelWidth = Integer.parseInt(width);
        this.size = Integer.parseInt(blockSize);
        this.dataString = blockStream;
    }

    private void addBlock(int xPos, int yPos, char blockType, Direction dir) {
        int actorSize = (int) (size * ACTOR_SIZE_MULTIPLIER);
        int actorXPos = (int) ((xPos + ACTOR_POSITION_OFFSET) * size);
        int actorYPos = (int) ((yPos + ACTOR_POSITION_OFFSET) * size);

        switch (blockType) {
            case 'b':
                tiles.add(new Wall(xPos * size, yPos * size, size, size));
                break;
            case 'm':
                tiles.add(new MossyWall(xPos * size, yPos * size, size, size));
                break;
            case 'S':
                tiles.add(new Spike(xPos * size, yPos * size, size, dir));
                break;
            case '&':
                enemies.add(new Enemy(actorXPos, actorYPos, actorSize, actorSize, dir));
                break;
            case '@':
                enemies.add(
                        new HunterSeeker(actorXPos, actorYPos, actorSize, actorSize, this.player));
                break;
            case 'c':
                tiles.add(new Coin(actorXPos, actorYPos, actorSize, actorSize));
                numCoins++;
                break;
            case 'P':
                player.setX(actorXPos);
                player.setY(actorYPos);
                player.setWidth(actorSize);
                player.setHeight(actorSize);
                break;
            case 'B':
                tiles.add(new BouncePad(xPos * size, yPos * size, size, size, dir));
                break;
            case 'G':
                enemies.add(new EnemyGenerator(actorXPos, actorYPos, actorSize, actorSize, enemies,
                        player));
        }
    }

    public void loadLevel() {
        JSONObject jsonObject = getJsonObject();
        setupInternalValues(jsonObject);

        this.player = new Player(0, 0, size, size);

        int xPos = 0;
        int yPos = 0;
        for (String blockSet : this.dataString.split("\\|")) {
            String[] blockData = blockSet.split("-");
            String[] extraData = blockData[0].split("#");
            Direction d = Direction.NONE;
            if (extraData.length > 1) {
                d = Direction.fromString(extraData[1]);
            }
            char blockType = blockData[0].charAt(0);
            int blockQ = Integer.parseInt(blockData[1]);

            for (; blockQ > 0; blockQ--) {
                addBlock(xPos, yPos, blockType, d);
                xPos++;
                if (xPos % this.levelWidth == 0) {
                    xPos = 0;
                    yPos++;
                }
            }
        }
    }

    public List<Tile> getTiles() {
        return this.tiles;
    }

    public List<Enemy> getEnemies() {
        return this.enemies;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getWidth() {
        return levelWidth * size;
    }

    public int getHeight() {
        return levelHeight * size;
    }

    public int getSize() {
        return size;
    }

    public String getDataString() {
        return this.dataString;
    }

    int getNumCoins() {
        return numCoins;
    }

}
