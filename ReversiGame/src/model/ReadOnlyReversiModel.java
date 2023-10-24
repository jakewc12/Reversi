package model;

/**
 * Anything observable about a reversi game should be included here.
 */
public interface ReadOnlyReversiModel {

  Disc getDiscAt(int Q, int R, int S);

  /**
   * Returns the color of whos turn it is. This is denoted in all caps for example BLACK for black
   * and WHITE for white.
   *
   * @return a string representing who's turn it currently is.
   */
  String getCurrentTurn();

  /**
   * Gets the total height of the board.
   *
   * @return the total height of the board including empty cells.
   */
  int getBoardHeight();

  /**
   * Gets the total width of the board.
   *
   * @return the total width of the board including empty cells.
   */
  int getBoardWidth();

  /**
   * Checks if the game is over by seeing if all the cells are filled or there are new legal moves
   * left.
   *
   * @return true if the game is over and false if the game is not over and there are legal moves
   * left to be played.
   */
  boolean gameOver();

}
