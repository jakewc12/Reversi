package model;


import model.hexreversi.Direction;
import model.hexreversi.LogicalHexCoordinate;

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
public interface GameCell {

  /**
   * Gets the contents of this Cell.
   *
   * @return the disc inside this cell.
   */
  Disc cellContents();

  /**
   * Gets the coordinate of this cell.
   *
   * @return a coordinate that contains q,r,s.
   */
  LogicalHexCoordinate getCoordinate();

  /**
   * Gets the Q coordinate of this cell. The Q LogicalHexCoordinate decreases when going left of the
   * origin and increases when going right of the origin.
   *
   * @return an integer which follows the above pattern.
   */
  int getCoordinateQ();

  /**
   * Gets the R coordinate of this cell. The R LogicalHexCoordinate decreases when going up a row from
   * the origin and increases when going down a row.
   *
   * @return an integer which follows the above pattern.
   */
  int getCoordinateR();

  /**
   * Gets the S coordinate of this cell. The S LogicalHexCoordinate decreases when going right of the
   * origin and increases when going left of the origin.
   *
   * @return an integer which follows the above pattern.
   */
  int getCoordinateS();

  /**
   * Gets this neighbors cell in a direction.
   *
   * @param direction the direction you want to get the neighbor from. See Direction enum class for
   *                  descriptions.
   * @return a Cell without contents that has the coordinates of a neighboring cell in direction.
   */
  LogicalHexCoordinate getCellNeighbor(Direction direction);
}
