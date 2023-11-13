package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The MockMutableReversiModel class extends MutableReversi and serves as a mock implementation of a
 * mutable Reversi model for testing purposes. It overrides certain methods to log actions and
 * interactions, providing a way to observe and verify the behavior of the game without a graphical
 * interface. This class does not affect most of the actual game logic and is intended solely for
 * testing.
 */
public class MockMutableReversiModel extends MutableReversi {

  private Appendable out;
  List<HexagonCell> cells = new ArrayList<>();

  /**
   * Constructs a MockMutableReversiModel with the specified size and an Appendable for logging.
   *
   * @param size The size of the game board.
   * @param out  The Appendable object for logging game actions.
   */
  public MockMutableReversiModel(int size, Appendable out) {
    super(size);
    this.out = out;
  }

  @Override
  public void startGame(List<HexagonCell> board) {
    super.startGame(board);
    this.cells = board;
  }

  private void append(String thing) {
    try {
      out.append(thing).append("\n");
    } catch (Exception e) {
      System.out.println("Connection with Appendable failed.");
    }
  }

  @Override
  public void skipCurrentTurn() {
    append("Turn skipped");
    super.skipCurrentTurn();
  }

  @Override
  public boolean isLegalMove(Coordinate coordinate) {
    for (HexagonCell cell : cells) {
      if (cell.getCoordinate().equals(coordinate)
          && cell.cellContents().getColor() != DiscColor.GREY) {
        throw new IllegalStateException("Move not legal");
      }
    }
    return true;
  }

  @Override
  public void placeDisc(Coordinate coordinate) {
    append("Place disc called at " + coordinate);
    super.placeDisc(coordinate);
  }

  /**
   * Forces the placement of a disc at the specified coordinate, logging the action.
   *
   * @param coordinate The coordinate at which the disc is forced to be placed.
   */
  public void forcePlaceDisc(Coordinate coordinate) {
    append("Force place disc called at " + coordinate);
    for (HexagonCell cell : cells) {
      if (cell.getCoordinate().equals(coordinate)) {
        cell.cellContents().changeColorTo(DiscColor.BLACK);
      }
    }
  }

  /**
   * Forces the placement of a disc at the specified coordinate with the specified color, logging
   * the action.
   *
   * @param coordinate The coordinate at which the disc is forced to be placed.
   * @param color      The color of the disc to be placed.
   */
  public void forcePlaceDisc(Coordinate coordinate, DiscColor color) {
    append("Force place disc called at " + coordinate);
    for (HexagonCell cell : cells) {
      if (cell.getCoordinate().equals(coordinate)) {
        cell.cellContents().changeColorTo(color);
      }
    }
  }

  /**
   * Changes the current turn to the specified color if it is not already the current turn, logging
   * the action.
   *
   * @param color The color to set as the current turn.
   */
  public void changeTurnTo(DiscColor color) {
    if (getCurrentTurn() != color) {
      super.skipCurrentTurn();
    }
  }
}
