package digitalviews;

/**
 * A manager that manages all the drawing of hexes and updating of hexes.
 */
public interface DigitalBoardManager {

  /**
   * Signals the view to draw or redraw itself.
   */
  void refresh();

}
