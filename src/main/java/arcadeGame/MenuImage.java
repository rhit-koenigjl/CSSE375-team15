package arcadeGame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public enum MenuImage {
  LOGO("menu_logo.png", (int) (940 * 0.65), (int) (384 * 0.65)),
  PLAY("play_button.png", (int) (72 * 1.7), (int) (108 * 1.7)),
  HELP("help_button.png", (int) (48 * 1.7), (int) (72 * 1.7)),
  CREDITS("credits_button.png", (int) (48 * 1.7), (int) (72 * 1.7)),
  MENU_BLOCK("menu_block.png", 512, 128),
  LEFT_KEY("arrow_key_left.png", 64, 64),
  RIGHT_KEY("arrow_key_right.png", 64, 64),
  UP_KEY("arrow_key_up.png", 64, 64),
  DOWN_KEY("arrow_key_down.png", 64, 64);

  private BufferedImage image;

  private MenuImage(String fileName, int width, int height) {
    try {
      this.image = ImageIO.read(getClass().getResource("/images/" + fileName));
      resize(width, height);
    } catch (Exception e) {
      System.err.println("Error loading image: " + fileName);
    }
  }

  private void resize(int width, int height) {
    BufferedImage resizedImage = new BufferedImage(width, height, image.getType());
    Graphics2D g = resizedImage.createGraphics();
    g.drawImage(image, 0, 0, width, height, null);
    g.dispose();
    image = resizedImage;
  }

  Image getImage() {
    return this.image;
  }

}
