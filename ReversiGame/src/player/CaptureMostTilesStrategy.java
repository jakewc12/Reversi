package player;

import java.util.List;
import java.util.Optional;
import model.Coordinate;
import model.DiscColor;
import model.ReadOnlyReversiModel;

public class CaptureMostTilesStrategy implements ReversiStrategy {

  @Override
  public Optional<Coordinate> chooseMove(ReadOnlyReversiModel model, Player player) {

    List<Coordinate> allCoords = model.getAllCoordinates();
    Coordinate currentBestMove = allCoords.get(0);
    int highestFlips = 0;

    for (Coordinate currentCoord : allCoords) {
      if(model.getColorAt(currentCoord)!= DiscColor.GREY){
        continue;
      }
      int currentNumFlips = model.getNumFlipsOnMove(currentCoord, player.getColor());
      if (highestFlips > currentNumFlips || currentNumFlips == 0) {
        continue;
      }
      if (highestFlips < currentNumFlips) {
        currentBestMove = currentCoord;
      }
      if (highestFlips == currentNumFlips && currentCoord.getR() <= currentBestMove.getR()) {
        if(currentCoord.getR() == currentBestMove.getR() && Math.abs(currentCoord.getQ()) < Math.abs(currentBestMove.getQ())){
          currentBestMove = currentCoord;
        } else if (currentCoord.getR() < currentBestMove.getR()) {
          currentBestMove = currentCoord;
        }
      }

      highestFlips = currentNumFlips;
    }
    if (highestFlips == 0) {
      return Optional.empty();
    }
    return Optional.ofNullable(currentBestMove);
  }
}
