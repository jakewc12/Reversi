package DustinRaymondReversi.controller;

import DustinRaymondReversi.model.HexPosn;

/**
 * Represents a set of features (events) characteristic of a Reversi game. These features generally
 * represent actions taken by a player and are used to relay information between the player inputs
 * and the game itself.
 */
public interface ReversiPlayerActions {

  /**
   * Represents the player attempting to make a move at the given position in whatever Reversi game
   * this observer is linked to.
   *
   * @param pos the position that the player is attempting to move at
   * @throws IllegalArgumentException if the position is null
   */
  void attemptMove(HexPosn pos);

  /**
   * Represents the player attempting to pass their turn.
   */
  void attemptPass();
}
