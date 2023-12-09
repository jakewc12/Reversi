package controllertests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import helpers.MockMutableReversiModel;
import model.DiscColor;
import model.MutableReversiModel;
import player.CaptureMostTilesStrategy;
import player.HumanPlayer;
import player.MachinePlayer;
import player.Player;

/**
 * Tests issues with reversisquare players.
 */
public class ReversiSquarePlayerTests {
  private Player player;
  private MutableReversiModel model;
  private Appendable log;

  /**
   * Sets up variables used for testing.
   */
  @Before
  public void init() {
    log = new StringBuffer();
    model = new MockMutableReversiModel(4, log, "square");
    model.setUpGame(model.getBoard());
  }

  /**
   * Tests that getColor works.
   */
  @Test
  public void testMachinePlayerGetColorWorks() {
    player = new MachinePlayer(DiscColor.BLACK, new CaptureMostTilesStrategy());
    Assert.assertEquals(player.getPlayerColor(), DiscColor.BLACK);
  }

  /**
   * Tests that getColor works.
   */
  @Test
  public void testHumanPlayerGetColorWorks() {
    player = new HumanPlayer(model, DiscColor.BLACK);
    Assert.assertEquals(player.getPlayerColor(), DiscColor.BLACK);
  }

  /**
   * Tests that human player doesnt make a move since it's the view that does it.
   */
  @Test
  public void testHumanPlayerMakeMoveDoesntDoAnything() {
    player = new HumanPlayer(model, DiscColor.BLACK);
    Assert.assertFalse(log.toString().contains("Place"));
  }

  /**
   * Tests that machine automatically makes moves thru player.
   */
  @Test
  public void testMachinePlayerPlaces() {
    player = new MachinePlayer(DiscColor.BLACK, new CaptureMostTilesStrategy());
    //model.setUpGame(model.getBoard());
    player.playMove(model);
    Assert.assertTrue(log.toString().contains("Place"));
  }
}
