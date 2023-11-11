package model.player;

import java.util.List;
import java.util.Optional;
import model.Coordinate;
import model.ReadOnlyReversiModel;

public class CaptureMostTilesStrategy implements ReversiStrategy{
  @Override
  public Optional<Coordinate> chooseMove(ReadOnlyReversiModel model, Player player) {
    List<Coordinate> allCoords = model.getAllCoordinates();
    Optional<Coordinate> currentBestMove = Optional.empty();
    int highestFlips = 0;
    System.out.println();
    for (Coordinate currentCoord: allCoords){
      int currentNumFlips = model.getNumFlipsOnMove(currentCoord, player.getColor());
      if(highestFlips < currentNumFlips){
        currentBestMove = Optional.ofNullable(currentCoord);
      }
    }
    return currentBestMove;
  }
}
