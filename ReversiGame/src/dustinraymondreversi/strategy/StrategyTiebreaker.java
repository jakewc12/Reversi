package dustinraymondreversi.strategy;

import dustinraymondreversi.model.HexPosn;
import dustinraymondreversi.model.ReadOnlyReversiModel;
import java.util.Optional;

/**
 * Represents a way of "breaking ties" by choosing one particular move among the (potentially
 * numerous) moves that might be returned by a particular Reversi strategy. If the strategy fails to
 * return any moves, the tiebreaker returns an empty value; otherwise, it picks one of the values
 * returned by the strategy and returns that one according to some particular rule.
 */
public interface StrategyTiebreaker {

  /**
   * Picks one particular move among all the possible moves that are returned by the given strategy.
   * If the strategy fails to find any moves, returns an empty.
   *
   * @param strategy the strategy to consult
   * @param model    the board that the strategy and the tiebreaker check
   * @return a move that is acceptable, or empty if the strategy cannot find a move
   * @throws IllegalArgumentException if the parameters are invalid
   */
  Optional<HexPosn> chooseOneMove(ReversiStrategy strategy, ReadOnlyReversiModel model);
}
