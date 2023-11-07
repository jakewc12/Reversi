package model.player;

import model.MutableReversiModel;
import model.Position;

public interface ReversiStrategy {
  Position chooseMove(MutableReversiModel model, PlayerInterface who);
}
