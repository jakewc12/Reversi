package digitalviews;

public interface DigitalWindow {
  /**
   * Make the view visible. This is usually called
   * after the view is constructed
   */
  void makeVisible();

  /**
   * Signal the view to draw itself
   */
  void refresh();
}
