package model;

/**
 * A class meant to represent a hexagonal cell on a reversi board.
 */
public class GameCell implements HexagonCell {

  private final Disc contents;
  private final int coordinateQ;
  private final int coordinateR;
  private final int coordinateS;

  private final int[][] cellDirectionVectors = {{+1, 0, -1}, // Dead left0
      {+1, -1, 0}, //Top left1
      {0, 1, -1}, //BottomLeft2
      {-1, 0, +1}, // Dead right3
      {-1, +1, 0}, // Bottom Right4
      {0, -1, +1},}; // Top Right5

  private int[] cell_Direction(Direction direction) {
    return cellDirectionVectors[direction.directionNum];
  }

  public GameCell getCellNeighbor(Direction direction) {
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

  public enum Direction {
    TOPLEFT(1), DEADLEFT(0), BOTTOMLEFT(2), TOPRIGHT(5), DEADRIGHT(3), BOTTOMRIGHT(4);
    protected int directionNum;

    Direction(int i) {
      directionNum = i;
    }
  }

}
