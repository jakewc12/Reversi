package reversimodeltests;

import model.MutableReversiModel;
import model.hexreversi.MutableHexReversi;
import model.squarereversi.MutableSquareReversi;
import org.junit.Before;
import org.junit.Test;
import textualview.SquareReversiTextualView;
import textualview.TextualView;

public class SquareReversiTests {
  private MutableReversiModel game;
  private TextualView tv;
  private StringBuilder gameLog;

  /**
   * initializes game which will be used for testing.
   */
  @Before
  public void init() {
    game = new MutableSquareReversi(8);
  }

  @Test
  public void show(){
    tv = new SquareReversiTextualView(game);
    game.setUpGame(game.getBoard());
    game.startGame();
    System.out.println(tv);
  }


}
