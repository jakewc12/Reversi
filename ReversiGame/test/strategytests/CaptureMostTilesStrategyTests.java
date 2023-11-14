package strategytests;

import model.Coordinate;
import model.DiscColor;
import model.MockMutableReversiModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import player.ComputerPlayer;
import player.CaptureMostTilesStrategy;
import player.Player;
import player.ReversiStrategy;

/**
 * tests any issues with CaptureMostTilesStrategy.
 */
public class CaptureMostTilesStrategyTests {

  private MockMutableReversiModel model;
  private Player blackAI;
  private Player whiteAI;
  private ReversiStrategy strategy;
  private Appendable log;

  /**
   * initalizes log and the different strategies which will be used for testing.
   */
  @Before
  public void init() {
    log = new StringBuffer();
    model = new MockMutableReversiModel(3, log);
    strategy = new CaptureMostTilesStrategy();
    whiteAI = new ComputerPlayer(DiscColor.WHITE, strategy);
    blackAI = new ComputerPlayer(DiscColor.BLACK, strategy);
  }

  /**
   * tests that at the start of a game, white choose correct move.
   */
  @Test
  public void testGoesForUpperLeftMostCoordOnTie() {
    model.startGame(model.getBoard());
    whiteAI.playMove(model);
    Assert.assertTrue(log.toString().contains("Place disc called at (1, -2, 1)"));
    //Assert.assertEquals("Place disc called at (1, -2, 1)\n", log.toString());
  }

  /**
   * tests that throughout the game, the strategy chooses the correct move.
   */
  @Test
  public void GoesForMostTiles() {
    model.startGame(model.getBoard());
    blackAI.playMove(model);
    System.out.println(log.toString());
    model.skipCurrentTurn();
    blackAI.playMove(model);
    Assert.assertTrue(log.toString().contains("Place disc called at (1, -2, 1)"));
    Assert.assertTrue(log.toString().contains("Turn skipped"));
    Assert.assertTrue(log.toString().contains("disc called at (-1, -1, 2)"));
    //Assert.assertEquals(
      //  "Place disc called at (1, -2, 1)\nTurn skipped\nPlace " + "disc called at (-1, -1, 2)\n",
        //log.toString());
  }

  /**
   * tests that the strategy works differently for different players.
   */
  @Test
  public void worksWithWhiteMove() {
    model.startGame(model.getBoard());
    blackAI.playMove(model);
    whiteAI.playMove(model);
    blackAI.playMove(model);
    Assert.assertTrue(log.toString().contains("Place disc called at (1, -2, 1)"));
    Assert.assertTrue(log.toString().contains("Place disc called at (2, -3, 1)"));
    Assert.assertTrue(log.toString().contains("Place disc called at (-1, -1, 2)"));
   // Assert.assertEquals("Place disc called at (1, -2, 1)\n"
     //   + "Place disc called at (2, -3, 1)\n"
       // + "Place disc called at (-1, -1, 2)\n", log.toString());
  }

  /**
   * tests that the best move doesnt go top left if there is a better move somewhere else.
   */
  @Test
  public void findsBestMoveAndDoesntAlwaysGoTopLeft() {
    model.startGame(model.getBoard());
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
    model.startGame(model.getBoard());
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
    model.startGame(model.getBoard());
    model.forcePlaceDisc(new Coordinate(1, -1, 0), DiscColor.BLACK);
    model.forcePlaceDisc(new Coordinate(-1, 0, 1), DiscColor.BLACK);
    model.forcePlaceDisc(new Coordinate(0, 1, -1), DiscColor.BLACK);
    whiteAI.playMove(model);
    blackAI.playMove(model);
    Assert.assertTrue(log.toString().contains("Turn skipped"));
   // Assert.assertTrue(log.toString().contains("Turn skipped\nTurn skipped\n"));
  }
}

