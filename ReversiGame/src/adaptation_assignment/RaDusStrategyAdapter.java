package adaptation_assignment;

import DustinRaymondReversi.model.HexPosn;
import DustinRaymondReversi.model.ReadOnlyReversiModel;
import DustinRaymondReversi.strategy.ReversiStrategy;
import java.util.List;
import java.util.Optional;
import model.Coordinate;
import player.Player;

public class RaDusStrategyAdapter implements ReversiStrategy, player.ReversiStrategy {


  @Override
  public List<HexPosn> chooseMoves(ReadOnlyReversiModel game) {
    return null;
  }

  @Override
  public Optional<Coordinate> chooseMove(model.ReadOnlyReversiModel model, Player who) {
    return Optional.empty();
  }
}
