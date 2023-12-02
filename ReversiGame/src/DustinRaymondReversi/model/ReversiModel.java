package DustinRaymondReversi.model;

import DustinRaymondReversi.controller.ReversiModelFeatures;

/**
 * Interface for a game of Reversi. Contains functionality for players to make moves, pass turn, and
 * functionality to access relevant game data.
 */
public interface ReversiModel extends ReadOnlyReversiModel {

  /**
   * Places the current player's piece at the specified coordinates in the game, performs the
   * flipping operation on any enemy cells that this move captures, and then switches to the next
   * player whose turn it is.
   *
   * @param c - the given coordinate to move at.
   * @throws IllegalArgumentException if invalid coordinates.
   * @throws IllegalStateException    if move is illegal but coordinates are valid.
   * @throws IllegalStateException    if game is over
   */
  void move(HexPosn c) throws IllegalArgumentException, IllegalStateException;

  /**
   * Passes the current player's turn, switching to the next player whose turn it is. Note: if both
   * players pass in a row without either of them making a move, the game ends.
   *
   * @throws IllegalStateException if game is over.
   */
  void passTurn() throws IllegalStateException;

  /**
   * Subscribes the given listener to this model, so that it is notified whenever certain events
   * occur, such as a player finishing up their turn.
   *
   * @param listener the listener to add.
   */
  void addModelListener(ReversiModelFeatures listener);

  /**
   * Starts a game of Reversi by assigning the player piece type to each player's representative.
   * Before this method is called, there should be two ReversiPlayerActions objects that will
   * represent each player of the game, and startGame will assign a color to each player. The method
   * will then notify the players so that each can make their moves.
   *
   * @throws IllegalStateException if there are not two ReversiPlayerActions objects subscribed as
   *                               listeners to the model already.
   */
  void startGame();
}
