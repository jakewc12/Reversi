package model.player;

import java.util.Optional;
import model.Coordinate;
import model.ReadOnlyReversiModel;

public class GoForCornersStrategy implements ReversiStrategy {

  @Override
  public Optional<Coordinate> chooseMove(ReadOnlyReversiModel model, Player who) {
    for (int q = -model.getBoardRadius(); q <= model.getBoardRadius(); q++) {
      for (int r = -model.getBoardRadius(); r <= model.getBoardRadius(); r++) {
        for (int s = -model.getBoardRadius(); s <= model.getBoardRadius(); s++) {
          if ((q + r + s) != 0) {
            continue;
          }
          if (!checkEdgeCoordinate(q, r, s, model.getBoardRadius()) || (q == 0 && r == 0
              && s == 0)) {
            continue;
          }
          if (model.checkLegalMove(q, r, s)) {
            return Optional.of(new Coordinate(q, r, s));
          }
        }
      }
    }
    return Optional.empty();
  }

  private boolean checkEdgeCoordinate(int q, int r, int s, int radius) {
    return ((q == 0) || (q == radius) || (q == -radius)) && ((r == 0) || (r == radius) || (r
        == -radius)) && (r >= 0 || r <= radius)
        && ((s == 0) || (s == radius) || (s == -radius));
  }
  private boolean checkEdgeCoordinate(Coordinate cord,  int radius) {
    return checkEdgeCoordinate(cord.getQ(),cord.getR(), cord.getS(), radius);
  }
}
