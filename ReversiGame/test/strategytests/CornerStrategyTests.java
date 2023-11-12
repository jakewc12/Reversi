package strategytests;

import java.awt.*;
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
import player.AIPlayer;
import player.Player;
import player.ReversiStrategy;

public class CornerStrategyTests {
  private MockMutableReversiModel model;
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
    model.startGame(model.getBoard());
    Optional<Coordinate> cord = strategy.chooseMove(model, player);
    String oldLog = log.toString();
    log = new StringBuffer();
    model = new MockMutableReversiModel(3, log);
    model.startGame(model.getBoard());
    model.placeDisc(new Coordinate(-3, 0, 3));
    Optional<Coordinate> cord2 = strategy.chooseMove(model, player);
    System.out.println(oldLog);
    System.out.println(log.toString());
    Assert.assertNotEquals(oldLog, log.toString());
  }

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
