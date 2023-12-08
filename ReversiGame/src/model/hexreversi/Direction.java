package model.hexreversi;

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
public enum Direction {
  DEAD_LEFT(0), DEAD_RIGHT(1), TOP_LEFT(2), TOP_RIGHT(3), BOTTOM_LEFT(4), BOTTOM_RIGHT(5);
  final int directionNum;

  Direction(int i) {
    directionNum = i;
  }
}
