package model.hexreversi;

import model.Disc;
import model.DiscColor;
import model.GameCell;
import model.GameDisc;

/**
 * A class meant to represent a hexagonal cell on a reversi board.
 */
public class HexCell implements GameCell {

  private final Disc contents;

  private final LogicalHexCoordinate logicalCoordinate;



  /**
   * Creates a new Game Cell which can hold Discs.
   *
   * @param contents          the contents of the cell which can be null or a class from the disc
   *                          interface.
   * @param logicalCoordinate the game cells logicalCoordinate.
   */

  public HexCell(DiscColor contents, LogicalHexCoordinate logicalCoordinate) {
    if (contents == null) {
      throw new IllegalArgumentException("Cannot have no disc when creating a game cell");
    }
    this.contents = new GameDisc(contents);
    this.logicalCoordinate = logicalCoordinate;
  }

  @Override
  public LogicalHexCoordinate getCellNeighbor(HexDirections hexDirections) {
    //need to check that neighbor is not off board

    int[] addCell = hexDirections.vector;
    return new HexCoordinate(
        logicalCoordinate.getIntQ() + addCell[0], logicalCoordinate.getIntR() + addCell[1],
        logicalCoordinate.getIntS() + addCell[2]);
  }

  @Override
  public LogicalHexCoordinate getCoordinate() {
    return logicalCoordinate;
  }

  @Override
  public Disc cellContents() {
    return contents;
  }

  @Override
  public int getCoordinateQ() {
    return logicalCoordinate.getIntQ();
  }

  @Override
  public int getCoordinateR() {
    return logicalCoordinate.getIntR();
  }


  @Override
  public int getCoordinateS() {
    return logicalCoordinate.getIntS();
  }

  @Override
  public String toString() {
    return logicalCoordinate.toString();
  }

}
