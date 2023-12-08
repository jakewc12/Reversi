package digitalviews.squarereversi;

import java.awt.*;
import java.util.Optional;

import model.squarereversi.SquareCoordinate;

public class DrawnSquare {
  private Polygon poly;
  private final SquareCoordinate logicalSquareCord;
  private final double squareCenterCoordX;
  private final double squareCenterCoordY;
  private final Color color;
  private final Color discColor;
  private Optional<Integer> numFlipsOnHex;

  public DrawnSquare(Polygon poly, SquareCoordinate logicalSquareCord
          , double squareCenterCoordX, double squareCenterCoordY
          , Color color, Color discColor, Optional<Integer> numFlipsOnHex) {
    this.poly = poly;
    this.logicalSquareCord = logicalSquareCord;
    this.squareCenterCoordX = squareCenterCoordX;
    this.squareCenterCoordY = squareCenterCoordY;
    this.color = color;
    this.discColor = discColor;
    this.numFlipsOnHex = numFlipsOnHex;
  }
}
