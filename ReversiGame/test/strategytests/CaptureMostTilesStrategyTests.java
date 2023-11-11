package strategytests;

import java.util.Optional;
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
    Coordinate expected = new Coordinate(1, -2, 1);
    Optional<Coordinate> moveChosen = strategy.chooseMove(model, player);

    Assert.assertTrue(moveChosen.isPresent());
    Assert.assertTrue(model.isLegalMove(moveChosen.get()));
    Assert.assertEquals(expected, moveChosen.get());
  }
}
