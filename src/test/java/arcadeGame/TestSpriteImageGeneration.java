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
        }
      }
    }

    assertTrue(expectedFileNames.isEmpty());
  }

  @Test
  public void testBackgroundImageGenerator() {
    Set<Direction> expectedDirections = Set.of(Direction.NONE);
    Set<String> expectedFileNames = new HashSet<>(Set.of("background.png"));

    GameImage gameImage = GameImage.BACKGROUND;
    checkImageGeneration(gameImage, expectedDirections, expectedFileNames);
  }

  @Test
  public void testBouncePadImageGenerator() {
    Set<Direction> expectedDirections = Set.of(Direction.NONE);
    Set<String> expectedFileNames = new HashSet<>(Set.of("bounce_pad.gif"));

    GameImage gameImage = GameImage.BOUNCE_PAD;
    checkImageGeneration(gameImage, expectedDirections, expectedFileNames);
  }

  @Test
  public void testBrickImageGenerator() {
    Set<Direction> expectedDirections = Set.of(Direction.NONE);
    Set<String> expectedFileNames = new HashSet<>(Set.of("brick.png"));

    GameImage gameImage = GameImage.BRICK;
    checkImageGeneration(gameImage, expectedDirections, expectedFileNames);
  }

  @Test
  public void testCoinImageGenerator() {
    Set<Direction> expectedDirections = Set.of(Direction.NONE);
    Set<String> expectedFileNames = new HashSet<>(Set.of("coin.gif"));

    GameImage gameImage = GameImage.COIN;
    checkImageGeneration(gameImage, expectedDirections, expectedFileNames);
  }

}
