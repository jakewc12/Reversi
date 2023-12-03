import DustinRaymondReversi.view.BasicReversiGUIView;
import adaptation_assignment.RaDusModelAdapter;
import adaptation_assignment.RaDusStrategyAdapter;
import adaptation_assignment.RaDusToOurViewAdapter;
import controller.Controller;
import controller.ReversiController;
import digitalviews.DigitalReversiWindow;
import digitalviews.DigitalWindow;

import java.util.ArrayList;
import java.util.List;

import model.DiscColor;
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
public final class ReversiAdapted {

  /**
   * The main method for starting the Reversi game.
   *
   * @param args The command-line arguments (not used in this application).
   */
  public static void main(String[] args) {
    // Initialize the MutableReversiModel with a board size of 3
    RaDusModelAdapter model = new RaDusModelAdapter(3);
    model.setUpGame(model.getBoard());

    // Initialize the DigitalReversiWindow view
    DigitalWindow viewPlayer1 = new DigitalReversiWindow(model);
    DigitalWindow viewPlayer2 = new RaDusToOurViewAdapter(new BasicReversiGUIView(model));

    Player player2 = new MachinePlayer(DiscColor.BLACK, new CaptureMostTilesStrategy());
    Player player1 = new MachinePlayer(DiscColor.WHITE, new RaDusStrategyAdapter());

    // should we do a sleep so that the move isnt made literally automatically
    List<Player> players = makePlayers(args, model);
    Controller controller1 = new ReversiController(model, player1, viewPlayer1);
    Controller controller2 = new ReversiController(model, player2, viewPlayer2);
    model.startGame();
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
    List<Player> players = new ArrayList<Player>();
    //by default, if player1 is human and player2 is AI unless command-line says other
    if (args.length == 0) {
      players.add(new HumanPlayer(model, DiscColor.BLACK));
      players.add(new MachinePlayer(DiscColor.WHITE, new CaptureMostTilesStrategy()));
    } else if (args.length == 1) {
      //chooses first players strategy
      if (args[0].equalsIgnoreCase("strategy1")) {
        players.add(new MachinePlayer(DiscColor.BLACK, new CaptureMostTilesStrategy()));
      } else {
        players.add(new HumanPlayer(model, DiscColor.BLACK));
        players.add(new MachinePlayer(DiscColor.WHITE, new CaptureMostTilesStrategy()));
      }
    } else {
      String player1 = args[0];
      String player2 = args[1];
      if (player1.equals("strategy1")) {
        players.add(new MachinePlayer(DiscColor.BLACK, new CaptureMostTilesStrategy()));
      } else {
        players.add(new HumanPlayer(model, DiscColor.BLACK));
      }
      if (player2.equals("human")) {
        players.add(new HumanPlayer(model, DiscColor.WHITE));
      } else {
        players.add(new MachinePlayer(DiscColor.WHITE, new CaptureMostTilesStrategy()));
      }
    }
    return players;
  }
}
