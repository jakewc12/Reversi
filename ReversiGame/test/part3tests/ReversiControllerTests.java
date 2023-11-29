package part3tests;

import controller.Controller;
import controller.ReversiController;
import digitalviews.DigitalReversiWindow;
import digitalviews.DigitalWindow;
import helpers.MockDigitalReversiWindow;
import helpers.MockMutableReversiModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.DiscColor;
import model.MutableReversi;
import model.MutableReversiModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import player.CaptureMostTilesStrategy;
import player.HumanPlayer;
import player.MachinePlayer;
import player.Player;

/**
 * Tests potential issues with the controller for Reversi.
 */
public class ReversiControllerTests {

  private Controller controller;
  private MockMutableReversiModel model;
  Appendable modelLog;
  Appendable viewLog;
  private Player player;
  private DigitalWindow view;


  /**
   * Sets up variables used for testing.
   */
  @Before
  public void init() {
    modelLog = new StringBuffer();
    viewLog = new StringBuffer();
    model = new MockMutableReversiModel(3, modelLog);
    model.setUpGame(model.getBoard());
    player = new MachinePlayer(DiscColor.BLACK, new CaptureMostTilesStrategy());
    view = new MockDigitalReversiWindow(model, viewLog);
  }

  /**
   * Tests that invalid inputs given to controller throws exceptions.
   */
  @Test
  public void testInvalidModel() {
    Assert.assertThrows(NullPointerException.class,
        () -> new ReversiController(null, new HumanPlayer(null, DiscColor.BLACK),
            new DigitalReversiWindow(null)));
    Assert.assertThrows(IllegalArgumentException.class,
        () -> new ReversiController(new MutableReversi(-1),
            new HumanPlayer(new MutableReversi(-1), DiscColor.BLACK),
            new DigitalReversiWindow(new MutableReversi(-1))));
  }

  /**
   * Tests that controller interacts with model correctly.
   */
  @Test
  public void testThatControllerInteractsWithModel() {
    controller = new ReversiController(model, player, view);
    model.startGame();
    Assert.assertTrue(modelLog.toString().contains("Place disc"));
  }

  /**
   * Tests that when no moves are available for AI, move is skipped.
   */
  @Test
  public void testWhenNoMovesAvailableTurnSkipped() {
    model = new MockMutableReversiModel(1, modelLog);
    model.setUpGame(model.getBoard());
    model.startGame();
    Assert.assertFalse(modelLog.toString().contains("Place disc"));
  }

  /**
   * Tests that model can have multiple listeners.
   */
  @Test
  public void testModelCanInteractWithMultipleListeners() {
    model = new MockMutableReversiModel(1, modelLog);
    model.setUpGame(model.getBoard());
    Player player2 = new MachinePlayer(DiscColor.WHITE, new CaptureMostTilesStrategy());
    Controller controller2 = new ReversiController(model, player2, view);
    model.startGame();
    Assert.assertTrue(modelLog.toString().contains("added feature"));
  }

  @Test
  public void controllerAddsItselfAsFeatureOnConstruction() {
    controller = new ReversiController(model, player, view);
    Controller controller2 = new ReversiController(model, player, view);
    Controller controller3 = new ReversiController(model, player, view);
    Assert.assertTrue(modelLog.toString()
        .contains("added feature to model\nadded feature to model\nadded feature to model\n"));
  }

  @Test
  public void controllerInteractsWithViewOnRun() {
    controller = new ReversiController(model, player, view);
    controller.run();
    Assert.assertEquals("Feature added to view\n" + "Refreshed window\n" + "Made window visible\n",
        viewLog.toString());
  }

  /**
   * Creates two controllers and then tests that the other controller gets notified when a disc
   * gets placed
   */
  @Test
  public void allControllersGetNotifiedMoveWasMade() {

    model.addBanItem("getNumFlipsOnMove");
    view = new MockDigitalReversiWindow(model, modelLog);
    controller = new ReversiController(model, player, view);
    Controller controller2 = new ReversiController(model, player, view);
    model.startGame();

    Assert.assertTrue(modelLog.toString().contains("Place disc called at"));

    String[] split = modelLog.toString().split("Notified controller");
    Assert.assertEquals(3,split.length);
  }
}
