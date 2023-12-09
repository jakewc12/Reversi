package model.squarereversi;

import java.util.ArrayList;
import java.util.List;
import model.Coordinate;
import model.Disc;
import model.DiscColor;
import model.GameCell;
import model.MutableReversi;

/**
 * Meant to simulate the logic of a game of reversi. The game of reversi does not start until start
 * game is called with an adequate board size.
 */
public class MutableSquareReversi extends MutableReversi {

  //width is how far from the center cell every edge cell is
  private final int size;

  /**
   * Creates a MutableHexReversi and sets all game values to zero until startGame is called.
   *
   * @param size is the intended radius of the game, in relation to the center cell.
   * @invariant a game cannot be played unless gameStarted is true.
   * @invariant size cannot be less than 1.
   */
  public MutableSquareReversi(int size) {
    super(size);
    if (size <= 1) {
      throw new IllegalArgumentException("Invalid size given");
    }
    if (size % 2 == 1) {
      throw new IllegalArgumentException("Size is not even");
    }
    this.size = size;
  }

  @Override
  public List<GameCell> getBoard() {
    List<GameCell> localCells = new ArrayList<>();
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        if (row == getBoardRadius() / 2 - 1 && col == getBoardRadius() / 2 - 1) {
          localCells.add(new SquareCell(DiscColor.BLACK, row, col));
        } else if (row == getBoardRadius() / 2 && col == getBoardRadius() / 2) {
          localCells.add(new SquareCell(DiscColor.BLACK, row, col));
        } else if (row == getBoardRadius() / 2 - 1 && col == getBoardRadius() / 2) {
          localCells.add(new SquareCell(DiscColor.WHITE, row, col));
        } else if (row == getBoardRadius() / 2 && col == getBoardRadius() / 2 - 1) {
          localCells.add(new SquareCell(DiscColor.WHITE, row, col));
        } else {
          localCells.add(new SquareCell(DiscColor.GREY, row, col));
        }
      }
    }
    return localCells;
  }

  protected void checkValidCoordinates(Coordinate genericCoord) {
    SquareCoordinate coord = (SquareCoordinate) genericCoord;
    if (Math.abs(coord.getIntQ()) > size || Math.abs(coord.getIntR()) > size) {
      throw new IllegalArgumentException(
          "Invalid coordinates given for size. Max coordinate size is: " + size + "\n coordinates "
              + "were " + coord);
    }
  }

  /**
   * Get all the possible flips if the target cell was placed.
   *
   * @param currentColor the color of the disc you want placed
   * @param targetCell   the cell you want to place on the board.
   * @return the surrounding cells which will flip if the targetCell was placed.
   */
  public ArrayList<Disc> getAllFlips(GameCell targetCell, DiscColor currentColor) {
    ArrayList<Disc> toFlip = new ArrayList<>();
    if (targetCell.cellContents().getColor() != DiscColor.GREY) {
      return toFlip;
    }
    //Check Horizontal
    toFlip.addAll(getInLineFlipsPossible(getAllCellInDirection(targetCell, SquareDirections.ABOVE),
        currentColor));
    toFlip.addAll(
        getInLineFlipsPossible(getAllCellInDirection(targetCell, SquareDirections.BELOW),
            currentColor));

    //Check Right diagonal
    toFlip.addAll(
        getInLineFlipsPossible(getAllCellInDirection(targetCell, SquareDirections.TOP_LEFT),
            currentColor));
    toFlip.addAll(
        getInLineFlipsPossible(getAllCellInDirection(targetCell, SquareDirections.DEAD_LEFT),
            currentColor));

    //Check Left diagonal
    toFlip.addAll(
        getInLineFlipsPossible(getAllCellInDirection(targetCell, SquareDirections.BOTTOM_LEFT),
            currentColor));
    toFlip.addAll(
        getInLineFlipsPossible(getAllCellInDirection(targetCell, SquareDirections.TOP_RIGHT),
            currentColor));
    toFlip.addAll(
        getInLineFlipsPossible(getAllCellInDirection(targetCell, SquareDirections.DEAD_RIGHT),
            currentColor));
    toFlip.addAll(
        getInLineFlipsPossible(getAllCellInDirection(targetCell, SquareDirections.BOTTOM_RIGHT),
            currentColor));

    return toFlip;
  }

  @Override
  public int getBoardSize() {
    return getBoardRadius();
  }


  @Override
  public int getBoardRadius() {
    return size;
  }
}