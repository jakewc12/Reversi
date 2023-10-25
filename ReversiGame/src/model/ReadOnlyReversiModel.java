package model;

import model.GameDisc.DiscColor;

/**
 * Anything observable about a reversi game should be included here.
 */
public interface ReadOnlyReversiModel {

  Disc getDiscAt(int Q, int R, int S);

  /**
   * Returns the color of whose turn it is.
   *
   * @return a DiscColor representing which colors turn it is. For example if its whites turn it
   * would return DiscColor.WHITE.
   */
  DiscColor getCurrentTurn();

  /**
   * Gets the total width of the board.
   *
   * @return the total width of the board including empty cells.
   */
  int getBoardSize();

  /**
   * Gets the radius from the center of the board to an outside edge without including the center
   * tile.
   *
   * @return the total width of the board including empty cells.
   */
  int getBoardRadius();

  /**
   * Checks if the game is over by seeing if all the cells are filled or there are new legal moves
   * left.
   *
   * @return true if the game is over and false if the game is not over and there are legal moves
   * left to be played.
   */
  boolean gameOver();

  /**
   * Starts the game of reversi by initializing all the values and creating a board of radius size
   * not including the origin. For example if a size of 4 was inputted then the total board size
   * would be 11. Start game also sets the first player to black.
   */
  void startGame(int size);
}
