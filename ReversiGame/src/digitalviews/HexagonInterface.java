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
   * the Y position of the center of the hexagon in relation to the panel it is drawn on.
   * @return the Y position of the hexagon.
   */
  double getY();

  /**
   * the X position of the center of the hexagon in relation to the panel it is drawn on.
   * @return the X position of the hexagon.
   */
  double getX();

  /**
   * The coordinate in (Q,R,S) form of the hexagon.
   * @return the coordinates of the hex.
   */
  Coordinate getCoordinate();
}
