package player;

import java.util.List;
import java.util.Optional;

import model.Coordinate;
import model.DiscColor;
import model.ReadOnlyReversiModel;

/**
 * A strategy that when given a game of reversi will attempt to flip the most amount of tiles of a
 * given color.
 */
public class CaptureMostTilesStrategy implements ReversiStrategy {

  /**
   * When given a model and player the strategy will choose the best move that player can make
   * according to the strategy.
   *
   * @param model  the board the player is playing on.
   * @param player the player who is using the strategy.
   * @return a move if one is found by the strategy, the strategy may be empty if none is found.
   */
  @Override
  public Optional<Coordinate> chooseMove(ReadOnlyReversiModel model, Player player) {

    List<Coordinate> allCoords = model.getAllCoordinates();
    Coordinate currentBestMove = allCoords.get(0);
    int highestFlips = 0;

    for (Coordinate currentCoord : allCoords) {
      if (model.getColorAt(currentCoord) != DiscColor.GREY) {
        continue;
      }
      int currentNumFlips = model.getNumFlipsOnMove(currentCoord, player.getColor());
      if (highestFlips > currentNumFlips || currentNumFlips == 0) {
        continue;
      }
      if (highestFlips < currentNumFlips) {
        currentBestMove = currentCoord;
      }
      if (highestFlips == currentNumFlips && currentCoord.getIntR() <= currentBestMove.getIntR()) {
        if (currentCoord.getIntR() == currentBestMove.getIntR()
                && Math.abs(currentCoord.getIntQ()) < Math.abs(currentBestMove.getIntQ())) {
          currentBestMove = currentCoord;
        } else if (currentCoord.getIntR() < currentBestMove.getIntR()) {
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
