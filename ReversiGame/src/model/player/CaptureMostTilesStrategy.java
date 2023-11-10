package model.player;

import java.util.Optional;
import model.Coordinate;
import model.ReadOnlyReversiModel;

public class CaptureMostTilesStrategy implements ReversiStrategy{
  @Override
  public Optional<Coordinate> chooseMove(ReadOnlyReversiModel model, Player player) {

    //DO NOT RETURN NULL THIS IS PLACEHOLDER
    //If there are multiple moves that claim the same, highest number of tiles, return top left
    //of those tiles
    return Optional.empty();
  }
}
