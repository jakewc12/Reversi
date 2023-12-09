package controllertests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.DiscColor;
import helpers.MockMutableHexReversiModel;
import model.MutableReversiModel;
import player.CaptureMostTilesStrategy;
import player.HumanPlayer;
import player.MachinePlayer;
import player.Player;

/**
 * Tests potential issues with the player class.
 */
public class PlayerTests {
  private Player player;
  private MutableReversiModel model;
  private Appendable log;

  /**
   * Sets up variables used for testing.
   */
  @Before
  public void init() {
    log = new StringBuffer();
    model = new MockMutableHexReversiModel(3, log);
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
    player.playMove(model);
    Assert.assertTrue(log.toString().contains("Place"));
  }
}
