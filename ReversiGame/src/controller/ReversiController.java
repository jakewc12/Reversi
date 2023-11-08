package controller;

public interface ReversiController {

  /**
   * Process a given string command and return status or error as a string
   *
   * @param command the command given, including any parameters (e.g. "move 3")
   * @return status or error message
   */
  String processCommand(String command);

  /**
   * Start the program, i.e. give control to the controller
   */
  void go();
}
