package model;

/**
 * An Interface meant to represent either a black or white token in a reversi game.
 */
public interface Disc {

  /**
   * Changes the current color to the opposite.
   */
  void flipColor();

  /**
   * Returns the current color of this disc.
   *
   * @return a string representation of this discs color. BLACK for black, WHITE for white.
   */
  String getColor();
  /**
   * Returns the current color of this disc as a string.
   *
   * @return a string representation of this discs color. BLACK for black, WHITE for white.
   */
  String toString();
}
