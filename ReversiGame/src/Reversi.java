import digitalviews.DigitalReversiWindow;
import digitalviews.DigitalWindow;
import model.MutableReversi;
import model.MutableReversiModel;

/**
 * The Reversi class serves as the main entry point for the Reversi game application. It initializes
 * the game model, textual view, and digital view, and sets up the controller. The main method
 * creates an instance of MutableReversiModel, initializes a ReversiTextualView, and a
 * DigitalReversiWindow. It then starts the game, displays the initial game state, and sets up the
 * controller for user interaction.
 */
public final class Reversi {

  /**
   * The main method for starting the Reversi game.
   *
   * @param args The command-line arguments (not used in this application).
   */
  public static void main(String[] args) {
    // Initialize the MutableReversiModel with a board size of 3
    MutableReversiModel model = new MutableReversi(3);
    model.startGame(model.getBoard());

    // Initialize the DigitalReversiWindow view
    DigitalWindow view = new DigitalReversiWindow(model);
    view.makeVisible();
  }
}
