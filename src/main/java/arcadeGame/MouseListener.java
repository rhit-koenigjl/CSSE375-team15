package arcadeGame;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;

public class MouseListener extends MouseAdapter {
  private static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
  private static final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);

  private Map<Rectangle, SceneUpdater> clicks = new HashMap<>();
  private SceneManager sceneManager;
  private JFrame frame;
  private Rectangle hovered;

  MouseListener(JFrame frame) {
    this.frame = frame;
    frame.addMouseMotionListener(this);
    frame.addMouseListener(this);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (sceneManager == null || hovered == null) {
      return;
    }
    sceneManager.switchScene(clicks.get(hovered));
    clicks.clear();
    resetCursor();
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    if (sceneManager == null || clicks.isEmpty()) {
      return;
    }
    for (Rectangle r : clicks.keySet()) {
      if (r.contains(e.getX(), e.getY())) {
        hovered = r;
        frame.setCursor(HAND_CURSOR);
        return;
      }
    }
    resetCursor();
  }

  private void resetCursor() {
    frame.setCursor(DEFAULT_CURSOR);
    hovered = null;
  }

  void setSceneManager(SceneManager sceneManager) {
    this.sceneManager = sceneManager;
  }

  void addClickAction(Rectangle r, SceneUpdater s) {
    clicks.put(r, s);
  }

}
