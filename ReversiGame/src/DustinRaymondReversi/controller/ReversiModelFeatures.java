package DustinRaymondReversi.controller;

import DustinRaymondReversi.model.PlayerPiece;

/**
 * Represents an observer of a Reversi model, which can receive updates about certain changes in the
 * game's state, such as when a player has made their turn. An object that implements this interface
 * is intended to subscribe to a Reversi model.
 */
public interface ReversiModelFeatures {

  /**
   * Notifies this observer that the players in whatever Reversi model it is subscribed to have
   * switched, i.e. the current player has just finished playing their turn.
   */
  void notifyPlayersSwitched();

  /**
   * Initializes the piece associated with the player whose actions are managed through this
   * controller. The piece will only be initialized if it has not been initialized yet; attempting
   * to initialize the piece afterward will throw an error.
   *
   * @param piece the piece to associate with the player
   * @throws IllegalArgumentException if the given piece is null
   * @throws IllegalStateException    if a piece was attempted to be initialized after it already
   *                                  had been, i.e. someone tried to cheat
   */
  void initializePlayerPiece(PlayerPiece piece);
}
