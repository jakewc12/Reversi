package model;

/**
 * A class meant to represent a hexagonal cell on a reversi board.
 */
public class GameCell implements HexagonCell {

  private final Disc contents;

  private final LogicalCoordinate logicalCoordinate;
  private final int[][] cellDirectionVectors = {{-1, 0, 1}, //DEADLEFT(0)
      {1, 0, -1}, //DEAD-RIGHT(1)
      {0, -1, 1}, //TOP-LEFT(2)
      {1, -1, 0}, //TOP-RIGHT(3)
      {-1, +1, 0}, //BOTTOM-LEFT(4)
      {0, 1, -1}}; //BOTTOM-RIGHT(5)


  /**
   * Creates a new Game Cell which can hold Discs.
   *
   * @param contents   the contents of the cell which can be null or a class from the disc
   *                   interface.
   * @param logicalCoordinate the game cells logicalCoordinate.
   */

  public GameCell(DiscColor contents, LogicalCoordinate logicalCoordinate) {
    if (contents == null) {
      throw new IllegalArgumentException("Cannot have no disc when creating a game cell");
    }
    this.contents = new GameDisc(contents);
    this.logicalCoordinate = logicalCoordinate;
  }


  private int[] cellDirection(Direction direction) {
    return cellDirectionVectors[direction.directionNum];
  }

  @Override
  public LogicalCoordinate getCellNeighbor(Direction direction) {
    //need to check that neighbor is not off board

    int[] addCell = cellDirection(direction);
    return new Coordinate(
        logicalCoordinate.getIntQ() + addCell[0], logicalCoordinate.getIntR() + addCell[1],
        logicalCoordinate.getIntS() + addCell[2]);
  }

  @Override
  public LogicalCoordinate getCoordinate() {
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
