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
   * Provide the view with a callback option to process a command.
   *
   * @param callback object
   */
  void setCommandCallback(Consumer<String> callback);

  /**
   * Transmit an error message to the view, in case the command could not be processed correctly.
   *
   * @param error The error message that should be transmitted.
   */
  void showErrorMessage(String error);
  void addFeaturesListener(Features feature);
}
