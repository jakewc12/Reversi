package controllertests;

import controller.Controller;
import controller.ReversiController;
import digitalviews.DigitalReversiWindow;
import digitalviews.DigitalWindow;
import helpers.MockDigitalReversiWindow;
import helpers.MockMutableReversiModel;

import model.DiscColor;
import model.hexreversi.MutableHexReversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import player.CaptureMostTilesStrategy;
import player.MachinePlayer;
import player.Player;

/**
 * Tests potential issues with the controller for Reversi.
 */
public class SquareReversiControllerTests {

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
    model = new MockMutableReversiModel(4, modelLog, "square");
    model.setUpGame(model.getBoard());
    player = new MachinePlayer(DiscColor.BLACK, new CaptureMostTilesStrategy());
    view = new MockDigitalReversiWindow(model, viewLog);
  }

  /**
   * Tests that invalid inputs given to controller throws exceptions.
   */
  @Test
  public void testInvalidModel() {
    Assert.assertThrows(IllegalArgumentException.class, () -> new ReversiController(null
        , player, new DigitalReversiWindow(null)));
    Assert.assertThrows(IllegalArgumentException.class
        , () -> new ReversiController(new MutableHexReversi(-1), player
            , new DigitalReversiWindow(new MutableHexReversi(-1))));
  }


  /**
   * Tests that when no moves are available for AI, move is skipped.
   */
  @Test
  public void testWhenNoMovesAvailableTurnSkipped() {
    model = new MockMutableReversiModel(2, modelLog, "square");
    model.setUpGame(model.getBoard());
    model.startGame();
    Assert.assertFalse(modelLog.toString().contains("Place disc"));
  }

  /**
   * Tests that model can have multiple listeners.
   */
  @Test
  public void testModelCanInteractWithMultipleListeners() {
    model = new MockMutableReversiModel(2, modelLog, "square");
    model.setUpGame(model.getBoard());
    Player player2 = new MachinePlayer(DiscColor.WHITE, new CaptureMostTilesStrategy());
    Controller controller2 = new ReversiController(model, player2, view);
    model.startGame();
    Assert.assertTrue(modelLog.toString().contains("added feature"));
  }

  /**
   * Tests that the model contains multiple features if added.
   */
  @Test
  public void controllerAddsItselfAsFeatureOnConstruction() {
    controller = new ReversiController(model, player, view);
    Controller controller2 = new ReversiController(model, player, view);
    Controller controller3 = new ReversiController(model, player, view);
    Assert.assertTrue(modelLog.toString().contains("added feature to model" +
        "\nadded feature to model\nadded feature to model\n"));
  }

  /**
   * Tests that the view works with controller.
   */
  @Test
  public void controllerInteractsWithViewOnRun() {
    controller = new ReversiController(model, player, view);
    controller.run();
    Assert.assertEquals("Feature added to view\n" + "Refreshed window\n"
        + "Made window visible\n", viewLog.toString());
  }

}
