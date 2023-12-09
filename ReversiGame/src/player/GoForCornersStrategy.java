package player;

import java.util.List;
import java.util.Optional;
import model.Coordinate;
import model.ReadOnlyReversiModel;
import model.hexreversi.LogicalHexCoordinate;

/**
 * The GoForCornersStrategy class implements the ReversiStrategy interface and represents a strategy
 * where the player tries to make moves towards the corners of the Reversi game board. The strategy
 * checks available coordinates, focusing on the corners and returns nothing if no corner move can
 * be made.
 */
public class GoForCornersStrategy implements ReversiStrategy {

  /**
   * When given a model and player the strategy will choose the best move that player can make
   * according to the strategy. This strategy will attempt to go for a corner if any are available.
   *
   * @param model the board the player is playing on.
   * @param who   the player who is using the strategy.
   * @return a move if one is found by the strategy, the strategy may be empty if none is found.
   */
  @Override
  public Optional<Coordinate> chooseMove(ReadOnlyReversiModel model, Player who) {

    List<Coordinate> allCoords = model.getAllCoordinates();

    for (Coordinate current : allCoords) {
      LogicalHexCoordinate cord = (LogicalHexCoordinate) current;
      if (!checkEdgeCoordinate(cord, model.getBoardRadius()) || (cord.getIntQ() == 0
          && cord.getIntR() == 0 && cord.getIntS() == 0)) {
        continue;
      }
      try {
        int flipped = model.getNumFlipsOnMove(current, who.getPlayerColor());
        if (flipped > 0) {
          return Optional.of(current);
        }
      } catch (IllegalStateException ignored) {
        continue;
      }
    }
    return Optional.empty();
  }

  private boolean checkEdgeCoordinate(int q, int r, int s, int radius) {
    return ((q == 0) || (q == radius) || (q == -radius)) && ((r == 0) || (r == radius) || (r
        == -radius)) && (r >= 0 || r <= radius) && ((s == 0) || (s == radius) || (s == -radius));
  }

  private boolean checkEdgeCoordinate(LogicalHexCoordinate cord, int radius) {
    return checkEdgeCoordinate(cord.getIntQ(), cord.getIntR(), cord.getIntS(), radius);
  }
}
