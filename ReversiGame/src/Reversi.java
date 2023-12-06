import dustinraymondreversi.strategy.AvoidCornerTrapStrategy;
import dustinraymondreversi.strategy.ChooseCornerStrategy;
import dustinraymondreversi.strategy.ChosenByBothStrategyCombinator;
import dustinraymondreversi.strategy.GreedilyCaptureStrategy;
import dustinraymondreversi.view.BasicReversiGUIView;
import adaptionassignment.RaDusModelAdapter;
import adaptionassignment.RaDusStrategyAdapter;
import adaptionassignment.RaDusToOurViewAdapter;
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
public final class Reversi {

  /**
   * The main method for starting the Reversi game.
   *
   * @param args The command-line arguments (not used in this application).
   */
  public static void main(String[] args) {
    // Initialize the MutableReversiModel with a board size of 3
    RaDusModelAdapter model = new RaDusModelAdapter(3);
    model.setUpGame(model.getBoard());

    List<Player> players = makePlayers(args, model);
    Player player1 = players.get(0);
    Player player2 = players.get(1);
    // should we do a sleep so that the move isnt made literally automatically
    // Initialize the DigitalReversiWindow view
    DigitalWindow viewPlayer1;
    DigitalWindow viewPlayer2;
    if (player1.getStrategy().isEmpty() || (player1.getStrategy().isPresent()
            && !(player1.getStrategy().get() instanceof RaDusStrategyAdapter))) {
      viewPlayer1 = new DigitalReversiWindow(model);
    } else {
      viewPlayer1 = new RaDusToOurViewAdapter(new BasicReversiGUIView(model));
    }
    if (player2.getStrategy().isEmpty() || (player2.getStrategy().isPresent()
            && !(player2.getStrategy().get() instanceof RaDusStrategyAdapter))) {
      viewPlayer2 = new DigitalReversiWindow(model);
    } else {
      viewPlayer2 = new RaDusToOurViewAdapter(new BasicReversiGUIView(model));
    }

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
      players.add(new MachinePlayer(DiscColor.BLACK, new CaptureMostTilesStrategy()));
      players.add(new MachinePlayer(DiscColor.WHITE, new RaDusStrategyAdapter(
              new GreedilyCaptureStrategy())));
    } else if (args.length == 1) {
      //chooses first players strategy
      players.add(choosePlayer(args[0], DiscColor.BLACK, model));
      players.add(new MachinePlayer(DiscColor.WHITE, new CaptureMostTilesStrategy()));
    } else {
      String player1 = args[0];
      String player2 = args[1];
      players.add(choosePlayer(player1, DiscColor.BLACK, model));
      players.add(choosePlayer(player2, DiscColor.WHITE, model));
    }
    return players;
  }

  private static Player choosePlayer(String input, DiscColor color, MutableReversiModel model) {
    if (input.equalsIgnoreCase("strategy1")) {
      return (new MachinePlayer(color, new CaptureMostTilesStrategy()));
    } else if (input.equalsIgnoreCase("providerStrategy1")) {
      return (new MachinePlayer(color, new RaDusStrategyAdapter(new GreedilyCaptureStrategy())));
    } else if (input.equalsIgnoreCase("providerStrategy2")) {
      return (new MachinePlayer(color, new RaDusStrategyAdapter(new AvoidCornerTrapStrategy())));
    } else if (input.equalsIgnoreCase("providerStrategy3")) {
      return (new MachinePlayer(color, new RaDusStrategyAdapter(new ChosenByBothStrategyCombinator(
              new AvoidCornerTrapStrategy()
              , new ChooseCornerStrategy()))));
    }
    return new HumanPlayer(model, color);
  }
}
