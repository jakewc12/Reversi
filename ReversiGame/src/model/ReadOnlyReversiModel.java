package model;

import java.util.List;

import model.GameDisc.DiscColor;

/**
 * Anything observable about a reversi game should be included here.
 */
public interface ReadOnlyReversiModel {

  /**
   * returns the disc color at the specified coordinate.
   *
   * @param q q radius in relation to the center.
   * @param r r radius in relation to the center.
   * @param s s radius in relation to the center.
   * @return disc at specified location.
   * @throws IllegalStateException    if game hasn't started.
   * @throws IllegalArgumentException coordinates are illegal.
   */
  DiscColor getColorAt(int q, int r, int s);

  /**
   * Returns the color of whos turn it is. This is denoted by DiscColor.
   *
   * @return a DiscColor representing who's turn it currently is.
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
   * @return true if the game is over and false there are legal moves to be played.
   * @throws IllegalStateException if game hasn't started.
   */
  boolean gameOver();

  /**
   * Initializes all values.
   *
   * @param board is the board that will be used to play the game on.
   * @throws IllegalArgumentException if size is negative.
   * @throws IllegalStateException    if the game has already started.
   */
  void startGame(List<HexagonCell> board);

  /**
   * Returns a board in a default initial state based on the game's size.
   * @return List of HexagonCell representing a board at default state.
   */
  List<HexagonCell> getBoard();
}
