package model.squarereversi;

import model.Disc;
import model.DiscColor;
import model.GameDisc;

/**
 * A class meant to represent a hexagonal cell on a reversi board.
 */
public class SquareCell {

  private final Disc contents;

  private final SquareCoordinate logicalCoordinate;


  /**
   * Creates a new Game Cell which can hold Discs.
   *
   * @param contents          the contents of the cell which can be null or a class from the disc
   *                          interface.
   * @param logicalCoordinate the game cells logicalCoordinate.
   */

  public SquareCell(DiscColor contents, SquareCoordinate logicalCoordinate) {
    if (contents == null) {
      throw new IllegalArgumentException("Cannot have no disc when creating a game cell");
    }
    this.contents = new GameDisc(contents);
    this.logicalCoordinate = logicalCoordinate;
  }

  public SquareCoordinate getCellNeighbor(SquareDirections hexDirections) {
    //need to check that neighbor is not off board

    int[] addCell = hexDirections.vector;
    return new SquareCoordinate(logicalCoordinate.getRow() + addCell[0],
        logicalCoordinate.getCol() + addCell[1]);
  }

  public SquareCoordinate getCoordinate() {
    return logicalCoordinate;
  }

  public Disc cellContents() {
    return contents;
  }

  public String toString() {
    return logicalCoordinate.toString();
  }

}
