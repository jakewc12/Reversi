package digitalviews.hexreversi;

import digitalviews.DrawnShape;
import digitalviews.commandpatternhelpers.DrawDiscs;
import digitalviews.commandpatternhelpers.DrawHints;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Optional;
import model.Coordinate;
import model.DiscColor;
import model.hexreversi.LogicalHexCoordinate;

/**
 * A single digital hexagon tile on the game board.
 */
public class DrawnHexagon implements DrawnShape {

  public static final double THETA = (Math.PI * 2) / 6.0;
  //default hexagon radius
  static int hexagonRadius = 25;
  private final LogicalHexCoordinate logicalHexCoord;
  private final double hexCenterCoordY;
  private final double hexCenterCoordX;
  private final Color color;
  //2 means there will plenty of space between hexes, 1.8 is the shorter end of that space
  private final double buffer = 1.85;
  private Polygon poly;
  private Color discColor;
  private final Optional<Integer> numFlipsOnHex;

  /**
   * Creates a new hexagon that has the game coordinates q and r, a color of clr.
   *
   * @param logicalHexCoord   the coordinates a ReversiModel will use to find the hex.
   * @param discColor         the color of the disc inside the hexagon cell.
   * @param boardCenterCoordx the dead center or origin of the digital board in pixel coordinates.
   * @param boardCenterCoordy the dead center or origin of the digital board in pixel coordinates.
   * @param hexColor          the color of the background of the hex.
   * @param hexagonRadius     the radius of the hexagon.
   */
  public DrawnHexagon(LogicalHexCoordinate logicalHexCoord, DiscColor discColor,
      int boardCenterCoordx, int boardCenterCoordy, Color hexColor, int hexagonRadius,
      Optional<Integer> numFlips) {

    this.logicalHexCoord = logicalHexCoord;

    //Creates digital coordinates based on the logical coordinates given and the given center of
    // the digital board
    this.hexCenterCoordY = boardCenterCoordy + (hexagonRadius * 1.6) * (logicalHexCoord.getIntR());
    this.hexCenterCoordX = calculateX(boardCenterCoordx);
    this.numFlipsOnHex = numFlips;
    if (discColor == DiscColor.BLACK) {
      this.discColor = Color.BLACK;
    } else if (discColor == DiscColor.WHITE) {
      this.discColor = Color.WHITE;
    } else {
      this.discColor = Color.LIGHT_GRAY;
    }
    DrawnHexagon.hexagonRadius = hexagonRadius;
    this.color = hexColor;
    resetPolygon();
  }

  /**
   * Calculates x based on the logical coordinate R and offsets it by the logical coordinate S. This
   * calculation is also based on the digital center of the board.
   *
   * @param centerX the center x coordinate of the digital board.
   * @return A double which is the center coordinate of the hex
   */
  private double calculateX(int centerX) {
    double setCoordX;
    double offsetX;

    //Creates an offset based on the row the hexagon resides in.
    offsetX = logicalHexCoord.getIntR() * hexagonRadius * (buffer / 2);

    //Calculates the center of the hex using S and the given hexagon radius
    //1.8 is used for smaller space between hexagons
    setCoordX = centerX + (hexagonRadius * buffer) * (-logicalHexCoord.getIntS());
    setCoordX -= offsetX;
    return setCoordX;
  }

  private void resetPolygon() {
    poly = new Polygon();
    for (int i = 0; i < 6; i++) {
      int x1 = (int) (hexCenterCoordX + hexagonRadius * Math.sin(THETA * i));
      int y1 = (int) (hexCenterCoordY + hexagonRadius * Math.cos(THETA * i));
      poly.addPoint(x1, y1);
    }
  }

  /**
   * Draws the hexagon and the disc it contains on the digital board.
   *
   * @param g the graphic to be drawn on.
   */
  public void draw(Graphics g) {
    resetPolygon();
    Graphics2D g2d = (Graphics2D) g;
    drawHex(g2d);
    drawDisc(g2d);
    numFlipsOnHex.ifPresent(integer -> new DrawHints().draw(g, String.valueOf(integer),
        poly.getBounds().x + hexagonRadius / 2 + 6, poly.getBounds().y + hexagonRadius / 2 + 12));
  }

  private void drawHex(Graphics2D g) {
    g.setColor(Color.BLACK);
    g.drawPolygon(poly);
    g.setColor(color);
    g.fillPolygon(poly);
  }

  private void drawDisc(Graphics2D g) {
    if (discColor != Color.WHITE && discColor != Color.BLACK) {
      discColor = color;
    }
    new DrawDiscs().run(g, poly.getBounds().x + hexagonRadius / 2,
        poly.getBounds().y + hexagonRadius / 2, hexagonRadius, discColor);
  }

  /**
   * The Y position of the center of the hexagon on the digital board.
   *
   * @return the Y position of the hexagon.
   */
  public double getY() {
    return hexCenterCoordY;
  }

  /**
   * The X position of the center of the hexagon on the digital board.
   *
   * @return the X position of the hexagon.
   */
  public double getX() {
    return hexCenterCoordX;
  }


  /**
   * The logical coordinates of the hex which the model will use to make moves.
   *
   * @return The (q,r,s) coordinates of the hex.
   */
  @Override
  public Coordinate getLogicalCoord() {
    return this.logicalHexCoord;
  }

  public boolean containsPoint(int x, int y) {
    return poly.contains(x, y);
  }
}
