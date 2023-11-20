import controller.HumanPlayerController;
import controller.MachinePlayerController;
import controller.ReversiControllerInterface;
import digitalviews.DigitalReversiWindow;
import digitalviews.DigitalWindow;
import model.DiscColor;
import model.MutableReversi;
import model.MutableReversiModel;
import player.CaptureMostTilesStrategy;
import player.ComputerPlayer;
import player.HumanPlayer;
import player.Player;

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
    DigitalWindow viewPlayer1 = new DigitalReversiWindow(model);
    DigitalWindow viewPlayer2 = new DigitalReversiWindow(model);
    viewPlayer1.makeVisible();
    viewPlayer2.makeVisible();
    Player player1 = new HumanPlayer(model, DiscColor.WHITE);
    Player player2 = new HumanPlayer(model, DiscColor.BLACK);
    ReversiControllerInterface controller1 = new HumanPlayerController(model, player1, viewPlayer1);
    ReversiControllerInterface controller2 = new HumanPlayerController(model, player2, viewPlayer2);
    controller1.run();
    controller2.run();
  }
}
