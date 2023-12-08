package model.hexreversi;

import model.Coordinate;
import model.Direction;
import model.Disc;
import model.DiscColor;
import model.GameCell;
import model.GameDisc;

/**
 * Makes a game cell that has Q, R and S coordinates. 0,0,0 is the dead center of the board called
 * the origin which is located halfway down and halfway from the side.
 *
 * <p>Q decrease when going left of the origin and increases when going right of the origin.
 *
 * <p>R decreases when going up a row from the origin and increases when going down a row.
 *
 * <p>S decrease when going right of the origin and increases when going left of the origin.
 *
 * <p>A more detailed explanation is shown here https://www.redblobgames.com/grids/hexagons/.
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
  public Coordinate getCoordinate() {
    return (Coordinate) logicalCoordinate;
  }

  @Override
  public Boolean containsCoordinate(Coordinate otherCoord) {
    if (otherCoord instanceof HexCoordinate a) {
      return logicalCoordinate.equals(a);
    }
    return false;
  }

  @Override
  public Coordinate getCellNeighbor(Direction hexDirections) {
    int[] addCell = hexDirections.vector();
    return new HexCoordinate(logicalCoordinate.getIntQ() + addCell[0],
        logicalCoordinate.getIntR() + addCell[1], logicalCoordinate.getIntS() + addCell[2]);
  }

  @Override
  public Disc cellContents() {
    return contents;
  }

  /**
   * Gets the Q coordinate of this cell. The Q LogicalHexCoordinate decreases when going left of the
   * origin and increases when going right of the origin.
   *
   * @return an integer which follows the above pattern.
   */
  public int getCoordinateQ() {
    return logicalCoordinate.getIntQ();
  }

  /**
   * Gets the R coordinate of this cell. The R LogicalHexCoordinate decreases when going up a row
   * from the origin and increases when going down a row.
   *
   * @return an integer which follows the above pattern.
   */
  public int getCoordinateR() {
    return logicalCoordinate.getIntR();
  }

  /**
   * Gets the S coordinate of this cell. The S LogicalHexCoordinate decreases when going right of
   * the origin and increases when going left of the origin.
   *
   * @return an integer which follows the above pattern.
   */
  public int getCoordinateS() {
    return logicalCoordinate.getIntS();
  }

  @Override
  public String toString() {
    return logicalCoordinate.toString();
  }

}
