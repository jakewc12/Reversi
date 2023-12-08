package digitalviews;

import java.awt.Graphics;
import model.hexreversi.LogicalHexCoordinate;

/**
 * Meant to represent a drawn digital hexagon which mirrors a logical hexagon in the game of
 * reversi.
 */
public interface DrawnHexagonInterface {

  /**
   * Draws the hexagon on the graphic.
   *
   * @param g the graphic to be drawn on.
   */
  void draw(Graphics g);


  /**
   * the Y position of the center of the hexagon in relation to the panel it is drawn on.
   *
   * @return the Y position of the hexagon.
   */
  double getY();

  /**
   * the X position of the center of the hexagon in relation to the panel it is drawn on.
   *
   * @return the X position of the hexagon.
   */
  double getX();

  /**
   * The coordinate in (Q,R,S) form of the hexagon.
   *
   * @return the coordinates of the hex.
   */
  LogicalHexCoordinate getLogicalHexCoord();

  /**
   * Checks if the hex contains the given digital (x,y) point.
   *
   * @return Returns true if the point is inside the hex, false otherwise.
   */
  boolean containsPoint(int x, int y);

}
