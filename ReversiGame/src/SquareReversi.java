import java.util.List;

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

public class SquareReversi {
  public static void main(String[] args) {
    // Initialize the MutableReversiModel with a board size of 3
    MutableReversiModel model = new MutableSquareReversi(4);
    model.setUpGame(model.getBoard());

    // Initialize the DigitalReversiWindow view
    DigitalWindow viewPlayer1 = new DigitalReversiWindow(model);
    DigitalWindow viewPlayer2 = new DigitalReversiWindow(model);

    Player player2 = new HumanPlayer(model, DiscColor.BLACK);
    Player player1 = new MachinePlayer(DiscColor.WHITE, new CaptureMostTilesStrategy());

    // should we do a sleep so that the move isnt made literally automatically
    Controller controller1 = new ReversiController(model, player1, viewPlayer1);
    Controller controller2 = new ReversiController(model, player2, viewPlayer2);
    model.startGame();
    viewPlayer1.makeVisible();
    viewPlayer2.makeVisible();
  }

}
