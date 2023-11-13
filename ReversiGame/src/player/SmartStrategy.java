package player;

import java.util.List;
import java.util.Optional;
import model.Coordinate;
import model.ReadOnlyReversiModel;

/**
 * The SmartStrategy class implements the ReversiStrategy interface and represents a strategy that
 * intelligently selects the best move among a list of strategies. It evaluates the effectiveness of
 * each strategy based on the number of flips it would produce and chooses the one with the maximum
 * flips.
 */
public class SmartStrategy implements ReversiStrategy {

  private List<ReversiStrategy> strategies;

  /**
   * Constructs a SmartStrategy with a list of strategies.
   *
   * @param strategies The list of Reversi strategies to evaluate.
   * @throws IllegalArgumentException If the list of strategies is empty.
   */
  public SmartStrategy(List<ReversiStrategy> strategies) {
    if (strategies.isEmpty()) {
      throw new IllegalArgumentException("Strategies cannot be empty");
    }
    this.strategies = strategies;
  }

  @Override
  public String toString() {
    return "Smarter Strategy";
  }

  /**
   * Chooses the best move among the list of strategies.
   *
   * @param model The ReadOnlyReversiModel representing the current game state.
   * @param who   The Player object representing the player using the strategy.
   * @return An Optional containing the chosen move if found by the strategy or an empty optional.
   */
  @Override
  public Optional<Coordinate> chooseMove(ReadOnlyReversiModel model, Player who) {
    ReversiStrategy bestStrat = strategies.get(0);
    for (ReversiStrategy currentStrategy : strategies) {
      if (bestStrat.chooseMove(model, who).isEmpty()) {
        if (currentStrategy.chooseMove(model, who).isEmpty()) {
          continue;
        } else {
          bestStrat = currentStrategy;
        }
      }
      if (currentStrategy.chooseMove(model, who).isEmpty()) {
        continue;
      }
      if (model.getNumFlipsOnMove(currentStrategy.chooseMove(model, who).get(), who.getColor())
          > model.getNumFlipsOnMove(bestStrat.chooseMove(model, who).get(), who.getColor())) {
        bestStrat = currentStrategy;
      }
    }
    System.out.println(bestStrat);
    return bestStrat.chooseMove(model, who);
  }
}
