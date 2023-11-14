package player;

import model.DiscColor;
import model.MutableReversiModel;

/**
 * Anything related to the players of reversi.
 */
public interface Player {

  /**
   * Returns the disc color associated with this player.
   * @return the disc color, WHITE or BLACK associated with the player.
   */
  DiscColor getColor();

  /**
   * Finds the coordinate that aligns best with a specific ReversiStrategy.
   * @param model the Reversi game that is being played on.
   */
  void playMove(MutableReversiModel model);
}
