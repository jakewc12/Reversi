package model.player;

import java.util.Optional;
import model.Coordinate;
import model.ReadOnlyReversiModel;

public class NoOpponentCornerStrategy implements ReversiStrategy{

  @Override
  public Optional<Coordinate> chooseMove(ReadOnlyReversiModel model, Player who) {
    model.checkLegalMove(new Coordinate(1,1,1));
    return Optional.empty();
  }
}
