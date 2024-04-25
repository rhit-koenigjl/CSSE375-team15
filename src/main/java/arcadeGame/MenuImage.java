package arcadeGame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public enum MenuImage {
  LOGO("menu_logo.png", 470, 192),
  PLAY("play_button.png", 72, 108),
  HELP("help_button.png", 48, 72),
  CREDITS("credits_button.png", 48, 72);

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
