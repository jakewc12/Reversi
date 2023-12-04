package DustinRaymondReversi.model;

import java.util.Objects;

/**
 * Class representation of a Hexagonal coordinate. Using Axial LogicalCoordinate representation (2 axes)
 */
public class HexPosn {
  /**
   * Represents the 6 directions of every neighboring hexagonal coordinate.
   */
  public static enum NeighborDirection {
    RIGHT,
    TOP_RIGHT,
    TOP_LEFT,
    LEFT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT;
  }

  private final int r;
  private final int q;

  public HexPosn(int q, int r) {
    this.r = r;
    this.q = q;
  }

  /**
   * Get the r coordinate of the model.HexPosn.
   * @return - the r coordinate.
   */
  public int getR() {
    return this.r;
  }

  /**
   * Get the q coordinate of the model.HexPosn.
   * @return - the q coordinate.
   */
  public int getQ() {
    return this.q;
  }

  /**
   * // returns a new model.HexPosn with all coordinates set to 0.
   * @return the origin
   */
  public static HexPosn origin() {
    return new HexPosn(0, 0);
  }

  /**
   * The coordinate of the given neighbor.
   * @param direction - the NeighborDirection enum representing the direction of the neighbor.
   * @return - the model.HexPosn of the given neighbor.
   */
  public HexPosn getNeighbor(NeighborDirection direction) {
    switch (direction) {
      case RIGHT:
        return new HexPosn(this.q + 1, this.r);
      case TOP_RIGHT:
        return new HexPosn(this.q + 1, this.r - 1);
      case TOP_LEFT:
        return new HexPosn(this.q, this.r - 1);
      case LEFT:
        return new HexPosn(this.q - 1, this.r);
      case BOTTOM_LEFT:
        return new HexPosn(this.q - 1, this.r + 1);
      case BOTTOM_RIGHT:
        return new HexPosn(this.q, this.r + 1);
      default:
        throw new IllegalArgumentException("invalid NeighborDirection");
    }
  }

  /**
   * Gets the distance between the two given HexPosns using the formula for
   * distance of axial coordinates.
   * For more information, visit:
   * <a href="https://www.redblobgames.com/grids/hexagons/#distances">...</a>
   * under the Axial Coordinates section.
   * @param h1 - the first HexPosn
   * @param h2 - the second HexPosn
   * @return the distance between the two HexPosns
   */
  public static double distance(HexPosn h1, HexPosn h2) {
    return (Math.abs(h1.q - h2.q)
            + Math.abs(h1.q + h1.r - h2.q - h2.r)
            + Math.abs(h1.r - h2.r)) / 2.;
  }


  @Override
  public boolean equals(Object other) {
    if (other instanceof HexPosn) {
      HexPosn otherHexPosn = (HexPosn) other;
      return this.r == otherHexPosn.r && this.q == otherHexPosn.q;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(r, q);
  }

  @Override
  public String toString() {
    return "(q: " + this.q + ", r: " + this.r + ")";
  }
}
