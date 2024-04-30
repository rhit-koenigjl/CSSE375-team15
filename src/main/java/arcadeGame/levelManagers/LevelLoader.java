package arcadeGame.levelManagers;

import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
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
    private List<Tile> tiles;
    private List<Enemy> enemies;
    private Player player;

    private URL filePath;
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

        try {
            this.filePath = new File(path).toURI().toURL();
        } catch (Exception e) {
            System.err.println("Could not load level " + path);
            e.printStackTrace();
        }
    }

    JSONObject getJsonObject() {
        Object obj = null;

        try {
            obj = new JSONParser().parse(new InputStreamReader(this.filePath.openStream()));
        } catch (Exception e) {
            System.err.println("Could not load level " + this.filePath.getPath());
            e.printStackTrace();
        }

        return (JSONObject) obj;
    }

    void setupInternalValues(JSONObject jo) {
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
        int actorXPos = (int) ((xPos + 0.1) * size);
        int actorYPos = (int) ((yPos + 0.1) * size);

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
                tiles.add(new BouncePad(xPos * size, yPos * size, size, size));
                break;
            case 'G':
                enemies.add(new EnemyGenerator(actorXPos, actorYPos, actorSize, actorSize, enemies,
                        player));
        }
    }

    public void loadLevel() {
        JSONObject jo = getJsonObject();
        setupInternalValues(jo);

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
