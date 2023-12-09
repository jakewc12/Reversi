package digitalviews.CommandPatternHelpers;

import java.awt.*;

public class DrawDiscs {
  public void run(Graphics g, int x, int y, int length, Color discColor){
    g.setColor(discColor);
    g.drawOval(x, y,
            length, length);
    g.fillOval(x, y,
            length, length);
  }
}
