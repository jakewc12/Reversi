package model.squarereversi;

import model.Coordinate;
import model.Direction;
import model.Disc;
import model.DiscColor;
import model.GameCell;
import model.GameDisc;

/**
 * The {@code SquareCell} class represents a hexagonal cell on a Reversi board. It holds a Disc
 * object and has a logical coordinate on the game board.
 */
public class SquareCell implements GameCell {

  private final Disc contents;
  private final SquareCoordinate logicalCoordinate;

  /**
   * Creates a new Game Cell which can hold Discs.
   *
   * @param contents          the contents of the cell, which can be null or a class from the Disc
   *                          interface.
   * @param logicalCoordinate the game cell's logical coordinate.
   * @throws IllegalArgumentException if contents is null.
   */

  public SquareCell(DiscColor contents, SquareCoordinate logicalCoordinate) {
    if (contents == null) {
      throw new IllegalArgumentException("Cannot have no disc when creating a game cell");
    }
    this.contents = new GameDisc(contents);
    this.logicalCoordinate = logicalCoordinate;
  }

  /**
   * Creates a new Game Cell which can hold Discs.
   *
   * @param contents the contents of the cell, which can be null or a class from the Disc
   *                 interface.
   * @param row      the row coordinate of the game cell.
   * @param col      the column coordinate of the game cell.
   * @throws IllegalArgumentException if contents is null.
   */
  public SquareCell(DiscColor contents, int row, int col) {
    if (contents == null) {
      throw new IllegalArgumentException("Cannot have no disc when creating a game cell");
    }
    this.contents = new GameDisc(contents);
    this.logicalCoordinate = new SquareCoordinate(row, col);
  }

  @Override
  public Coordinate getCellNeighbor(Direction hexDirections) {
    //need to check that neighbor is not off board

    int[] addCell = hexDirections.vector();
    return new SquareCoordinate(logicalCoordinate.getIntR() + addCell[0],
        logicalCoordinate.getIntQ() + addCell[1]);
  }

  public SquareCoordinate getCoordinate() {
    return logicalCoordinate;
  }

  @Override
  public Boolean containsCoordinate(Coordinate otherCoord) {
    return null;
  }

  public Disc cellContents() {
    return contents;
  }

  public String toString() {
    return logicalCoordinate.toString();
  }

}
