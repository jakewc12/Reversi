package dustinraymondreversi.view;

import java.awt.geom.Path2D;

/**
 * Represents a regular hexagon drawn on a GUI view. The hexagon is oriented so that one of its
 * corners is pointing upward.
 */
public class HexagonShape extends Path2D.Double {

  // NOTE: width and height fields are stored so that sqrt(3) only needs to appear in
  // one calculation
  private final double width;

  private final double height;

  /**
   * Constructs a new regular hexagon with the given side length.
   *
   * @param sideLength the width of the hexagon
   * @throws IllegalArgumentException if any of the parameters are invalid
   */
  HexagonShape(double sideLength) {
    super();

    if (sideLength <= 0) {
      throw new IllegalArgumentException("Invalid dimensions for hexagon.");
    }

    this.width = sideLength * Math.sqrt(3);
    this.height = sideLength * 2;

    this.construct();
  }

  /**
   * Constructs this hexagon using line segments depending on its dimensions.
   */
  private void construct() {
    this.moveTo(-this.width / 2, this.height / 4);
    this.lineTo(0, this.height / 2);
    this.lineTo(this.width / 2, this.height / 4);
    this.lineTo(this.width / 2, -this.height / 4);
    this.lineTo(0, -this.height / 2);
    this.lineTo(-this.width / 2, -this.height / 4);
    this.closePath();
  }
}
