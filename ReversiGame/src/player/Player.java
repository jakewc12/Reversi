package player;

import model.DiscColor;
import model.MutableReversiModel;

/**
 * The Player interface defines the common behavior for players in a Reversi game. Implementations
 * of this interface represent players with a specific disc color.
 */
public interface Player {

  /**
   * Returns the disc color associated with this player.
   *
   * @return The disc color (WHITE or BLACK) associated with the player.
   */
  DiscColor getPlayerColor();

  /**
   * Plays a move on the MutableReversiModel based on the player's input.
   *
   * @param model The MutableReversiModel representing the current game state.
   */
  void playMove(MutableReversiModel model);
}
