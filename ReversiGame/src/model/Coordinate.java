package model;

public interface Coordinate {
  /**
   * Gets the Q coordinate. The Q LogicalHexCoordinate decreases when going left of the origin and
   * increases when going right of the origin. For square, represents the column.
   *
   * @return an integer which follows the above pattern.
   */
  int getIntQ();

  /**
   * Gets the R coordinate. The R LogicalHexCoordinate decreases when going up a row from the origin
   * and increases when going down a row. For square, represents the column
   *
   * @return an integer which follows the above pattern.
   */
  int getIntR();

  String toString();

  boolean equals(Object a);
}
