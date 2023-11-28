package strategytests;

import java.util.List;
import model.Coordinate;
import model.DiscColor;
import model.MockMutableReversiModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import player.CaptureMostTilesStrategy;
import player.MachinePlayer;
import player.Player;
import player.ReversiStrategy;

/**
 * tests any issues with CaptureMostTilesStrategy.
 */
public class CaptureMostTilesStrategyTests {

  private MockMutableReversiModel model;
  private Player blackAI;
  private Player whiteAI;
  private Appendable log;

  /**
   * initalizes log and the different strategies which will be used for testing.
   */
  @Before
  public void init() {
    log = new StringBuffer();
    model = new MockMutableReversiModel(3, log);
    ReversiStrategy strategy = new CaptureMostTilesStrategy();
    whiteAI = new MachinePlayer(DiscColor.WHITE, strategy);
    blackAI = new MachinePlayer(DiscColor.BLACK, strategy);
  }

  /**
   * tests that at the start of a game, white choose correct move.
   */
  @Test
  public void testGoesForUpperLeftMostCoordOnTie() {
    model.setUpGame(model.getBoard());
    whiteAI.playMove(model);
    Assert.assertTrue(log.toString().contains("Place disc called at (1, -2, 1)"));
  }

  /**
   * tests that throughout the game, the strategy chooses the correct move.
   */
  @Test
  public void GoesForMostTiles() {
    model.setUpGame(model.getBoard());
    blackAI.playMove(model);
    model.skipCurrentTurn();
    blackAI.playMove(model);
    Assert.assertTrue(log.toString().contains("Place disc called at (1, -2, 1)"));
    Assert.assertTrue(log.toString().contains("Turn skipped"));
    Assert.assertTrue(log.toString().contains("disc called at (-1, -1, 2)"));
  }

  /**
   * tests that the strategy works differently for different players.
   */
  @Test
  public void worksWithWhiteMove() {
    model.setUpGame(model.getBoard());
    blackAI.playMove(model);
    whiteAI.playMove(model);
    blackAI.playMove(model);
    Assert.assertTrue(log.toString().contains("Place disc called at (1, -2, 1)"));
    Assert.assertTrue(log.toString().contains("Place disc called at (2, -3, 1)"));
    Assert.assertTrue(log.toString().contains("Place disc called at (-1, -1, 2)"));
  }

  /**
   * tests that the best move doesnt go top left if there is a better move somewhere else.
   */
  @Test
  public void findsBestMoveAndDoesntAlwaysGoTopLeft() {
    model.setUpGame(model.getBoard());
    model.forcePlaceDisc(new Coordinate(3, 0, -3), DiscColor.BLACK);
    model.forcePlaceDisc(new Coordinate(2, 1, -3), DiscColor.WHITE);
    model.forcePlaceDisc(new Coordinate(1, 2, -3), DiscColor.WHITE);
    blackAI.playMove(model);
    Assert.assertTrue(log.toString().contains("Place disc called at (0, 3, -3)"));
    //Assert.assertTrue(log.toString().contains("Place disc called at (0, 3, -3)\n"));
  }

  /**
   * tests that the best move doesnt go top left if there is a better move somewhere else.
   */
  @Test
  public void findsBestMoveAndDoesntAlwaysGoTopLeft2() {
    model.setUpGame(model.getBoard());
    model.forcePlaceDisc(new Coordinate(3, 0, -3), DiscColor.WHITE);
    model.forcePlaceDisc(new Coordinate(2, 1, -3), DiscColor.BLACK);
    model.forcePlaceDisc(new Coordinate(1, 2, -3), DiscColor.BLACK);
    model.changeTurnTo(DiscColor.WHITE);
    whiteAI.playMove(model);
    Assert.assertTrue(log.toString().contains("Place disc called at (0, 3, -3)"));
    //Assert.assertTrue(log.toString().contains("Place disc called at (0, 3, -3)\n"));
  }

  /**
   * tests that if there are no valid moves, the strategy returns an optional empty.
   */
  @Test
  public void returnsEmptyOnNoValidMove() {
    model.setUpGame(model.getBoard());
    model.forcePlaceDisc(new Coordinate(1, -1, 0), DiscColor.BLACK);
    model.forcePlaceDisc(new Coordinate(-1, 0, 1), DiscColor.BLACK);
    model.forcePlaceDisc(new Coordinate(0, 1, -1), DiscColor.BLACK);
    whiteAI.playMove(model);
    blackAI.playMove(model);
    Assert.assertTrue(log.toString().contains("Turn skipped"));
  }

  @Test
  public void checksAllPossibleMovesOnBoard() {
    StringBuilder expected = new StringBuilder();
    model.setUpGame(model.getBoard());
    List<Coordinate> allCoords = model.getAllCoordinates();
    for (Coordinate coordinate : allCoords) {
      expected.append("Checked legal at ").append(coordinate).append("\n");
    }
    expected.append("Place disc called at (1, -2, 1)\n");
    blackAI.playMove(model);
    Assert.assertEquals(expected.toString(), log.toString());
  }
}

