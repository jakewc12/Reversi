package helpers;

import java.util.ArrayList;
import java.util.List;

import model.hexreversi.LogicalHexCoordinate;
import model.hexreversi.MutableHexReversi;
import model.hexreversi.HexCoordinate;
import model.DiscColor;
import model.GameCell;
import model.ModelStatus;

/**
 * The MockMutableHexReversiModel class extends MutableHexReversi and serves as a mock implementation of a
 * mutable Reversi model for testing purposes. It overrides certain methods to log actions and
 * interactions, providing a way to observe and verify the behavior of the game without a graphical
 * interface. This class does not affect most of the actual game logic and is intended solely for
 * testing.
 */
public class MockMutableHexReversiModel extends MutableHexReversi {

  private List<String> ban;
  private final Appendable out;
  private List<GameCell> cells = new ArrayList<>();
  private final List<ModelStatus> features = new ArrayList<>();

  /**
   * Constructs a MockMutableHexReversiModel with the specified size and an Appendable for logging.
   *
   * @param size The size of the game board.
   * @param out  The Appendable object for logging game actions.
   */
  public MockMutableHexReversiModel(int size, Appendable out) {
    super(size);
    this.out = out;
    ban = new ArrayList<>();
  }

  public void addBanItem(String item) {
    this.ban.add(item);
  }

  @Override
  public void setUpGame(List<GameCell> board) {
    super.setUpGame(board);
    this.cells = board;
  }

  @Override
  public void startGame() {
    super.startGame();
    notifyFeatures();
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
    if (!ban.contains("skipCurrentTurn")) {
      append("Turn skipped");
    }

    super.skipCurrentTurn();
  }

  @Override
  public boolean isLegalMove(LogicalHexCoordinate coordinate) {
    for (GameCell cell : cells) {
      if (cell.getCoordinate().equals(coordinate)
              && cell.cellContents().getColor() != DiscColor.GREY) {
        throw new IllegalStateException("Move not legal");
      }
    }
    return true;
  }

  @Override
  public void placeDisc(LogicalHexCoordinate coordinate) {
    if (!ban.contains("placeDisc")) {
      append("Place disc called at " + coordinate);
    }

    super.placeDisc(coordinate);
  }


  /**
   * Forces the placement of a disc at the specified HexCoordinate with the specified color, logging
   * the action.
   *
   * @param hexCoordinate The HexCoordinate at which the disc is forced to be placed.
   * @param color      The color of the disc to be placed.
   */
  public void forcePlaceDisc(HexCoordinate hexCoordinate, DiscColor color) {
    if (!ban.contains("forcePlaceDisc")) {
      append("Force place disc called at " + hexCoordinate);
    }
    for (GameCell cell : cells) {
      if (cell.getCoordinate().equals(hexCoordinate)) {
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

  /**
   * Gets the number of flips on a player move.
   *
   * @param coordinate  The HexCoordinate you want to place a Disc on.
   * @param playerColor The color of the player who is placing the disc
   * @return the number of discs flipped if the player makes that move.
   */
  @Override
  public int getNumFlipsOnMove(LogicalHexCoordinate coordinate, DiscColor playerColor) {
    if (!ban.contains("getNumFlipsOnMove")) {
      append("Checked legal at " + coordinate.toString());
    }

    return super.getNumFlipsOnMove(coordinate, playerColor);
  }

  @Override
  public void addFeaturesInterface(ModelStatus features) {
    if (!ban.contains("addFeaturesInterface")) {
      append("added feature to model");
    }

    this.features.add(features);
  }

  private void notifyFeatures() {
    for (ModelStatus feature : this.features) {
      if (!ban.contains("notifyFeatures")) {
        append("Notified " + feature + " that move was played.");
      }

      feature.moveWasPlayed();
    }
  }
}