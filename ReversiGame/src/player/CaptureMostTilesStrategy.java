package player;

import java.util.List;
import java.util.Optional;
import model.Coordinate;
import model.ReadOnlyReversiModel;

public class CaptureMostTilesStrategy implements ReversiStrategy {

  @Override
  public Optional<Coordinate> chooseMove(ReadOnlyReversiModel model, Player player) {

    List<Coordinate> allCoords = model.getAllCoordinates();
    Coordinate currentBestMove = allCoords.get(0);
    int highestFlips = 0;

    for (Coordinate currentCoord : allCoords) {
      int currentNumFlips = model.getNumFlipsOnMove(currentCoord, player.getColor());
      if(highestFlips > currentNumFlips){
        continue;
      }
      if (highestFlips < currentNumFlips) {
        currentBestMove = currentCoord;
      }
      if (highestFlips == currentNumFlips && currentCoord.getR() < currentBestMove.getR()) {
        if (currentCoord.getS() > currentBestMove.getS()) {
          currentBestMove = currentCoord;
        }
      }
      highestFlips = Math.max(highestFlips, currentNumFlips);
    }
    if (highestFlips == 0) {
      return Optional.empty();
    }
    return Optional.ofNullable(currentBestMove);
  }
}
