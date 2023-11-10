package model;

/**
 * A class meant to represent a hexagonal cell on a reversi board.
 */
public class GameCell implements HexagonCell {

  private final Disc contents;

  private Coordinate coordinate;
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

  public GameCell(Disc contents, Coordinate coordinate) {
    if (contents == null) {
      throw new IllegalArgumentException("Cannot have no disc when creating a game cell");
    }
    this.contents = contents;
    this.coordinate = coordinate;
  }

  private GameCell(int coordinateQ, int coordinateR, int coordinateS) {
    coordinate = new Coordinate(coordinateQ, coordinateR, coordinateS);
    contents = null;
  }

  private int[] cellDirection(Direction direction) {
    return cellDirectionVectors[direction.directionNum];
  }

  /**
   * Gets this neighbors cell in a direction.
   *
   * @param direction the direction you want to get the neighbor from. See Direction enum class for
   *                  descriptions.
   * @return a Cell without contents that has the coordinates of a neighboring cell in direction.
   */
  public GameCell getCellNeighbor(Direction direction) {
    //need to check that neighbor is not off board

    int[] addCell = cellDirection(direction);
    return new GameCell(coordinate.getQ() + addCell[0], coordinate.getR() + addCell[1],
        coordinate.getS() + addCell[2]);
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  /**
   * Gets the contents of this Cell.
   *
   * @return the disc inside this cell.
   */
  @Override
  public Disc cellContents() {
    return contents;
  }

  /**
   * Gets the Q coordinate of this cell.
   *
   * @return an integer that decrease when going left of the origin and increases when going right
   * of the origin.
   */
  @Override
  public int getCoordinateQ() {
    return coordinate.getQ();
  }

  /**
   * Gets the R coordinate of this cell.
   *
   * @return an integer decreases when going up a row from the origin and increases when going down
   * a row.
   */
  @Override
  public int getCoordinateR() {
    return coordinate.getR();
  }

  /**
   * Gets the S coordinate of this cell.
   *
   * @return an integer that decrease when going right of the origin and increases when going left
   * of the origin.
   */
  @Override
  public int getCoordinateS() {
    return coordinate.getS();
  }

  /**
   * returns a String rendering of the GameCell's coordinates.
   *
   * @return a String formatted as a coordinate from Q to R to S.
   */
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
    private int directionNum;

    Direction(int i) {
      directionNum = i;
    }
  }

}
