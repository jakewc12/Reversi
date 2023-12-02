package DustinRaymondReversi.strategy;

import java.util.List;

import DustinRaymondReversi.model.HexPosn;
import DustinRaymondReversi.model.ReadOnlyReversiModel;

/**
 * Represents a combination of two Reversi strategies, a primary strategy and a fallback
 * strategy. This strategy first attempts the primary strategy. If the primary strategy
 * finds any moves, then those moves are returned; otherwise, this combinator returns the
 * results of the fallback strategy.
 */
public class FallbackStrategyCombinator implements ReversiStrategy {

  private final ReversiStrategy primaryStrat;
  private final ReversiStrategy fallbackStrat;

  /**
   * Constructs a new fallback strategy combinator using the given primary and fallback
   * strategies.
   *
   * @param primary the primary strategy
   * @param fallback the fallback strategy
   * @throws IllegalArgumentException if the inputs are null
   */
  public FallbackStrategyCombinator(ReversiStrategy primary, ReversiStrategy fallback) {
    if (primary == null || fallback == null) {
      throw new IllegalArgumentException("Given strategies cannot be null.");
    }
    this.primaryStrat = primary;
    this.fallbackStrat = fallback;
  }

  @Override
  public List<HexPosn> chooseMoves(ReadOnlyReversiModel game) {
    if (game == null) {
      throw new IllegalArgumentException("Given game cannot be null.");
    }

    List<HexPosn> primaryMoves = this.primaryStrat.chooseMoves(game);

    if (primaryMoves.isEmpty()) {
      return this.fallbackStrat.chooseMoves(game);
    }

    return primaryMoves;
  }
}
