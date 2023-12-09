package digitalviews;

import java.util.List;
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

  /**
   * Shows hints of how many flips would happen if the player places on highlighted cord.
   */
  void enableHints();

  /**
   * True if a tile is currently highlighted, false else.
   *
   * @return if a tile is highlighted.
   */
  boolean tileCurrentlyClicked();

  /**
   * Returns all of the shapes in the manager.
   *
   * @return all of the drawnshapes.
   */
  List<DrawnShape> getShapes();
}
