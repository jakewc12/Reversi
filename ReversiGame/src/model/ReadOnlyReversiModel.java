package model;

import model.GameDisc.DiscColor;

/**
 * Anything observable about a reversi game should be included here.
 */
public interface ReadOnlyReversiModel {

  /**
   * returns the disc at the specified coordinate.
   * @param Q q radius in relation to the center.
   * @param R r radius in relation to the center.
   * @param S s radius in relation to the center.
   * @return disc at specified location.
   *
   * @throws IllegalStateException if game hasn't started.
   * @throws IllegalArgumentException coordinates are illegal.
   */
  Disc getDiscAt(int Q, int R, int S);

  /**
   * Returns the color of whos turn it is. This is denoted in all caps for example BLACK for black
   * and WHITE for white.
   *
   * @return a string representing who's turn it currently is.
   * @throws IllegalStateException if game hasn't started.
   */
  DiscColor getCurrentTurn();

  /**
   * Gets the total height of the board.
   *
   * @return the total height of the board including empty cells.
   * @throws IllegalStateException if game hasn't started
   */
  int getBoardSize();

  /**
   * Gets the radius of the board from the center cell, not including it.
   *
   * @return the total width of the board including empty cells.
   * @throws IllegalStateException if game hasn't started.
   */
  int getBoardRadius();

  /**
   * Checks if the game is over by seeing if all the cells are filled or there are new legal moves
   * left.
   *
   * @return true if the game is over and false if the game is not over and there are legal moves.
   * left to be played.
   * @throws IllegalStateException if game hasn't started.
   */
  boolean gameOver();

  /**
   * Initializes all values.
   *
   * @param size is the intended radius of the game, in relation to the center cell.
   * @throws IllegalArgumentException if size is negative.
   * @throws IllegalStateException if the game has already started.
   */
  void startGame(int size);
}
