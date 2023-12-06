package DustinRaymondReversi.strategy;

import DustinRaymondReversi.model.HexPosn;
import DustinRaymondReversi.model.ReadOnlyReversiModel;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Represents a strategy tiebreaker that chooses the top-leftmost cell in the Reversi board. If
 * multiple cells are closest to the top left, then the one with the smallest q-value is chosen.
 */
public class TopLeftmostTiebreaker implements StrategyTiebreaker {

  @Override
  public Optional<HexPosn> chooseOneMove(ReversiStrategy strategy
      , ReadOnlyReversiModel model) {
    if (model == null || strategy == null) {
      throw new IllegalArgumentException("Given game or strategy cannot be null.");
    }

    List<HexPosn> possibleMoves = strategy.chooseMoves(model);
    if (possibleMoves.isEmpty()) {
      return Optional.empty();
    }

    possibleMoves.sort(comparePointsCloserTo(new HexPosn(0, -(model.getSideLength() - 1))));
    return Optional.of(possibleMoves.get(0));
  }

  /**
   * Returns a Comparator that can take in 2 HexPosns, and compare distances to the given HexPosn.
   * For example, if the given point is h, and the two points in the Comparator are h1 and h2, it
   * will compare the values of distance(h, h1) and distance(h, h2).
   *
   * @param point the point in which the Comparator will compare the distances between the two given
   *              points and this point.
   * @return Comparator that can compare the distances between the 2 HexPosns and the given point.
   */
  private Comparator<HexPosn> comparePointsCloserTo(HexPosn point) {
    return (h1, h2) -> {
      double distanceH1 = HexPosn.distance(h1, point);
      double distanceH2 = HexPosn.distance(h2, point);

      int comparison = Double.compare(distanceH1, distanceH2);
      if (comparison == 0) {
        return Integer.compare(h1.getQ(), h2.getQ());
      } else {
        return comparison;
      }
    };
  }
}
