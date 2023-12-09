package model.hexreversi;

import java.util.ArrayList;
import java.util.List;
import model.Disc;
import model.DiscColor;
import model.GameCell;
import model.MutableReversi;

/**
 * Meant to simulate the logic of a game of reversi. The game of reversi does not start until start
 * game is called with an adequate board size.
 */
public class MutableHexReversi extends MutableReversi {

  /**
   * Creates a MutableHexReversi and sets all game values to zero until startGame is called.
   *
   * @param size is the intended radius of the game, in relation to the center cell.
   * @invariant a game cannot be played unless gameStarted is true.
   * @invariant size cannot be less than 1.
   */
  public MutableHexReversi(int size) {
    super(size);
    if (size <= 0) {
      throw new IllegalArgumentException("Invalid size given");
    }
  }

  @Override
  public List<GameCell> getBoard() {
    List<GameCell> localCells = new ArrayList<>();
    int size = getBoardRadius();
    for (int q = -size; q <= size; q++) {
      for (int r = -size; r <= size; r++) {
        for (int s = -size; s <= size; s++) {
          if (q + r + s == 0) {
            LogicalHexCoordinate currentCoord = new HexCoordinate(q, r, s);
            if ((q == 0 && r == -1 && s == 1) || (q == 1 && r == 0 && s == -1) || (q == -1 && r == 1
                && s == 0)) {
              HexCell newCell = new HexCell(DiscColor.BLACK, currentCoord);
              localCells.add(newCell);
            } else if ((q == 1 && r == -1 && s == 0) || (q == 0 && r == 1 && s == -1) || (q == -1
                && r == 0 && s == 1)) {
              HexCell newCell = new HexCell(DiscColor.WHITE, currentCoord);
              localCells.add(newCell);
            } else {
              localCells.add(new HexCell(DiscColor.GREY, currentCoord));
            }
          }
        }
      }
    }
    return localCells;
  }


  /**
   * Get all the possible flips if the target cell was placed.
   *
   * @param currentColor the color of the disc you want placed
   * @param targetCell   the cell you want to place on the board.
   * @return the surrounding cells which will flip if the targetCell was placed.
   */
  @Override
  public ArrayList<Disc> getAllFlips(GameCell targetCell, DiscColor currentColor) {
    ArrayList<Disc> toFlip = new ArrayList<>();
    if (targetCell.cellContents().getColor() != DiscColor.GREY) {
      return toFlip;
    }
    //Check Horizontal
    toFlip.addAll(getInLineFlipsPossible(getAllCellInDirection(targetCell, HexDirections.TOP_LEFT),
        currentColor));
    toFlip.addAll(
        getInLineFlipsPossible(getAllCellInDirection(targetCell, HexDirections.BOTTOM_RIGHT),
            currentColor));

    //Check Right diagonal
    toFlip.addAll(getInLineFlipsPossible(getAllCellInDirection(targetCell, HexDirections.DEAD_LEFT),
        currentColor));
    toFlip.addAll(
        getInLineFlipsPossible(getAllCellInDirection(targetCell, HexDirections.DEAD_RIGHT),
            currentColor));

    //Check Left diagonal
    toFlip.addAll(getInLineFlipsPossible(getAllCellInDirection(targetCell, HexDirections.TOP_RIGHT),
        currentColor));
    toFlip.addAll(
        getInLineFlipsPossible(getAllCellInDirection(targetCell, HexDirections.BOTTOM_LEFT),
            currentColor));

    return toFlip;
  }


}
