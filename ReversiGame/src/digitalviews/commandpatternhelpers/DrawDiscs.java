package digitalviews.commandpatternhelpers;

import java.awt.Color;
import java.awt.Graphics;

/**
 * The DrawDiscs class provides a simple utility for drawing filled discs on a graphics context. It
 * utilizes the java.awt.Graphics class to draw and fill a disc with specified dimensions and color.
 * The disc is drawn based on a specified location (x, y) and the length of the side of the bounding
 * square.
 */
public class DrawDiscs {

  /**
   * Draws a filled disc on the specified graphics context with the given parameters.
   *
   * @param g         The graphics context on which to draw the disc.
   * @param x         The x-coordinate of the top-left corner of the bounding square.
   * @param y         The y-coordinate of the top-left corner of the bounding square.
   * @param length    The length of the side of the bounding square, defining the disc size.
   * @param discColor The color of the disc to be drawn.
   */
  public void run(Graphics g, int x, int y, int length, Color discColor) {
    // Set the color for the disc
    g.setColor(discColor);

    // Draw the outline of the disc
    g.drawOval(x, y, length, length);

    // Fill the disc with the specified color
    g.fillOval(x, y, length, length);
  }
}
