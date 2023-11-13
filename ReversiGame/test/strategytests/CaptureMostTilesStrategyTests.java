package strategytests;

import model.Coordinate;
import model.DiscColor;
import model.MockMutableReversiModel;
import model.MutableReversi;
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

  private Player blackAI;
  private Player whiteAI;
  private ReversiStrategy strategy;
  private Appendable log;

  @Before
  public void init() {
    log = new StringBuffer();
    model = new MockMutableReversiModel(3, log);
    strategy = new CaptureMostTilesStrategy();
    whiteAI = new AIPlayer(DiscColor.WHITE, strategy);
    blackAI = new AIPlayer(DiscColor.BLACK, strategy);
  }

  @Test
  public void testGoesForUpperLeftMostCoordOnTie() {
    model.startGame(model.getBoard());
    whiteAI.playMove(model);
    Assert.assertEquals("Place disc called at (1, -2, 1)\n", log.toString());
  }

  @Test
  public void GoesForMostTiles() {
    model.startGame(model.getBoard());
    blackAI.playMove(model);
    model.skipCurrentTurn();
    blackAI.playMove(model);
    Assert.assertEquals("Place disc called at (1, -2, 1)\nTurn skipped\nPlace "
        + "disc called at (-1, -1, 2)\n", log.toString());
  }
  @Test
  public void worksOnMoreComplexScenarios() {
    TextualView tv = new ReversiTextualView(model);
    model.startGame(model.getBoard());
    while(!model.gameOver()){
      System.out.println("\nBlacks turn");
      blackAI.playMove(model);
      System.out.println(tv);
      System.out.println("\nWhites turn");
      whiteAI.playMove(model);
      System.out.println(tv);
    }

  }
}
