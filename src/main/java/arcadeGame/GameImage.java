package arcadeGame;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.ImageIcon;

public enum GameImage {
  BACKGROUND("background", Extension.PNG),
  BOUNCE_PAD("bounce_pad", Extension.GIF),
  BRICK("brick", Extension.PNG),
  COIN("coin", Extension.GIF),
  GHOST("ghost", Extension.PNG, DirectionType.EIGHT),
  MOSSY_BRICK("mossy_brick", Extension.PNG),
  PLAYER("player", Extension.GIF, DirectionType.TWO),
  SPAWNER("spawner", Extension.GIF),
  SPIKE("spike", Extension.PNG, DirectionType.FOUR),
  TRACKER("angry_ghost", Extension.PNG, DirectionType.EIGHT);

  private String fileName;
  private Extension extension;
  private Set<Direction> directions;
  private Map<Direction, Image> images;

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
        this.directions = Set.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT);
        break;
      case EIGHT:
        this.directions = Set.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT,
            Direction.UP_LEFT, Direction.UP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT);
        break;
    }
  }

  private void createImages() {
    images = new HashMap<>();
    for (Direction direction : directions) {
      StringBuilder path = new StringBuilder(this.fileName);
      if (direction != Direction.NONE) {
        path.append("_").append(direction.toString().toLowerCase());
      }
      path.append(".").append(this.extension.toString().toLowerCase());
      images.put(direction,
          new ImageIcon(getClass().getResource("/images/" + path.toString())).getImage());
    }
  }

  public Image getImage() {
    return this.images.get(Direction.NONE);
  }

  public Image getImage(Direction direction) {
    return this.images.get(direction);
  }

}
