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
  @Test
  public void testBackgroundImageGenerator() {
    int expectedCount = 1;
    Set<Direction> expectedDirections = Set.of(Direction.NONE);
    Set<String> expectedFileNames = new HashSet<>(Set.of("background.png"));

    GameImage gameImage = GameImage.BACKGROUND;
    Map<Direction, Image> images = gameImage.getImages();

    assertEquals(expectedCount, images.size());
    assertEquals(expectedDirections, images.keySet());

    for (Direction direction : images.keySet()) {
      if (expectedFileNames.isEmpty()) {
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
}
