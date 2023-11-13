package player;

import java.util.List;
import java.util.Optional;
import model.Coordinate;
import model.ReadOnlyReversiModel;

/**
 * The GoForCornersStrategy class implements the ReversiStrategy interface and represents a strategy
 * where the player tries to make moves towards the corners of the Reversi game board. The strategy
 * checks available coordinates, focusing on the corners and returns nothing if no corner move can
 * be made.
 */
public class GoForCornersStrategy implements ReversiStrategy {

  @Override
  public Optional<Coordinate> chooseMove(ReadOnlyReversiModel model, Player who) {

    List<Coordinate> allCoords = model.getAllCoordinates();

    for (Coordinate cord : allCoords) {
      if (!checkEdgeCoordinate(cord, model.getBoardRadius()) || (cord.getIntQ() == 0
          && cord.getIntR() == 0 && cord.getIntS() == 0)) {
        continue;
      }
      try {
        int flipped = model.getNumFlipsOnMove(cord, who.getColor());
        if (flipped > 0) {
          return Optional.of(cord);
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

  private boolean checkEdgeCoordinate(Coordinate cord, int radius) {
    return checkEdgeCoordinate(cord.getIntQ(), cord.getIntR(), cord.getIntS(), radius);
  }
}
