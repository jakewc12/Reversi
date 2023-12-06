package dustinraymondreversi.player;

import dustinraymondreversi.controller.ReversiPlayerActions;

/**
 * Represents an entity playing a game of Reversi. This entity can either be a human player or an AI
 * player that chooses moves automatically according to some strategy.
 */
public interface ReversiPlayer {

  /**
   * Adds the given player action observer as a listener for this player, which means that observer
   * will be notified when the player attempts an action.
   *
   * @param listener the listener to add
   * @throws IllegalArgumentException if the parameter is null
   */
  void addListener(ReversiPlayerActions listener);

  /**
   * Decides on some action to perform in the current Reversi game and attempts to perform it by
   * relaying the information back to the Reversi controller, if doing so is necessary for the
   * player to perform a move.
   */
  void performGameAction();
}
