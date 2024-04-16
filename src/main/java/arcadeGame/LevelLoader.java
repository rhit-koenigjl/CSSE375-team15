package arcadeGame;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class LevelLoader {
    private List<Tile> tiles;
    private List<Enemy> enemies;
    private Player player;

    private String filePath;
    private String dataString;

    private int size;
    private int levelWidth;
    private int levelHeight;
    private int numCoins;

    public LevelLoader(String path) {
        this.tiles = new ArrayList<Tile>();
        this.enemies = new ArrayList<Enemy>();
        this.player = null;
        this.size = -1;
        this.levelWidth = -1;
        this.levelHeight = -1;
        this.numCoins = 0;

        this.filePath = path;
    }

    public JSONObject getJsonObject() {
        Object obj = null;
        try {
            obj = new JSONParser().parse(new InputStreamReader(
                    ClassLoader.getSystemClassLoader().getResourceAsStream(this.filePath)));
        } catch (Exception e) {
            System.err
                    .println("Could not load level, issue parsing level file at: " + this.filePath);
            System.err.println(e.toString());
            e.printStackTrace();
        }

        return (JSONObject) obj;
    }

    public void setupInternalValues(JSONObject jo) {
        String h = (String) jo.get("height");
        String w = (String) jo.get("width");
        String s = (String) jo.get("block_size");
        String blockStream = (String) jo.get("data");
        this.levelHeight = Integer.parseInt(h);
        this.levelWidth = Integer.parseInt(w);
        size = Integer.parseInt(s);
        this.dataString = blockStream;
    }

    private void addBlock(int xPos, int yPos, char blockType, Direction dir) {
        int actorSize = (int) (size * 4.0 / 5.0);
        int actorXPos = (int) ((xPos + 0.2) * size);
        int actorYPos = (int) ((yPos + 0.2) * size);

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
                enemies.add(new HunterSeeker(actorXPos, actorYPos, actorSize, actorSize, this.player));
                break;
            case 'c':
                tiles.add(new Coin(xPos * size, yPos * size, size, size));
                numCoins++;
                break;
            case 'P':
                player.setX(actorXPos);
                player.setY(actorYPos);
                player.setWidth(actorSize);
                player.setHeight(actorSize);
                break;
            case 'B':
                tiles.add(new BouncePad(xPos * size, yPos * size, size, size));
                break;
            case 'G':
                enemies.add(new EnemyGenerator(actorXPos, actorYPos, actorSize, actorSize, enemies, player));
        }
    }

    public void loadLevel() {
        System.out.println("here");
        JSONObject jo = getJsonObject();
        setupInternalValues(jo);

        this.player = new Player(0, 0, size, size);

        int xPos = 0;
        int yPos = 0;
        for (String blockSet : this.dataString.split("\\|")) {
            // throw new NullPointerException(blockSet);
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

    public int getNumCoins() {
        return numCoins;
    }
}
