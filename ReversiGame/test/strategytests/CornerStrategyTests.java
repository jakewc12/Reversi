package strategytests;

import java.util.Optional;
import model.Coordinate;
import model.DiscColor;
import model.MockMutableReversiModel;
import model.MutableReversiModel;
import model.player.AIPlayer;
import model.player.GoForCornersStrategy;
import model.player.Player;
import model.player.ReversiStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

    model.placeDisc(new Coordinate(-3, 0, 3));
    model.placeDisc(new Coordinate(-3, 3, 0));
    model.placeDisc(new Coordinate(3, -3, 0));
    model.placeDisc(new Coordinate(0, -3, 3));
    model.placeDisc(new Coordinate(0, 3, -3));
    model.placeDisc(new Coordinate(3, 0, -3));
    Assert.assertEquals(Optional.empty(), strategy.chooseMove(model, player));
  }
}
