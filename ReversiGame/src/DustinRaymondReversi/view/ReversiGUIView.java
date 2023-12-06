package DustinRaymondReversi.view;

import DustinRaymondReversi.controller.ReversiPlayerActions;

/**
 * Represents a GUI window used to display a hexagonal Reversi game. Will contain an observer
 * pattern to notify other classes when relevant events such as a mouse click has occurred in the
 * view.
 */
public interface ReversiGUIView {

  /**
   * Adds the given features set to this view, allowing that features set to be notified whenever a
   * particular event occurs in the view (i.e. a player performs a specific mouse or key action that
   * corresponds to a particular feature of the Reversi game).
   *
   * @param features the listener to add
   * @throws IllegalArgumentException if the listener is null
   */
  void addFeaturesListener(ReversiPlayerActions features);

  /**
   * Toggles whether this window is visible to the user.
   */
  void display(boolean show);

  /**
   * Displays a dialogue box with the given message, if the player did an oopsie.
   *
   * @param message the error message to display depending on what the player did
   */
  void showError(String message);

  /**
   * Refreshes the display on the view.
   */
  void refresh();
}
