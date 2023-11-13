package player;

import java.util.List;
import java.util.Optional;
import model.Coordinate;
import model.ReadOnlyReversiModel;

/**
 *
 */
public class SmartStrategy implements ReversiStrategy {

  private List<ReversiStrategy> strategies;

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
