package digitalviews;

import java.awt.Color;

public interface DigitalBoardManager {

  /**
   * Tells the DigitalBoard to set the color of a hex at specified row,col.
   * @param row the row of the specified hexagon.
   * @param col the column of the specified hexagon.
   * @param color the color the hexagon should switch to.
   */
  void setColor(int row, int col, Color color);

  /**
   * Signals the view to draw or redraw itself.
   */
  void refresh();
}
