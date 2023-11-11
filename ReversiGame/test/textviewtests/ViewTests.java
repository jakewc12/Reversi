package textviewtests;

import java.io.IOException;

import model.Coordinate;
import model.MutableReversi;
import model.MutableReversiModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import view.ReversiTextualView;
import view.TextualView;


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
    game = new MutableReversi(5);
    gameLog = new StringBuilder();
  }

  /**
   * tests that textualview is correct at start of game.
   */
  @Test
  public void testTextViewOnStart() {
    game.startGame(game.getBoard());
    tv = new ReversiTextualView(game, gameLog);
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
    game.startGame(game.getBoard());
    game.placeDisc(new Coordinate(1, -2, 1));
    tv = new ReversiTextualView(game, gameLog);
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
