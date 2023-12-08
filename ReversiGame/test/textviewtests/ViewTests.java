package textviewtests;

import java.io.IOException;
import model.hexreversi.MutableHexReversi;
import model.hexreversi.HexCoordinate;
import model.MutableReversiModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import textualview.HexReversiTextualView;
import textualview.TextualView;


/**
 * A Test class meant to test the Reversi Textual view.
 */
public class ViewTests {

  private MutableReversiModel game;
  private TextualView tv;

  private StringBuilder gameLog;

  private int countNumOfInputInView(String input, String view) {
    int count = 0;
    String[] splitView = view.split(" ");
    for (String character : splitView) {
      if (character.contains(input)) {
        count++;
      }
    }
    return count;
  }

  /**
   * initializes game and gameLog which will be used for testing.
   */
  @Before
  public void init() {
    game = new MutableHexReversi(5);
    gameLog = new StringBuilder();
  }

  /**
   * tests that textualview is correct at start of game.
   */
  @Test
  public void testTextViewOnStart() {
    game.setUpGame(game.getBoard());
    tv = new HexReversiTextualView(game, gameLog);
    try {
      tv.render();
    } catch (IOException e) {
      System.out.println("Could not render: " + e);
    }
    String[] lines = gameLog.toString().split("\n");
    Assert.assertEquals(11, lines.length);
    Assert.assertEquals(3, countNumOfInputInView("X", gameLog.toString()));
    Assert.assertEquals(3, countNumOfInputInView("O", gameLog.toString()));
  }

  // Assumes this as TV board, X as black O as white
  //       _ _ _ _ _ _
  //      _ _ _ _ _ _ _
  //     _ _ _ _ _ _ _ _
  //    _ _ _ _ X _ _ _ _
  //   _ _ _ _ X X _ _ _ _
  //  _ _ _ _ O _ X _ _ _ _
  //   _ _ _ _ X O _ _ _ _
  //    _ _ _ _ _ _ _ _ _
  //     _ _ _ _ _ _ _ _
  //      _ _ _ _ _ _ _
  //       _ _ _ _ _ _

  /**
   * tests that textualview updates as game progresses.
   */
  @Test
  public void testTextViewUpdates() {
    game.setUpGame(game.getBoard());
    game.placeDisc(new HexCoordinate(1, -2, 1));
    tv = new HexReversiTextualView(game, gameLog);
    try {
      tv.render();
    } catch (IOException e) {
      System.out.println("Could not render: " + e);
    }
    String[] lines = gameLog.toString().split("\n");
    Assert.assertEquals(11, lines.length);
    Assert.assertEquals(5, countNumOfInputInView("X", gameLog.toString()));
    Assert.assertEquals(2, countNumOfInputInView("O", gameLog.toString()));
  }
}
