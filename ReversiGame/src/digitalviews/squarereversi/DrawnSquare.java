package digitalviews.squarereversi;

import java.awt.*;
import java.util.Optional;

import digitalviews.CommandPatternHelpers.DrawDiscs;
import digitalviews.CommandPatternHelpers.DrawHints;
import digitalviews.DrawnShape;
import model.Coordinate;
import model.DiscColor;
import model.hexreversi.LogicalHexCoordinate;
import model.squarereversi.SquareCoordinate;

/*
TODO:: NEED TO mAKE THIS IMPLEMENT DRAWNSHAPE
 */
public class DrawnSquare implements DrawnShape {
  private Polygon poly;
  private final SquareCoordinate logicalSquareCord;
  private final double squareCenterCoordX;
  private final double squareCenterCoordY;
  private final Color color;
  private Color discColor;
  private Optional<Integer> numFlipsOnHex;
  private final int squareLength;

  public DrawnSquare(SquareCoordinate logicalSquareCord, DiscColor discColor
          , double squareCenterCoordX, double squareCenterCoordY
          , Color color, int squareLength, Optional<Integer> numFlipsOnHex) {
    this.poly = poly;
    this.logicalSquareCord = logicalSquareCord;
    this.squareCenterCoordX = squareCenterCoordX;
    this.squareCenterCoordY = squareCenterCoordY;
    this.color = color;
    if (discColor == DiscColor.BLACK) {
      this.discColor = Color.BLACK;
    } else if (discColor == DiscColor.WHITE) {
      this.discColor = Color.WHITE;
    } else {
      this.discColor = Color.LIGHT_GRAY;
    }
    this.numFlipsOnHex = numFlipsOnHex;
    this.squareLength = squareLength;
  }

  /**
   * Draws the hexagon on the graphic.
   *
   * @param g the graphic to be drawn on.
   */
  public void draw(Graphics g) {
    resetPolygon();
    Graphics2D g2d = (Graphics2D) g;
    drawSquare(g2d);
    drawDisc(g2d);
    numFlipsOnHex.ifPresent(integer -> new DrawHints().draw(g, String.valueOf(integer)
            , poly.getBounds().x + squareLength / 2 + 6
            , poly.getBounds().y + squareLength / 2 + 12));
  }

  private void resetPolygon() {
    poly = new Polygon();
    for (int i = 0; i < 6; i++) {
      int x1 = (int) (getX() + squareLength * Math.sin(i));
      int y1 = (int) (getY() + squareLength * Math.cos(i));
      poly.addPoint(x1, y1);
    }
  }

  private void drawSquare(Graphics2D g) {
    g.setColor(Color.BLACK);
    g.drawPolygon(poly);
    g.setColor(color);
    g.fillPolygon(poly);
    if (numFlipsOnHex.isPresent()) {
      g.setColor(Color.BLACK);
      g.drawString(String.valueOf(numFlipsOnHex.get())
              , poly.getBounds().x + squareLength / 2 + 6
              , poly.getBounds().y + squareLength / 2 + 12);
    }
  }

  private void drawDisc(Graphics2D g) {
    if (discColor != Color.WHITE && discColor != Color.BLACK) {
      discColor = color;
    }
    new DrawDiscs().run(g, poly.getBounds().x + squareLength / 2
            , poly.getBounds().y + squareLength / 2, squareLength, discColor);
  }

  /**
   * the Y position of the center of the hexagon in relation to the panel it is drawn on.
   *
   * @return the Y position of the hexagon.
   */
  public double getY() {
    return squareCenterCoordY;
  }

  /**
   * the X position of the center of the hexagon in relation to the panel it is drawn on.
   *
   * @return the X position of the hexagon.
   */
  public double getX() {
    return squareCenterCoordX;
  }


  /**
   * The coordinate in Row Col form of the square
   *
   * @return the coordinates of the square.
   */
  @Override
  public Coordinate getLogicalCoord() {
    return this.logicalSquareCord;
  }

  /**
   * Checks if the hex contains the given digital (x,y) point.
   *
   * @param x
   * @param y
   * @return Returns true if the point is inside the hex, false otherwise.
   */
  public boolean containsPoint(int x, int y) {
    return poly.contains(x, y);
  }
}
