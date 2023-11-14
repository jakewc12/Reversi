package digitalviews;

import java.util.Optional;

import model.Coordinate;

/**
 * A manager that manages all the drawing of hexes and updating of hexes.
 */
public interface DigitalBoardManager {

  /**
   * Signals the view to draw or redraw itself.
   */
  void refresh();

  /**
   * Returns the (Q,R,S) coordinate of the current cell that is highlighted.
   *
   * @return the current highlighted cell on the board.
   */
  Optional<Coordinate> getHighlightedCord();

}
