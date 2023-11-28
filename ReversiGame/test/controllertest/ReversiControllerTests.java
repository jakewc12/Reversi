package controllertest;

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

  @Test
  public void testInvalidModel() {
    Assert.assertThrows(NullPointerException.class, () -> new ReversiControllerImp(null
        , new HumanPlayer(null, DiscColor.BLACK), new DigitalReversiWindow(null)));
    Assert.assertThrows(IllegalArgumentException.class
        , () -> new ReversiControllerImp(new MutableReversi(-1)
            , new HumanPlayer(new MutableReversi(-1)
            , DiscColor.BLACK), new DigitalReversiWindow(new MutableReversi(-1))));
  }

  @Test
  public void testThatControllerInteractsWithModel() {
    Appendable log = new StringBuffer();
    model = new MockMutableReversiModel(3, log);

    player = new MachinePlayer(DiscColor.BLACK, new CaptureMostTilesStrategy());
    DigitalWindow view = new DigitalReversiWindow(model);
    controller = new ReversiControllerImp(model, player, view);
    controller.run();
    model.setUpGame(model.getBoard());
    Assert.assertTrue(log.toString().contains("Place disc"));
  }

  @Test
  public void testWhenNoMovesAvailableTurnSkipped() {
    Appendable log = new StringBuffer();
    model = new MockMutableReversiModel(1, log);
    player = new MachinePlayer(DiscColor.BLACK, new CaptureMostTilesStrategy());
    DigitalWindow view = new DigitalReversiWindow(model);
    controller = new ReversiControllerImp(model, player, view);
    controller.run();
    model.setUpGame(model.getBoard());
    Assert.assertFalse(log.toString().contains("Place disc"));
  }
}
