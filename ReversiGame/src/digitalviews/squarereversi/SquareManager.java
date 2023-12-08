package digitalviews.squarereversi;

import java.util.Optional;

import digitalviews.DigitalBoardManager;
import model.hexreversi.LogicalHexCoordinate;

public class SquareManager implements DigitalBoardManager {
  private boolean hintsEnabled;
  /**
   * Signals the view to draw or redraw itself.
   */
  @Override
  public void refresh() {

  }

  /**
   * Returns the (Q,R,S) coordinate of the current cell that is highlighted.
   *
   * @return the current highlighted cell on the board.
   */
  @Override
  public Optional<LogicalHexCoordinate> getHighlightedCord() {
    return Optional.empty();
  }

  /**
   * Shows hints of how many flips would happen if the player places on highlighted cord.
   */
  @Override
  public void enableHints() {
    hintsEnabled = !hintsEnabled;
  }
}
