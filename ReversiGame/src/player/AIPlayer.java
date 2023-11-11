package player;

import java.util.Optional;
import model.Coordinate;
import model.DiscColor;
import model.MutableReversiModel;

public class AIPlayer implements Player {

  private DiscColor color;
  private ReversiStrategy strategy;

  public AIPlayer(DiscColor color, ReversiStrategy strategy) {
    this.color = color;
    this.strategy = strategy;
  }

  @Override
  public DiscColor getColor() {
    return this.color;
  }

  @Override
  public void playMove(MutableReversiModel model) {

    Optional<Coordinate> idealMove = strategy.chooseMove(model, this);

    if (idealMove.isEmpty()) {
      model.skipCurrentTurn();
      return;
    }
    model.placeDisc(idealMove.get());

  }
}
