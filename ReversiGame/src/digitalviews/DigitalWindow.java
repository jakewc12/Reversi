package digitalviews;

import java.util.function.Consumer;

public interface DigitalWindow {

  /**
   * Make the view visible. This is usually called after the view is constructed
   */
  void makeVisible();

  /**
   * Signal the view to draw itself
   */
  void refresh();

  /**
   * Provide the view with a callback option to process a command.
   *
   * @param callback object
   */
  void setCommandCallback(Consumer<String> callback);

  /**
   * Transmit an error message to the view, in case the command could not be processed correctly
   *
   * @param error
   */
  void showErrorMessage(String error);
}
