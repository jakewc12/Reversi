package model.hexreversi;

import model.Coordinate;

/**
 * Represents a hex coordinate representation using the Q,R,S system.
 */
public interface LogicalHexCoordinate extends Coordinate {

  /**
   * Gets the Q coordinate. The Q LogicalHexCoordinate decreases when going left of the origin and
   * increases when going right of the origin.
   *
   * @return an integer which follows the above pattern.
   */
  int getIntQ();

  /**
   * Gets the R coordinate. The R LogicalHexCoordinate decreases when going up a row from the origin
   * and increases when going down a row.
   *
   * @return an integer which follows the above pattern.
   */
  int getIntR();

  /**
   * Gets the S coordinate. The S LogicalHexCoordinate decreases when going right of the origin and
   * increases when going left of the origin.
   *
   * @return an integer which follows the above pattern.
   */
  int getIntS();
}
