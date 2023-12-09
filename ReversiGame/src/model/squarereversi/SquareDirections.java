package model.squarereversi;

import model.Direction;

/**
 * Meant to simulate directions in relation to a Square cell. Directions are:
 *
 * <p>DEAD_LEFT for neutral row, negative col.
 *
 * <p>TOP_LEFT for positive row, negative col.
 *
 * <p>BOTTOM_LEFT for negative row, negative col.
 *
 * <p>DEAD_RIGHT for neutral row, negative col.
 *
 * <p>TOP_RIGHT for positive row, negative col.
 *
 * <p>BOTTOM_RIGHT for negative row, negative col.
 *
 * <p>ABOVE for positive row, neutral col.
 *
 * <p>BELOW for negative row, neutral col.
 */
public enum SquareDirections implements Direction {

  DEAD_LEFT(new int[]{0, -1}), TOP_LEFT(new int[]{1, -1}), BOTTOM_LEFT(
      new int[]{-1, -1}), DEAD_RIGHT(new int[]{0, 1}), TOP_RIGHT(
      new int[]{1, 1}), BOTTOM_RIGHT(new int[]{-1, 1}), ABOVE(new int[]{1, 0}), BELOW(
      new int[]{-1, 0});
  final int[] vector;

  SquareDirections(int[] i) {
    vector = i;
  }

  @Override
  public int[] vector() {
    return this.vector;
  }
}
