package model.player;

import model.DiscColor;
import model.MutableReversiModel;
import model.Position;

public interface Player {

  /**
   * Returns the disc color associated with this player.
   * @return the disc color, WHITE or BLACK associated with the player.
   */
  DiscColor getColor();

  /**
   * Finds the coordinate that aligns best with a specific ReversiStrategy.
   * @param model the Reversi game that is being played on.
   * @return Position of the best placed disc based on a strategy.
   */
  Position play(MutableReversiModel model);
}
