package DustinRaymondReversi.strategy;

import DustinRaymondReversi.model.HexPosn;
import DustinRaymondReversi.model.ReadOnlyReversiModel;
import java.util.List;

/**
 * Represents a strategy used to select cells to play at in a Reversi game. The interface contains a
 * single method that returns a list of HexPosns (in axial coordinates) representing the best cells
 * in the Reversi game (provided as a read-only Reversi model) at which to place a piece for the
 * current player in the game, based on some kind of criteria for deciding which moves would be best
 * for the player to perform.
 */
public interface ReversiStrategy {

  /**
   * Considers a given game of Reversi and decides on ideal moves for the current player.
   *
   * @param game the Reversi game to check
   * @return a list of candidate positions to play at, empty list if no move is possible
   * @throws IllegalArgumentException if the game is null
   */
  List<HexPosn> chooseMoves(ReadOnlyReversiModel game);
}
