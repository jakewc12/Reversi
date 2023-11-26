package model;

/**
 * A class meant to represent a hexagonal cell on a reversi board.
 */
public class GameCell implements HexagonCell {

  private final Disc contents;

  private final Coordinate coordinate;
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
   * @param coordinate the game cells coordinate.
   */

  public GameCell(DiscColor contents, Coordinate coordinate) {
    if (contents == null) {
      throw new IllegalArgumentException("Cannot have no disc when creating a game cell");
    }
    this.contents = new GameDisc(contents);
    this.coordinate = coordinate;
  }


  private int[] cellDirection(Direction direction) {
    return cellDirectionVectors[direction.directionNum];
  }

  @Override
  public Coordinate getCellNeighbor(Direction direction) {
    //need to check that neighbor is not off board

    int[] addCell = cellDirection(direction);
    return new Coordinate(coordinate.getIntQ() + addCell[0], coordinate.getIntR() + addCell[1],
        coordinate.getIntS() + addCell[2]);
  }

  @Override
  public Coordinate getCoordinate() {
    return coordinate;
  }

  @Override
  public Disc cellContents() {
    return contents;
  }

  @Override
  public int getCoordinateQ() {
    return coordinate.getIntQ();
  }

  @Override
  public int getCoordinateR() {
    return coordinate.getIntR();
  }


  @Override
  public int getCoordinateS() {
    return coordinate.getIntS();
  }

  @Override
  public String toString() {
    return coordinate.toString();
  }

  /**
   * Meant to simulate directions in relation to a hexagon cell. Directions are:
   *
   * <p>TOP_LEFT for positive s direction static q direction.
   *
   * <p>BOTTOM_RIGHT for negative s direction static q direction.
   *
   * <p>TOP_RIGHT for positive q direction static s direction.
   *
   * <p>BOTTOM_LEFT for negative q direction static s direction.
   *
   * <p>DEAD_LEFT for negative q direction static r direction.
   *
   * <p>DEAD_RIGHT for positive q direction static r direction.
   */
  public enum Direction {
    DEAD_LEFT(0), DEAD_RIGHT(1), TOP_LEFT(2), TOP_RIGHT(3), BOTTOM_LEFT(4), BOTTOM_RIGHT(5);
    private final int directionNum;

    Direction(int i) {
      directionNum = i;
    }
  }

}
