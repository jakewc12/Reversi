package player;

import java.util.List;
import java.util.Optional;
import model.Coordinate;
import model.ReadOnlyReversiModel;

public class GoForCornersStrategy implements ReversiStrategy {

  @Override
  public Optional<Coordinate> chooseMove(ReadOnlyReversiModel model, Player who) {
    List<Coordinate> allCoords = model.getAllCoordinates();

    for (Coordinate cord: allCoords) {
      int q = cord.getQ();
      int r = cord.getR();
      int s = cord.getS();

          if (!checkEdgeCoordinate(cord, model.getBoardRadius()) || (q == 0 && r == 0
              && s == 0)) {
            continue;
          }
          try{
          model.isLegalMove(cord);
            return Optional.of(new Coordinate(q, r, s));
          }catch (IllegalStateException ignored){}
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
