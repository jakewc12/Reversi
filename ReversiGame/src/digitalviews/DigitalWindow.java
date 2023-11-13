package digitalviews;

import java.util.function.Consumer;

/**
 * The DigitalWindow interface defines the common contract for digital views in a graphical user
 * interface. Implementing classes are expected to provide methods for displaying, refreshing,
 * handling user commands, and showing error messages.
 */
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
   * @param callback The Consumer object that will handle user commands.
   */
  void setCommandCallback(Consumer<String> callback);

  /**
   * Transmit an error message to the view, in case the command could not be processed correctly.
   *
   * @param error The error message that should be transmitted.
   */
  void showErrorMessage(String error);
}
