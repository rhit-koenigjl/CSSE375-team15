package arcadeGame.gameComponents.imageManagers;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum GameImage {
    BACKGROUND("background", Extension.PNG),
    BOUNCE_PAD("bounce_pad", Extension.GIF, DirectionType.FOUR),
    BRICK("brick", Extension.PNG),
    COIN("coin", Extension.GIF),
    GHOST("ghost", Extension.PNG, DirectionType.EIGHT),
    DEAD_GHOST("dead_ghost", Extension.PNG),
    MOSSY_BRICK("mossy_brick", Extension.PNG),
    PLAYER("player", Extension.GIF, DirectionType.TWO),
    SPAWNER("spawner", Extension.GIF),
    SPIKE("spike", Extension.PNG, DirectionType.FOUR),
    TRACKER("angry_ghost", Extension.PNG, DirectionType.EIGHT),
    JUMP_WIND("jump_wind", Extension.PNG);

    private String fileName;
    private Extension extension;
    private Set<Direction> directions;
    private Map<Direction, Image> images;

    // For testing purposes
    private Map<Direction, File> imageFiles;

    private GameImage(String fileName, Extension extension, DirectionType directionType) {
        this.fileName = fileName;
        this.extension = extension;
        setPossibleDirections(directionType);
        createImages();
    }

    private GameImage(String fileName, Extension extension) {
        this(fileName, extension, DirectionType.NONE);
    }

    private enum Extension {
        GIF, PNG;
    }

    private enum DirectionType {
        NONE, TWO, FOUR, EIGHT;
    }

    private void setPossibleDirections(DirectionType directionType) {
        switch (directionType) {
            case NONE:
                this.directions = Set.of(Direction.NONE);
                break;
            case TWO:
                this.directions = Set.of(Direction.LEFT, Direction.RIGHT);
                break;
            case FOUR:
                this.directions =
                        Set.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT);
                break;
            case EIGHT:
                this.directions = Set.of(Direction.UP, Direction.DOWN, Direction.LEFT,
                        Direction.RIGHT, Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN_LEFT,
                        Direction.DOWN_RIGHT);
                break;
        }
    }

    private void createImages() {
        images = new HashMap<>();
        imageFiles = new HashMap<>();
        for (Direction direction : directions) {
            StringBuilder path = new StringBuilder(this.fileName);
            if (direction != Direction.NONE) {
                path.append("_").append(direction.toString().toLowerCase());
            }
            path.append(".").append(this.extension.toString().toLowerCase());
            try {
                images.put(direction, generateImage(path.toString()));
                imageFiles.put(direction, new File(path.toString()));
            } catch (Exception e) {
                System.err.println("Could not load image: " + path.toString());
            }
        }
    }

    private Image generateImage(String path) {
        try {
            InputStream in = getClass().getResourceAsStream("/images/" + path);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = in.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            byte[] imageInByte = buffer.toByteArray();
            return Toolkit.getDefaultToolkit().createImage(imageInByte);
        } catch (Exception e) {
            System.err.println("Could not load image: " + path);
            return null;
        }
    }

    public Image getImage() {
        return this.images.get(Direction.NONE);
    }

    public Image getImage(Direction direction) {
        return this.images.get(direction);
    }

    // For testing purposes
    public Map<Direction, Image> getImages() {
        return this.images;
    }

    // For testing purposes
    public File getImageFile(Direction direction) {
        return this.imageFiles.get(direction);
    }

}
