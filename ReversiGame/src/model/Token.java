package model;

/**
 * A Interface meant to represent either a black or white token in a reversi game.
 */
public interface Token {
  void flipColor();
  String getColor();
  String toString();
}
