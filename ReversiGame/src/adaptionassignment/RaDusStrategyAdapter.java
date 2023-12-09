package adaptionassignment;

import dustinraymondreversi.model.HexPosn;
import java.util.List;
import java.util.Optional;
import model.Coordinate;
import player.Player;

/**
 * Adapts our client's strategy interface to our strategy interface.
 */
public class RaDusStrategyAdapter implements player.ReversiStrategy {

  private final dustinraymondreversi.strategy.ReversiStrategy raDusStrategy;

  /**
   * Creates a RaDusStrategyAdapter which takes in our clients strategy and converts it to ours.
   *
   * @param strategy the strategy that should be converted.
   */
  public RaDusStrategyAdapter(dustinraymondreversi.strategy.ReversiStrategy strategy) {
    raDusStrategy = strategy;
  }

  @Override
  public Optional<Coordinate> chooseMove(model.ReadOnlyReversiModel model, Player who) {
    RaDusModelAdapter adapter = new RaDusModelAdapter(model.getBoardRadius());
    adapter.setUpGame(model);
    List<HexPosn> possibleMoves = raDusStrategy.chooseMoves(adapter);

    if (possibleMoves.isEmpty()) {
      return Optional.empty();
    }
    //need to check that the move is legal for this player.

    for (HexPosn coordinate : possibleMoves) {
      if (model.getNumFlipsOnMove(new HexPosToLogicalHexCoordinate(coordinate),
          who.getPlayerColor())
          > 0) {
        return Optional.of(new HexPosToLogicalHexCoordinate(coordinate));
      }
    }
    return Optional.empty();
  }
}
