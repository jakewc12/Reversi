package strategytests;

import java.util.ArrayList;
import java.util.List;

import model.GameCell;
import model.GameDisc;
import model.HexagonCell;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import model.Coordinate;
import model.DiscColor;
import model.MockMutableReversiModel;
import player.GoForCornersStrategy;
import player.ComputerPlayer;
import player.Player;
import player.ReversiStrategy;

/**
 * tests any issues relating to GoForCornerStrategy.
 */
public class CornerStrategyTests {
  private MockMutableReversiModel model;
  private Player player;
  private ReversiStrategy strategy;
  private Appendable log;

  /**
   * creates the log and different things that are used for testing.
   */
  @Before
  public void init() {
    log = new StringBuffer();
    model = new MockMutableReversiModel(3, log);
    player = new ComputerPlayer(DiscColor.BLACK, new GoForCornersStrategy());
    strategy = new GoForCornersStrategy();
  }

  /**
   * tests that if there are legal corner tiles to play at, it returns that.
   */
  @Test
  public void testCornerTilesFound() {
    model.startGame(model.getBoard());
    Optional<Coordinate> cord = strategy.chooseMove(model, player);
    String oldLog = log.toString();
    log = new StringBuffer();
    model = new MockMutableReversiModel(3, log);
    model.startGame(model.getBoard());
    model.forcePlaceDisc(new Coordinate(-3, 0, 3));
    Optional<Coordinate> cord2 = strategy.chooseMove(model, player);
    System.out.println(oldLog);
    System.out.println(log.toString());
    Assert.assertNotEquals(oldLog, log.toString());
  }

  /**
   * tests that when there are no corners legal to place, it returns an optional empty.
   */
  @Test
  public void testNoCornersEmptyReturnsEmptyOptionalMove() {
    List<HexagonCell> filledBoard = new ArrayList<>();
    model = new MockMutableReversiModel(1, log);
    filledBoard.add(new GameCell(new GameDisc(DiscColor.BLACK), new Coordinate(0,0,0)));
    filledBoard.add(new GameCell(new GameDisc(DiscColor.BLACK), new Coordinate(-1, 1, 0)));
    filledBoard.add(new GameCell(new GameDisc(DiscColor.BLACK), new Coordinate(-1, 0, 1)));
    filledBoard.add(new GameCell(new GameDisc(DiscColor.BLACK), new Coordinate(1, -1, 0)));
    filledBoard.add(new GameCell(new GameDisc(DiscColor.BLACK), new Coordinate(0, -1, 1)));
    filledBoard.add(new GameCell(new GameDisc(DiscColor.BLACK), new Coordinate(0, 1, -1)));
    filledBoard.add(new GameCell(new GameDisc(DiscColor.BLACK), new Coordinate(1, 0, -1)));
    model.startGame(filledBoard);
    Assert.assertEquals(Optional.empty(), strategy.chooseMove(model, player));

    model = new MockMutableReversiModel(1,log);
    model.startGame(model.getBoard());
    Assert.assertEquals(Optional.empty(), strategy.chooseMove(model, player));
  }
}
