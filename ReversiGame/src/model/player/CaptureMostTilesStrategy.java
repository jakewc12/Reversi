package model.player;

import java.util.Optional;
import model.MutableReversiModel;
import model.Position;
import model.ReadOnlyReversiModel;

public class CaptureMostTilesStrategy implements ReversiStrategy{
  @Override
  public Optional<Position> chooseMove(ReadOnlyReversiModel model, Player player) {
    if(model.getCurrentTurn()!= player.getColor() ){
      throw new IllegalStateException("Not players turn");
    }
    //DO NOT RETURN NULL THIS IS PLACEHOLDER
    //If no legal moves left, throw IllegalStateException
    //If there are multiple moves that claim the same, highest number of tiles, return top left
    //of those tiles
    return Optional.empty();
  }
}
