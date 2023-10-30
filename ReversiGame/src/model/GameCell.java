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


  private int[] cell_Direction(Direction direction) {
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

    int[] addCell = cell_Direction(direction);
    return new GameCell(coordinateQ + addCell[0], coordinateR + addCell[1],
        coordinateS + addCell[2]);
  }


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

  @Override
  public Disc cellContents() {
    return contents;
  }

  @Override
  public int getCoordinateQ() {
    return coordinateQ;
  }

  @Override
  public int getCoordinateR() {
    return coordinateR;
  }

  @Override
  public int getCoordinateS() {
    return coordinateS;
  }

  @Override
  public String toString() {
    return "(" + coordinateQ + ", " + coordinateR + ", " + coordinateS + ")";
  }

  /**
   * Meant to simulate directions in relation to a hexagon cell. Directions are:
   *
   * <p>TOPLEFT for positive s direction static q direction.
   *
   * <p>BOTTOMRIGHT for negative s direction static q direction.
   *
   * <p>TOPRIGHT for positive q direction static s direction.
   *
   * <p>BOTTOMLEFT for negative q direction static s direction.
   *
   * <p>DEADLEFT for negative q direction static r direction.
   *
   * <p>DEADRIGHT for positive q direction static r direction.
   */
  public enum Direction {
    DEAD_LEFT(0), DEAD_RIGHT(1), TOP_LEFT(2), TOP_RIGHT(3), BOTTOM_LEFT(4), BOTTOM_RIGHT(5);
    protected int directionNum;

    Direction(int i) {
      directionNum = i;
    }
  }

}
