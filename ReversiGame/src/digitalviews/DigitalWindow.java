package digitalviews;

import java.util.function.Consumer;

/**
 * The DigitalWindow interface defines the common behavior for digital views in a Reversi game.
 * Implementations of this interface represent graphical user interfaces or other digital displays
 * that allow users to interact with the game and receive information about the game state.
 */
public interface DigitalWindow {

  /**
   * Makes the view visible. This is usually called after the view is constructed.
   */
  void makeVisible();

  /**
   * Signals the view to draw or redraw itself.
   */
  void refresh();

  /**
   * Provides the view with a callback option to process a command.
   *
   * @param callback The Consumer object that will handle the command.
   */
  void setCommandCallback(Consumer<String> callback);

  /**
   * Transmits an error message to the view in case the command could not be processed correctly.
   *
   * @param error The error message that should be transmitted.
   */
  void showErrorMessage(String error);
}
