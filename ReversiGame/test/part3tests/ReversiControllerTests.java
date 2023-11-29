package part3tests;

import controller.ReversiController;
import controller.ReversiControllerImp;
import digitalviews.DigitalReversiWindow;
import digitalviews.DigitalWindow;
import model.DiscColor;
import model.MockMutableReversiModel;
import model.MutableReversi;
import model.MutableReversiModel;

import org.junit.Assert;
import org.junit.Test;

import player.CaptureMostTilesStrategy;
import player.HumanPlayer;
import player.MachinePlayer;
import player.Player;

/**
 * Tests potential issues with the controller for Reversi.
 */
public class ReversiControllerTests {

  private ReversiController controller;
  private MutableReversiModel model;
  private Player player;
  private DigitalWindow view;

  /**
   * Tests that invalid inputs given to controller throws exceptions.
   */
  @Test
  public void testInvalidModel() {
    Assert.assertThrows(NullPointerException.class, () -> new ReversiControllerImp(null
            , new HumanPlayer(null, DiscColor.BLACK), new DigitalReversiWindow(null)));
    Assert.assertThrows(IllegalArgumentException.class
            , () -> new ReversiControllerImp(new MutableReversi(-1)
                    , new HumanPlayer(new MutableReversi(-1)
                    , DiscColor.BLACK), new DigitalReversiWindow(new MutableReversi(-1))));
  }

  /**
   * Tests that controller interacts with model correctly.
   */
  @Test
  public void testThatControllerInteractsWithModel() {
    Appendable log = new StringBuffer();
    model = new MockMutableReversiModel(3, log);
    model.setUpGame(model.getBoard());
    player = new MachinePlayer(DiscColor.BLACK, new CaptureMostTilesStrategy());
    DigitalWindow view = new DigitalReversiWindow(model);
    controller = new ReversiControllerImp(model, player, view);
    model.startGame();
    System.out.println(log.toString());
    Assert.assertTrue(log.toString().contains("Place disc"));
  }

  /**
   * Tests that when no moves are available for AI, move is skipped.
   */
  @Test
  public void testWhenNoMovesAvailableTurnSkipped() {
    Appendable log = new StringBuffer();
    model = new MockMutableReversiModel(1, log);
    model.setUpGame(model.getBoard());
    player = new MachinePlayer(DiscColor.BLACK, new CaptureMostTilesStrategy());
    DigitalWindow view = new DigitalReversiWindow(model);
    controller = new ReversiControllerImp(model, player, view);
    model.startGame();
    Assert.assertFalse(log.toString().contains("Place disc"));
  }

  /**
   * Tests that model can have multiple listeners.
   */
  @Test
  public void testModelCanInteractWithMultipleListeners() {
    Appendable log = new StringBuffer();
    model = new MockMutableReversiModel(1, log);
    model.setUpGame(model.getBoard());
    player = new MachinePlayer(DiscColor.BLACK, new CaptureMostTilesStrategy());
    Player player2 = new MachinePlayer(DiscColor.WHITE, new CaptureMostTilesStrategy());
    DigitalWindow view = new DigitalReversiWindow(model);
    controller = new ReversiControllerImp(model, player, view);
    ReversiController controller2 = new ReversiControllerImp(model, player2, view);
    model.startGame();
    Assert.assertTrue(log.toString().contains("added feature"));
  }
}
