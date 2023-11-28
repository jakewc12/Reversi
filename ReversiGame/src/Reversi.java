import controller.ReversiController;
import controller.ReversiControllerImp;
import digitalviews.DigitalReversiWindow;
import digitalviews.DigitalWindow;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.DiscColor;
import model.MutableReversi;
import model.MutableReversiModel;
import player.CaptureMostTilesStrategy;
import player.HumanPlayer;
import player.MachinePlayer;
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

    // Initialize the DigitalReversiWindow view
    DigitalWindow viewPlayer1 = new DigitalReversiWindow(model);
    DigitalWindow viewPlayer2 = new DigitalReversiWindow(model);

    Player player2 = new MachinePlayer(DiscColor.WHITE, new CaptureMostTilesStrategy());
    Player player1 = new HumanPlayer(model, DiscColor.BLACK);

    // should we do a sleep so that the move isnt made literally automatically
    List<Player> players = makePlayers(args, model);
    ReversiController controller1 = new ReversiControllerImp(model, players.get(0), viewPlayer1);
    ReversiController controller2 = new ReversiControllerImp(model, players.get(1), viewPlayer2);
    model.startGame(model.getBoard());
    viewPlayer1.makeVisible();
    viewPlayer2.makeVisible();
  }

  /**
   * Creates the two players based on the command-line arguments.
   *
   * @param args  the player types given.
   * @param model the model used to play the game.
   * @return the desired players based on the given types.
   */
  private static List<Player> makePlayers(String[] args, MutableReversiModel model) {
    if (args.length < 2) {
      throw new IllegalArgumentException("Not enough players given");
    }
    List<Player> players = new ArrayList<Player>();
    String player1 = args[0];
    String player2 = args[1];
    if ((!Objects.equals(player1, "human") && !Objects.equals(player1, "strategy1"))
        || (!Objects.equals(player2, "human") && !Objects.equals(player2, "strategy1"))) {
      throw new IllegalArgumentException("Invalid strategies given");
    }
    if (player1.equals("human")) {
      players.add(new HumanPlayer(model, DiscColor.BLACK));
    } else if (player1.equals("strategy1")) {
      players.add(new MachinePlayer(DiscColor.BLACK, new CaptureMostTilesStrategy()));
    }
    if (player2.equals("human")) {
      players.add(new HumanPlayer(model, DiscColor.WHITE));
    } else if (player2.equals("strategy1")) {
      players.add(new MachinePlayer(DiscColor.WHITE, new CaptureMostTilesStrategy()));
    }
    return players;
  }
}
