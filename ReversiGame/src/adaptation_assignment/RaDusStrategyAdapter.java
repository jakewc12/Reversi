package adaptation_assignment;

import DustinRaymondReversi.model.HexPosn;
import java.util.List;
import java.util.Optional;
import model.Coordinate;
import player.Player;

public class RaDusStrategyAdapter implements player.ReversiStrategy {

  private final DustinRaymondReversi.strategy.ReversiStrategy raDusStrategy;

  public RaDusStrategyAdapter(DustinRaymondReversi.strategy.ReversiStrategy strategy) {
    raDusStrategy = strategy;
  }

  @Override
  public Optional<Coordinate> chooseMove(model.ReadOnlyReversiModel model, Player who) {
    RaDusModelAdapter adapter = new RaDusModelAdapter(model.getBoardSize()-1);
    adapter.setUpGame(model);
    List<HexPosn> possibleMoves = raDusStrategy.chooseMoves(adapter);

    if(possibleMoves.isEmpty()){
      return Optional.empty();
    }
    //need to check that the move is legal for this player.

    for(HexPosn coordinate : possibleMoves){
      if(model.getNumFlipsOnMove(new HexPosToCoordinate(coordinate), who.getPlayerColor())>0){
        return Optional.of(new HexPosToCoordinate(coordinate));
      }
    }
    return Optional.empty();
  }
}
