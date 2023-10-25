package model;

/**
 * A class meant to represent a hexagonal cell on a reversi board.
 */
public class GameCell implements HexagonCell {

  private final Disc contents;
  private final int coordinateQ;
  private final int coordinateR;
  private final int coordinateS;

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
}
