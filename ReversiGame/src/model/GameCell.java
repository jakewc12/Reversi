package model;

/**
 * Makes a game cell that has Q, R and S coordinates. 0,0,0 is the dead center of the board
 * called the origin which is located halfway down and halfway from the side.
 *
 * <p>Q decrease when going left of the origin and increases when going right of the origin.
 *
 * <p>R decreases when going up a row from the origin and increases when going down a row.
 *
 * <p>S decrease when going right of the origin and increases when going left of the origin.
 *
 * <p>A more detailed explanation is shown below.
 * <p><ahref="https://www.redblobgames.com/grids/hexagons/">...</a>.
 */
public interface GameCell {

  /**
   * Gets the contents of this Cell.
   *
   * @return the disc inside this cell.
   */
  GameCell cellContents();

  /**
   * Gets the Q coordinate of this cell.
   *
   * @return an integer that decrease when going left of the origin and increases when going right
   * of the origin.
   */
  int getQ();

  /**
   * Gets the R coordinate of this cell.
   *
   * @return an integer decreases when going up a row from the origin and increases when going down
   * a row.
   */
  int getR();

  /**
   * Gets the S coordinate of this cell.
   *
   * @return an integer that decrease when going right of the origin and increases when going left
   * of the origin.
   */
  int getS();
}
