package strategytests;

import model.DiscColor;
import model.MockMutableReversiModel;
import model.MutableReversiModel;
import model.player.AIPlayer;
import model.player.GoForCornersStrategy;
import model.player.Player;
import model.player.ReversiStrategy;
import org.junit.Before;
import org.junit.Test;

public class CaptureMostTilesStrategyTests {
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
  public void testIdentifiesBestMove(){

  }
}
