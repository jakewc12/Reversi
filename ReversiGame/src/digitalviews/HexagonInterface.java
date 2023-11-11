package digitalviews;

import java.awt.*;

import model.Coordinate;

public interface HexagonInterface {
  /**
   * Draws the hexagon on the graphic.
   * @param g the graphic to be drawn on.
   */
  void draw(Graphics g);

  /**
   * This would set the background color of the tile to cyan or back to light grey. This does NOT
   * change the color of the disc.
   *
   * @param clr the color you want the disc set to.
   */
  void setColor(Color clr);

  /**
   * the Y position of the center of the hexagon in relation to the panel it is drawn on.
   * @return the Y position of the hexagon.
   */
  int getY();

  /**
   * the X position of the center of the hexagon in relation to the panel it is drawn on.
   * @return the X position of the hexagon.
   */
  int getX();

  /**
   * The coordinate in (Q,R,S) form of the hexagon.
   * @return
   */
  Coordinate getCoordinate();
}
