package arcadeGame;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class MouseListener extends MouseAdapter {
  private Map<Rectangle, SceneUpdater> clicks = new HashMap<>();
  private SceneManager sceneManager;

  @Override
  public void mouseClicked(MouseEvent e) {
    if (sceneManager == null) {
      return;
    }
    for (Rectangle r : clicks.keySet()) {
      if (r.contains(e.getX(), e.getY())) {
        sceneManager.switchScene(clicks.get(r));
      }
    }
  }

  void setSceneManager(SceneManager sceneManager) {
    this.sceneManager = sceneManager;
  }

  void addClickAction(Rectangle r, SceneUpdater s) {
    clicks.put(r, s);
  }

}
