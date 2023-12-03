package adaptation_assignment;

import DustinRaymondReversi.model.HexPosn;
import java.util.List;
import java.util.Optional;
import model.Coordinate;
import player.Player;

public class RaDusStrategyAdapter implements player.ReversiStrategy {

  private DustinRaymondReversi.strategy.ReversiStrategy raDusStrategy;

  public RaDusStrategyAdapter(DustinRaymondReversi.strategy.ReversiStrategy strategy) {
    raDusStrategy = strategy;
  }

  @Override
  public Optional<Coordinate> chooseMove(model.ReadOnlyReversiModel model, Player who) {
    RaDusModelAdapter adapter = new RaDusModelAdapter(model.getBoardSize());
    adapter.setUpGame(model);
    List<HexPosn> possibleMoves = raDusStrategy.chooseMoves(adapter);
    if(possibleMoves.isEmpty()){
      return Optional.empty();
    }

    return Optional.of(new HexPosToCoordinate(possibleMoves.get(0)));
  }
}
