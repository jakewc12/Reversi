package model.hexreversi;

import model.Direction;

/**
 * Meant to simulate directions in relation to a hexagon cell. Directions are:
 *
 * <p>TOP_LEFT for positive s direction static q direction.
 *
 * <p>BOTTOM_RIGHT for negative s direction static q direction.
 *
 * <p>TOP_RIGHT for positive q direction static s direction.
 *
 * <p>BOTTOM_LEFT for negative q direction static s direction.
 *
 * <p>DEAD_LEFT for negative q direction static r direction.
 *
 * <p>DEAD_RIGHT for positive q direction static r direction.
 */
public enum HexDirections implements Direction {
  DEAD_LEFT(new int[]{-1, 0, 1}), DEAD_RIGHT(new int[]{1, 0, -1}), TOP_LEFT(
      new int[]{0, -1, 1}), TOP_RIGHT(new int[]{1, -1, 0}), BOTTOM_LEFT(
      new int[]{-1, +1, 0}), BOTTOM_RIGHT(new int[]{0, 1, -1});
  final int[] vector;

  HexDirections(int[] i) {
    vector = i;
  }

  @Override
  public int[] vector() {
    return this.vector;
  }
}
