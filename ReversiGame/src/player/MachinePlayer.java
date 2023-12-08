package player;

import java.util.Objects;
import java.util.Optional;
import model.DiscColor;
import model.hexreversi.LogicalHexCoordinate;
import model.MutableReversiModel;

/**
 * An AI that plays a game of reversi according to the strategy given to it.
 */
public class MachinePlayer implements Player {

  private final DiscColor playerColor;
  private final ReversiStrategy strategy;

  /**
   * Creates an AI that will follow the given strategy.
   *
   * @param playerColor The color of the discs the AI will place. This will also determine move
   *                    recognition.
   * @param strategy    The strategy the AI will follow when playing its turn.
   */
  public MachinePlayer(DiscColor playerColor, ReversiStrategy strategy) {
    Objects.requireNonNull(playerColor);
    Objects.requireNonNull(strategy);
    this.playerColor = playerColor;
    this.strategy = strategy;
  }

  @Override
  public DiscColor getPlayerColor() {
    return this.playerColor;
  }

  @Override
  public void playMove(MutableReversiModel model) {
    Optional<LogicalHexCoordinate> idealMove = strategy.chooseMove(model, this);

    if (idealMove.isEmpty()) {
      model.skipCurrentTurn();
      return;
    }
    model.placeDisc(idealMove.get());

  }

  @Override
  public Optional<ReversiStrategy> getStrategy() {
    return Optional.of(this.strategy);
  }
}
