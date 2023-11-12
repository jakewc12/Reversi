package strategytests;

import model.Coordinate;
import model.DiscColor;
import model.MockMutableReversiModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
    Assert.assertEquals("Place disc called at (1, -2, 1)\n", log.toString());
  }

  @Test
  public void GoesForMostTiles() {
    TextualView tv = new ReversiTextualView(model);
    model.startGame(model.getBoard());
    player.playMove(model);
    model.placeDisc(new Coordinate(1, -1, 0));
    player.playMove(model);
    System.out.println(tv);
    Assert.assertEquals("Place disc called at (1, -2, 1)\nPlace disc called at (1, -1, 0)\nPlace "
        + "disc called at (-1, -1, 2)\n", log.toString());
  }
}
