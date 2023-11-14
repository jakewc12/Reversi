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

  /**
   * Adds features to the window to allow for key interaction.
   * @param features the features to be added to the view.
   */
  void addFeaturesListener(Features features);
}
