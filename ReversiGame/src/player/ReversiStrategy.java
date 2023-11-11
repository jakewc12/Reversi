package player;

import java.util.Optional;
import model.Coordinate;
import model.ReadOnlyReversiModel;

public interface ReversiStrategy {

  /**
   *  When given a model and player the strategy will choose the best move that player can make
   *  according to the strategy.
   * @param model the board the player is playing on.
   * @param who the player who is using the strategy.
   * @return a move if one is found by the strategy, the strategy may be empty if none is found.
   */
  Optional<Coordinate> chooseMove(ReadOnlyReversiModel model, Player who);
}
