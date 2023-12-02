package adaptertests;

import DustinRaymondReversi.view.BasicReversiGUIView;
import adaptation_assignment.RaDusModelAdapter;
import adaptation_assignment.RaymondDustinToOurViewAdapter;
import controller.Controller;
import controller.ReversiController;
import digitalviews.DigitalReversiWindow;
import digitalviews.DigitalWindow;
import model.DiscColor;
import org.junit.Before;
import org.junit.Test;
import player.CaptureMostTilesStrategy;
import player.HumanPlayer;
import player.MachinePlayer;
import player.Player;

public class AdapterTests {

  private RaDusModelAdapter model;
  private DigitalWindow view1;
  private RaymondDustinToOurViewAdapter view2;

  private Player player1;
  private Player player2;

  private Controller controller1;
  private Controller controller2;


  @Before
  private void init(){
    model = new RaDusModelAdapter(3);
    model.setUpGame(model.getBoard());

    view1 = new DigitalReversiWindow(model);
    view2 =
        new RaymondDustinToOurViewAdapter(new BasicReversiGUIView(model));

    player1 = new HumanPlayer(model, DiscColor.BLACK);
    player2 = new MachinePlayer(DiscColor.WHITE, new CaptureMostTilesStrategy());

    controller1 = new ReversiController(model, player1, view1);
    controller2 = new ReversiController(model, player2, view2);

    model.startGame();
  }

  @Test
  public void testRunningDoesntBreakShit(){

  }
}
