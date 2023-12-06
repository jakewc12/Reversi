package dustinraymondreversi.strategy;

import dustinraymondreversi.model.HexPosn;
import dustinraymondreversi.model.ReadOnlyReversiModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Reversi strategy that exclusively chooses to place pieces in the corners of the
 * board. If no corner is available, this strategy returns no moves.
 */
public class ChooseCornerStrategy implements ReversiStrategy {

  @Override
  public List<HexPosn> chooseMoves(ReadOnlyReversiModel game) {
    if (game == null) {
      throw new IllegalArgumentException("Given game cannot be null.");
    }

    List<HexPosn> validCorners = new ArrayList<>();

    for (HexPosn corner : this.getCorners(game)) {
      if (game.isLegalMoveAt(corner)) {
        validCorners.add(corner);
      }
    }

    return validCorners;
  }

  /**
   * Returns all the corners in the board of the given Reversi game.
   *
   * @param game the given Reversi game to check.
   * @return all 6 corners of the board.
   */
  private List<HexPosn> getCorners(ReadOnlyReversiModel game) {
    int sideLength = game.getSideLength();
    int borderCoord = sideLength - 1;

    List<HexPosn> corners = new ArrayList<>();
    corners.add(new HexPosn(0, -borderCoord));
    corners.add(new HexPosn(borderCoord, -borderCoord));
    corners.add(new HexPosn(borderCoord, 0));
    corners.add(new HexPosn(0, borderCoord));
    corners.add(new HexPosn(-borderCoord, borderCoord));
    corners.add(new HexPosn(-borderCoord, 0));

    return corners;
  }
}
