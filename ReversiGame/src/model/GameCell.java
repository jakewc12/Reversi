package model;


import model.hexreversi.HexDirections;
import model.hexreversi.LogicalHexCoordinate;

/**
 * Meant as a template for a generic game cell.
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
  Coordinate getCoordinate();

  /**
   * Gets the coordinate of this cell.
   *
   * @return a coordinate that contains q,r,s.
   */
  Boolean containsCoordinate(Coordinate otherCoord);

  /**
   * Gets this neighbors cell in a hexDirections.
   *
   * @param hexDirections the hexDirections you want to get the neighbor from. See HexDirections enum class for
   *                  descriptions.
   * @return a Cell without contents that has the coordinates of a neighboring cell in hexDirections.
   */
  Coordinate getCellNeighbor(Direction hexDirections);
}
