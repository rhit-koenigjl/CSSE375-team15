package arcadeGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.awt.Image;
import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

public class TestSpriteImageGeneration {

  private Set<Direction> getNoDirections() {
    return Set.of(Direction.NONE);
  }

  private void checkImageGeneration(GameImage gameImage, Set<Direction> expectedDirections,
      Set<String> expectedFileNames) {
    int expectedCount = expectedDirections.size();
    Map<Direction, Image> images = gameImage.getImages();

    assertEquals(expectedCount, images.size());
    assertEquals(expectedDirections, images.keySet());

    for (Direction direction : images.keySet()) {
      if (expectedFileNames.isEmpty()) {
        // We have more images than expected
        assertTrue(false);
      }
      File fileName = gameImage.getImageFile(direction);
      for (String expectedFileName : expectedFileNames) {
        if (fileName.getPath().contains(expectedFileName)) {
          assertTrue(true);
          expectedFileNames.remove(expectedFileName);
          break;
        }
      }
    }

    assertTrue(expectedFileNames.isEmpty());
  }

  @Test
  public void testBackgroundImageGenerator() {
    Set<Direction> expectedDirections = getNoDirections();
    Set<String> expectedFileNames = new HashSet<>(Set.of("background.png"));

    GameImage gameImage = GameImage.BACKGROUND;
    checkImageGeneration(gameImage, expectedDirections, expectedFileNames);
  }

  @Test
  public void testBouncePadImageGenerator() {
    Set<Direction> expectedDirections = getNoDirections();
    Set<String> expectedFileNames = new HashSet<>(Set.of("bounce_pad.gif"));

    GameImage gameImage = GameImage.BOUNCE_PAD;
    checkImageGeneration(gameImage, expectedDirections, expectedFileNames);
  }

  @Test
  public void testBrickImageGenerator() {
    Set<Direction> expectedDirections = getNoDirections();
    Set<String> expectedFileNames = new HashSet<>(Set.of("brick.png"));

    GameImage gameImage = GameImage.BRICK;
    checkImageGeneration(gameImage, expectedDirections, expectedFileNames);
  }

  @Test
  public void testCoinImageGenerator() {
    Set<Direction> expectedDirections = getNoDirections();
    Set<String> expectedFileNames = new HashSet<>(Set.of("coin.gif"));

    GameImage gameImage = GameImage.COIN;
    checkImageGeneration(gameImage, expectedDirections, expectedFileNames);
  }

  private Set<Direction> getEightDirections() {
    return Set.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT, Direction.UP_LEFT,
        Direction.UP_RIGHT, Direction.DOWN_LEFT, Direction.DOWN_RIGHT);
  }

  private Set<String> getDirectedFiles(String fileName, String extension,
      Set<Direction> directions) {
    Set<String> fileNames = new HashSet<>();
    for (Direction direction : directions) {
      fileNames.add(fileName + "_" + direction.toString().toLowerCase() + "." + extension);
    }
    return fileNames;
  }

  @Test
  public void testGhostImageGenerator() {
    Set<Direction> expectedDirections = getEightDirections();
    Set<String> expectedFileNames = getDirectedFiles("ghost", "png", expectedDirections);

    GameImage gameImage = GameImage.GHOST;
    checkImageGeneration(gameImage, expectedDirections, expectedFileNames);
  }

  @Test
  public void testMossyBrickImageGenerator() {
    Set<Direction> expectedDirections = getNoDirections();
    Set<String> expectedFileNames = new HashSet<>(Set.of("mossy_brick.png"));

    GameImage gameImage = GameImage.MOSSY_BRICK;
    checkImageGeneration(gameImage, expectedDirections, expectedFileNames);
  }

  private Set<Direction> getTwoDirections() {
    return Set.of(Direction.LEFT, Direction.RIGHT);
  }

  @Test
  public void testPlayerImageGenerator() {
    Set<Direction> expectedDirections = getTwoDirections();
    Set<String> expectedFileNames = getDirectedFiles("player", "gif", expectedDirections);

    GameImage gameImage = GameImage.PLAYER;
    checkImageGeneration(gameImage, expectedDirections, expectedFileNames);
  }

}
