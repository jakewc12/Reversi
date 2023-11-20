package player;

import java.util.Objects;
import java.util.Optional;

import model.Coordinate;
import model.DiscColor;
import model.MutableReversiModel;

/**
 * An AI that plays a game of reversi according to the strategy given to it.
 */
public class MachinePlayer implements Player {

  private DiscColor color;
  private ReversiStrategy strategy;

  /**
   * Creates an AI that will follow the given strategy.
   *
   * @param color    The color of the discs the AI will place. This will also determine move
   *                 recognition.
   * @param strategy The strategy the AI will follow when playing its turn.
   */
  public MachinePlayer(DiscColor color, ReversiStrategy strategy) {
    Objects.requireNonNull(color);
    Objects.requireNonNull(strategy);
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
