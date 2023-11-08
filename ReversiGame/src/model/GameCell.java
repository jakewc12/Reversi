package model;

/**
 * A class meant to represent a hexagonal cell on a reversi board.
 */
public class GameCell implements HexagonCell {

  private final Disc contents;
  private final int coordinateQ;
  private final int coordinateR;
  private final int coordinateS;
  private final int[][] cellDirectionVectors = {{-1, 0, 1}, //DEADLEFT(0)
      {1, 0, -1}, //DEAD-RIGHT(1)
      {0, -1, 1}, //TOP-LEFT(2)
      {1, -1, 0}, //TOP-RIGHT(3)
      {-1, +1, 0}, //BOTTOM-LEFT(4)
      {0, 1, -1}}; //BOTTOM-RIGHT(5)


  /**
   * Creates a new Game Cell which can hold Discs.
   *
   * @param contents    the contents of the cell which can be null or a class from the disc
   *                    interface.
   * @param coordinateQ an integer that decrease when going left of the origin and increases when
   *                    going right of the origin.
   * @param coordinateR an integer decreases when going up a row from the origin and increases when
   *                    going down a row.
   * @param coordinateS the coordinate that decrease when going right of the origin and increases *
   *                    when going left of the origin.
   */
  public GameCell(Disc contents, int coordinateQ, int coordinateR, int coordinateS) {
    if (contents == null) {
      throw new IllegalArgumentException("Cannot have no disc when creating a game cell");
    }
    this.contents = contents;
    this.coordinateQ = coordinateQ;
    this.coordinateR = coordinateR;
    this.coordinateS = coordinateS;
  }

  private GameCell(int coordinateQ, int coordinateR, int coordinateS) {
    this.coordinateQ = coordinateQ;
    this.coordinateR = coordinateR;
    this.coordinateS = coordinateS;
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
    return new GameCell(coordinateQ + addCell[0], coordinateR + addCell[1],
        coordinateS + addCell[2]);
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
   *     of the origin.
   */
  @Override
  public int getCoordinateQ() {
    return coordinateQ;
  }

  /**
   * Gets the R coordinate of this cell.
   *
   * @return an integer decreases when going up a row from the origin and increases when going down
   *     a row.
   */
  @Override
  public int getCoordinateR() {
    return coordinateR;
  }

  /**
   * Gets the S coordinate of this cell.
   *
   * @return an integer that decrease when going right of the origin and increases when going left
   *      of the origin.
   */
  @Override
  public int getCoordinateS() {
    return coordinateS;
  }

  /**
   * returns a String rendering of the GameCell's coordinates.
   *
   * @return a String formatted as a coordinate from Q to R to S.
   */
  @Override
  public String toString() {
    return "(" + coordinateQ + ", " + coordinateR + ", " + coordinateS + ")";
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
