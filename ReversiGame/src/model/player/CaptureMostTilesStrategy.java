package model.player;

import model.MutableReversiModel;
import model.Position;

public class CaptureMostTilesStrategy implements ReversiStrategy{
  @Override
  public Position chooseMove(MutableReversiModel model, Player player) {
    if(model.getCurrentTurn()!= player.getColor() ){
      throw new IllegalStateException("Not players turn");
    }
    //DO NOT RETURN NULL THIS IS PLACEHOLDER
    return null;
  }
}
