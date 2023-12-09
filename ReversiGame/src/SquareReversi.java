import controller.Controller;
import controller.ReversiController;
import digitalviews.DigitalReversiWindow;
import digitalviews.DigitalWindow;
import model.DiscColor;
import model.MutableReversiModel;
import model.squarereversi.MutableSquareReversi;
import player.CaptureMostTilesStrategy;
import player.HumanPlayer;
import player.MachinePlayer;
import player.Player;

/**
 * The {@code SquareReversi} class serves as the entry point for the Square Reversi game
 * application. It initializes the game model, players, and controllers, and starts the game with a
 * graphical user interface.
 */
public class SquareReversi {

  /**
   * The main method that initializes and starts the Square Reversi game.
   *
   * @param args The command line arguments (not used in this application).
   */
  public static void main(String[] args) {
    // Initialize the MutableReversiModel with a board size of 3
    MutableReversiModel model = new MutableSquareReversi(4);
    model.setUpGame(model.getBoard());

    // Initialize the DigitalReversiWindow view
    DigitalWindow viewPlayer1 = new DigitalReversiWindow(model);
    DigitalWindow viewPlayer2 = new DigitalReversiWindow(model);

    Player player2 = new HumanPlayer(model, DiscColor.BLACK);
    Player player1 = new HumanPlayer(model, DiscColor.WHITE);

    // should we do a sleep so that the move isn't made literally automatically
    Controller controller1 = new ReversiController(model, player1, viewPlayer1);
    Controller controller2 = new ReversiController(model, player2, viewPlayer2);
    model.startGame();
    viewPlayer1.makeVisible();
    viewPlayer2.makeVisible();
  }
}
