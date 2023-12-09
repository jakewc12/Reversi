package digitalviews.squarereversi;

import java.awt.*;
import java.util.Optional;

import digitalviews.CommandPatternHelpers.DrawDiscs;
import digitalviews.CommandPatternHelpers.DrawHints;
import digitalviews.DrawnShape;
import model.Coordinate;
import model.DiscColor;
import model.squarereversi.SquareCoordinate;

/*
TODO:: NEED TO mAKE THIS IMPLEMENT DRAWNSHAPE
 */
public class DrawnSquare implements DrawnShape {
  private final SquareCoordinate logicalSquareCord;
  private final double squareCenterCoordX;
  private final double squareCenterCoordY;
  private final Color color;
  private Color discColor;
  private Optional<Integer> numFlipsOnHex;
  private final double squareLength;

  public DrawnSquare(SquareCoordinate logicalSquareCord, DiscColor discColor
          , double squareCenterCoordX, double squareCenterCoordY
          , Color color, int squareLength, Optional<Integer> numFlipsOnHex) {
    this.logicalSquareCord = logicalSquareCord;
    this.squareCenterCoordX = squareCenterCoordX + logicalSquareCord.getIntQ()* squareLength;
    this.squareCenterCoordY = squareCenterCoordY + logicalSquareCord.getIntR()* squareLength;
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
    Graphics2D g2d = (Graphics2D) g;
    //drawSquare(g2d);
    g2d.setColor(Color.BLACK);
    g2d.drawRect((int)( getX()- squareLength/2), (int) ( getY()- squareLength/2), (int) squareLength, (int) squareLength);
    g2d.setColor(color);
    g2d.fillRect((int) ( getX()- squareLength/2), (int) ( getY()- squareLength/2), (int) squareLength, (int) squareLength);
    drawDisc(g2d);
    numFlipsOnHex.ifPresent(integer -> new DrawHints().draw(g, String.valueOf(integer)
            , (int) (getX()-5)
            , (int) (getY())));
  }

  private void drawDisc(Graphics2D g) {
    if (discColor != Color.WHITE && discColor != Color.BLACK) {
      discColor = color;
    }
    new DrawDiscs().run(g, (int) (getX() - squareLength / 3)
            , (int) (getY() - squareLength / 3), (int) (squareLength/1.5), discColor);
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
    return (squareCenterCoordX - x) < squareLength / 2 && Math.abs(squareCenterCoordY - y) < squareLength / 2;
  }
}
