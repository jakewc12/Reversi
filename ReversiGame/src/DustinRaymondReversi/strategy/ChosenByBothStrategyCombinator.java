package DustinRaymondReversi.strategy;

import java.util.List;

import DustinRaymondReversi.model.HexPosn;
import DustinRaymondReversi.model.ReadOnlyReversiModel;

/**
 * Represents a combination of two Reversi strategies where a move is only considered a
 * valid possibility if both strategies return it.
 */
public class ChosenByBothStrategyCombinator implements ReversiStrategy {

  private final ReversiStrategy strategy1;
  private final ReversiStrategy strategy2;

  /**
   * Constructs a new "chosen by both" strategy combinator with the given two strategies to
   * consult.
   *
   * @param strategy1 one of the two strategies
   * @param strategy2 one of the two strategies
   * @throws IllegalArgumentException if any of the inputs are null
   */
  public ChosenByBothStrategyCombinator(ReversiStrategy strategy1, ReversiStrategy strategy2) {
    if (strategy1 == null || strategy2 == null) {
      throw new IllegalArgumentException("Given strategies cannot be null.");
    }
    this.strategy1 = strategy1;
    this.strategy2 = strategy2;
  }

  @Override
  public List<HexPosn> chooseMoves(ReadOnlyReversiModel game) {
    if (game == null) {
      throw new IllegalArgumentException("Given game cannot be null.");
    }

    List<HexPosn> moves = strategy1.chooseMoves(game);
    moves.retainAll(strategy2.chooseMoves(game));

    return moves;
  }

}
