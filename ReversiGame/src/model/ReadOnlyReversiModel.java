package model;

import java.util.List;
import model.hexreversi.LogicalHexCoordinate;

/**
 * Anything observable about a reversi game should be included here.
 */
public interface ReadOnlyReversiModel {

  /**
   * returns the disc color at the specified logicalCoordinate.
   *
   * @param logicalCoordinate the coordinates for what color you want to get.
   * @return disc at specified location.
   * @throws IllegalStateException    if game hasn't started.
   * @throws IllegalArgumentException coordinates are illegal.
   */
  DiscColor getColorAt(LogicalHexCoordinate logicalCoordinate);

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
  void setUpGame(List<GameCell> board);

  /**
   * Returns a board in a default initial state based on the game's size.
   *
   * @return List of GameCell representing a board at default state.
   */
  List<GameCell> getBoard();

  /**
   * Checks if a move placed at (q,r,s) is legal and returns that.
   *
   * @param logicalCoordinate the logicalCoordinate of the move.
   * @return if the given move is legal or not.
   * @throws IllegalArgumentException if the coordinates are invalid.
   * @throws IllegalStateException    if the game hasn't started yet.
   */

  boolean isLegalMove(LogicalHexCoordinate logicalCoordinate);

  /**
   * Checks if the current player has any legal moves and returns that.
   *
   * @return if the current player has any legal moves left.
   * @throws IllegalStateException if game hasn't started.
   */
  boolean checkCurrentPlayerHasLegalMovesLeft();


  /**
   * Returns the score or count of tiles the given player has.
   *
   * @param color either BLACK or WHITE.
   * @return the score of player.
   * @throws IllegalStateException    if the game hasn't started yet.
   * @throws IllegalArgumentException if player is invalid.
   */
  int checkScoreOfPlayer(DiscColor color);

  /**
   * Gets the number of flips on a player move.
   *
   * @param logicalCoordinate The logicalCoordinate you want to place a Disc on.
   * @param playerColor       The color of the player who is placing the disc
   * @return the number of discs flipped if the player makes that move.
   */
  int getNumFlipsOnMove(LogicalHexCoordinate logicalCoordinate, DiscColor playerColor);

  /**
   * Gets all the coordinates for the current board.
   *
   * @return all the possible coordinates.
   */
  List<LogicalHexCoordinate> getAllCoordinates();

  /**
   * Begins the game and allows for moves to be made.
   */
  void startGame();
}

