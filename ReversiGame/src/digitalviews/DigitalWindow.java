package digitalviews;

import java.util.function.Consumer;

import controller.Features;

public interface DigitalWindow {

  /**
   * Make the view visible. This is usually called after the view is constructed.
   */
  void makeVisible();

  /**
   * Signals the view to draw or redraw itself.
   */
  void refresh();
  void addFeaturesListener(Features features);
}
