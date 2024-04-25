package arcadeGame;

import java.awt.Image;
import javax.swing.ImageIcon;

public enum MenuImage {
  LOGO("menu_logo.png"),
  PLAY("play_button.png"),
  HELP("help_button.png"),
  CREDITS("credits_button.png");

  private Image image;

  private MenuImage(String fileName) {
    this.image = new ImageIcon(getClass().getResource("/images/" + fileName)).getImage();
  }

  Image getImage() {
    return this.image;
  }

}
