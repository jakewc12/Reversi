package digitalviews.commandpatternhelpers;

import java.awt.Color;
import java.awt.Graphics;

/**
 * The {@code DrawHints} class provides a utility for drawing hints on a graphics context. It allows
 * drawing text with a specified color and position.
 */
public class DrawHints {

  /**
   * Draws text on the specified graphics context with the given parameters.
   *
   * @param g        The graphics context on which to draw the text.
   * @param numFlips The text to be drawn.
   * @param x        The x-coordinate of the starting point of the text.
   * @param y        The y-coordinate of the starting point of the text.
   * @return The updated graphics context after drawing the text.
   */
  public Graphics draw(Graphics g, String numFlips, int x, int y) {
    g.setColor(Color.BLACK);
    g.drawString(numFlips, x, y);
    return g;
  }
}
