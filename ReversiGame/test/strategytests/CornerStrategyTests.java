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
import model.MutableReversiModel;
import player.GoForCornersStrategy;
import player.AIPlayer;
import player.Player;
import player.ReversiStrategy;

public class CornerStrategyTests {
  private MutableReversiModel model;
  private Player player;
  private ReversiStrategy strategy;
  private Appendable log;

  @Before
  public void init() {
    log = new StringBuffer();
    model = new MockMutableReversiModel(3, log);
    player = new AIPlayer(DiscColor.BLACK, new GoForCornersStrategy());
    strategy = new GoForCornersStrategy();
  }

  @Test
  public void testCornerTilesFound() {
    strategy.chooseMove(model, player);
    String logString = log.toString();
    System.out.println(logString);
    Assert.assertFalse(logString.contains("1"));
    Assert.assertFalse(logString.contains("2"));
    Assert.assertTrue(logString.contains("-3"));
    Assert.assertTrue(logString.replace("-3", "").contains("3"));

    log = new StringBuffer();
    model = new MockMutableReversiModel(3, log);
    model.placeDisc(new Coordinate(-3, 0, 3));
    strategy.chooseMove(model, player);
    Assert.assertNotEquals(logString, log.toString());
    Assert.assertFalse(log.toString().contains("1"));
    Assert.assertFalse(log.toString().contains("2"));
  }

  @Test
  public void testNoCornersEmptyThrowIllegalState() {
    List<HexagonCell> filledBoard = new ArrayList<>();
    model = new MockMutableReversiModel(1,log);
    filledBoard.add(new GameCell(new GameDisc(DiscColor.BLACK),new Coordinate(-1,1,0)));
    filledBoard.add(new GameCell(new GameDisc(DiscColor.BLACK),new Coordinate(-1,0,1)));
    filledBoard.add(new GameCell(new GameDisc(DiscColor.BLACK),new Coordinate(1,-1,0)));
    filledBoard.add(new GameCell(new GameDisc(DiscColor.BLACK),new Coordinate(0,-1,1)));
    filledBoard.add(new GameCell(new GameDisc(DiscColor.BLACK),new Coordinate(0,1,-1)));
    filledBoard.add(new GameCell(new GameDisc(DiscColor.BLACK),new Coordinate(1,0,-1)));
    model.startGame(filledBoard);
    Assert.assertEquals(Optional.empty(), strategy.chooseMove(model, player));
  }
}
