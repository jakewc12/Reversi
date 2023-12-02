package DustinRaymondReversi.strategy;

import java.util.ArrayList;
import java.util.List;

import DustinRaymondReversi.model.HexPosn;
import DustinRaymondReversi.model.ReadOnlyReversiModel;

/**
 * Represents a Reversi strategy that chooses any move that does not place the player
 * in a "corner trap". A corner trap occurs when the player places a piece in a cell
 * immediately bordering a corner cell, because this can allow the opponent to place
 * a piece in that corner cell and secure an uncapturable position on the board. This
 * strategy returns any possible move except those adjacent to corner cells, without
 * considering any other factors.
 */
public class AvoidCornerTrapStrategy implements ReversiStrategy {

  @Override
  public List<HexPosn> chooseMoves(DustinRaymondReversi.model.ReadOnlyReversiModel game) {
    if (game == null) {
      throw new IllegalArgumentException("Given game cannot be null.");
    }

    List<HexPosn> cellsToAvoid = this.getCellsToAvoid(game);

    List<HexPosn> allValidMoves = new ArrayList<>();

    for (HexPosn pos: game.getAllValidCoordinates()) {
      if (game.isLegalMoveAt(pos) && !cellsToAvoid.contains(pos)) {
        allValidMoves.add(pos);
      }
    }

    return allValidMoves;
  }

  /**
   * Gets a List of all cells to avoid, which are cells that are next to corners.
   * @param game the game to check in.
   * @return all cells that neighbor the 6 corners, even if they are out of bounds.
   */
  private List<HexPosn> getCellsToAvoid(ReadOnlyReversiModel game) {
    int sideLength = game.getSideLength();
    int borderCoord = sideLength - 1;

    //Creates a list of all 6 corners of the board.
    List<HexPosn> corners = new ArrayList<>();
    corners.add(new HexPosn(0, -borderCoord));
    corners.add(new HexPosn(borderCoord, -borderCoord));
    corners.add(new HexPosn(borderCoord, 0));
    corners.add(new HexPosn(0, borderCoord));
    corners.add(new HexPosn(-borderCoord, borderCoord));
    corners.add(new HexPosn(-borderCoord, 0));

    List<HexPosn> cornerNeighbors = new ArrayList<>();

    //adds all the neighbors to each of the 6 corners, even if they are out of bounds
    for (HexPosn corner : corners) {
      for (HexPosn.NeighborDirection dir : HexPosn.NeighborDirection.values()) {
        cornerNeighbors.add(corner.getNeighbor(dir));
      }
    }

    return cornerNeighbors;
  }
}
