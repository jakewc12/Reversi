package model;

import model.GameDisc.DiscColor;

/**
 * An Interface meant to represent either a black or white token in a reversi game.
 */
public interface Disc {

  /**
   * Changes the current color to the provided one.
   */
  void changeColorTo(DiscColor color);

  /**
   * Changes the current color to the opposite. if the color is grey it throws an error
   */
  void flipDisc();

  /**
   * Returns the current color of this disc.
   *
   * @return a string representation of this discs color. BLACK for black, WHITE for white.
   */
  DiscColor getColor();

  /**
   * Returns the current color of this disc as a string.
   *
   * @return a string representation of this discs color. BLACK for black, WHITE for white.
   */
  String toString();
}
