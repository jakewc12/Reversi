package strategytests;

import java.util.Optional;
import model.Coordinate;
import model.DiscColor;
import model.MockMutableReversiModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Text;
import player.AIPlayer;
import player.CaptureMostTilesStrategy;
import player.Player;
import player.ReversiStrategy;
import view.ReversiTextualView;
import view.TextualView;

public class CaptureMostTilesStrategyTests {

  private MockMutableReversiModel model;

  private Player player;
  private ReversiStrategy strategy;
  private Appendable log;

  @Before
  public void init() {
    log = new StringBuffer();
    model = new MockMutableReversiModel(3, log);
    strategy = new CaptureMostTilesStrategy();
    player = new AIPlayer(DiscColor.BLACK, strategy);
  }

  @Test
  public void testGoesForUpperLeftMostCoordOnTie() {
    model.startGame(model.getBoard());
    player.playMove(model);
    Assert.assertEquals("Place disc called at (1, -2, 1)", log.toString());
  }
  @Test
  public void GoesForMostTiles() {
    TextualView tv = new ReversiTextualView(model);
    model.startGame(model.getBoard());
    System.out.println(tv);
    player.playMove(model);
    System.out.println(tv);
    player.playMove(model);
    System.out.println(tv);
    Assert.assertEquals("Place disc called at (1, -2, 1)", log.toString());
  }
}
