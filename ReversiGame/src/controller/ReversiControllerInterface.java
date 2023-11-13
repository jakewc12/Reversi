package controller;

/**
 * The ReversiControllerInterface defines the common behavior for controllers in a Reversi game.
 * Implementations of this interface process commands and manage the flow of the game.
 */
public interface ReversiControllerInterface {

  /**
   * Processes a given string command and returns the status or error as a string.
   *
   * @param command The command given, including any parameters (e.g., "move 3").
   * @return The status or error message.
   */
  String processCommand(String command);

  /**
   * Starts the program, giving control to the controller.
   */
  void go();
}

