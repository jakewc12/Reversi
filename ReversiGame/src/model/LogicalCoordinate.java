package model;

public interface LogicalCoordinate {

  /**
   * Gets the Q coordinate. The Q LogicalCoordinate decreases when going left of the origin and
   * increases when going right of the origin.
   *
   * @return an integer which follows the above pattern.
   */
  int getIntQ();

  /**
   * Gets the R coordinate. The R LogicalCoordinate decreases when going up a row from the origin
   * and increases when going down a row.
   *
   * @return an integer which follows the above pattern.
   */
  int getIntR();

  /**
   * Gets the S coordinate. The S LogicalCoordinate decreases when going right of the origin and
   * increases when going left of the origin.
   *
   * @return an integer which follows the above pattern.
   */
  int getIntS();
}
