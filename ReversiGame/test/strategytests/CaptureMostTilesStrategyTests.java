package strategytests;

import model.DiscColor;
import model.MockMutableReversiModel;
import model.MutableReversi;
import model.MutableReversiModel;
import player.AIPlayer;
import player.CaptureMostTilesStrategy;
import player.Player;
import player.ReversiStrategy;
import org.junit.Before;
import org.junit.Test;
import view.ReversiTextualView;
import view.TextualView;

public class CaptureMostTilesStrategyTests {
  private MutableReversiModel mockModel;

  private MutableReversiModel model;
  private Player player;
  private ReversiStrategy strategy;
  private Appendable log;

  @Before
  public void init() {
    log = new StringBuffer();
    mockModel = new MockMutableReversiModel(3, log);
    model = new MutableReversi(3);
    strategy = new CaptureMostTilesStrategy();
    player = new AIPlayer(DiscColor.BLACK, strategy);
    model.startGame(model.getBoard());
  }

  @Test
  public void testIdentifiesBestMove(){
    TextualView tv = new ReversiTextualView(model);
    System.out.println(tv);
    player.playMove(model);
    System.out.println(tv);
  }
}
