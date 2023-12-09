package helpers;

import model.ReadOnlyReversiModel;
import textualview.HexReversiTextualView;
import textualview.TextualView;

/**
 * The TestHelper class provides helper methods for testing purposes in a Reversi game. It includes
 * a method to print the current state of the game board to the console. Although not integrated
 * into any tests it sees active use during development.
 */
public class TestHelper {

  /**
   * Prints the current state of the game board to the console.
   *
   * @param model The ReadOnlyReversiModel representing the game state.
   */
  public static void printModelBoard(ReadOnlyReversiModel model) {
    TextualView tv = new HexReversiTextualView(model);
    System.out.println(tv);
  }
}
