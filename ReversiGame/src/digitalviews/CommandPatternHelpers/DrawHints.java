package digitalviews.CommandPatternHelpers;

import java.awt.*;

public class DrawHints {
  public Graphics draw(Graphics g, String numFlips, int x, int y) {
    g.setColor(Color.BLACK);
    g.drawString(numFlips, x, y);
    return g;
  }
}
