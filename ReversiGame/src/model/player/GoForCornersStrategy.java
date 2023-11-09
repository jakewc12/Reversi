package model.player;

import model.MutableReversiModel;
import model.Position;

public class GoForCornersStrategy implements ReversiStrategy {
  @Override
  public Position chooseMove(MutableReversiModel model, Player who) {
    for (int q = -model.getBoardRadius(); q <= model.getBoardRadius(); q++) {
      for (int r = -model.getBoardRadius(); r <= model.getBoardRadius(); r++) {
        for (int s = -model.getBoardRadius(); s <= model.getBoardRadius(); s++) {
          if ((q + r + s) != 0) {
            continue;
          }
          if (!checkEdgeCoordinate(q, r, s, model.getBoardRadius())||(q==0&&r==0&&s==0)) {
            continue;
          }
          if (model.checkLegalMove(q, r, s)) {
            return new Position(q, r, s);
          }
        }
      }
    }
    throw new IllegalStateException("No discs could be legally placed on a corner tile");
  }

  private boolean checkEdgeCoordinate(int q, int r, int s, int radius) {
    if(!((q==0)||(q==radius)||(q==-radius)) ||!((r==0)||(r==radius)||(r==-radius))||(r <0&&r >radius)
    ||!((s==0)||(s==radius)||(s==-radius))){
      return false;
    }
    return true;
  }
}
