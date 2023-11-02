package view;

import controller.ReversiController;

/**
 * A view for Tic-Tac-Toe: display the game board and provide visual interface for users.
 */
public interface ReversiView {

  /**
   * Set up the controller to handle click events in this view.
   * @param listener the controller
   */
  void addClickListener(ReversiController listener);

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();

  /**
   * Transmit an error message to the view, in case
   * the command could not be processed correctly
   *
   * @param error error transmitted.
   */
  void showErrorMessage(String error);
}
