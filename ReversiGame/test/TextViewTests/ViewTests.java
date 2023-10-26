package TextViewTests;

import java.io.IOException;
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

  @Before
  public void init() {
    game = new MutableReversi();
    gameLog = new StringBuilder();
  }

  @Test
  public void testTextViewOnStart() {
    game.startGame(5);
    tv = new ReversiTextualView(game, gameLog);
    try {
      tv.render();
    } catch (IOException e) {
      System.out.println("Could not render: " + e);
    }
    String[] lines = gameLog.toString().split("\n");
    String[] blackTokenCount = gameLog.toString().split("X");
    String[] whiteTokenCount = gameLog.toString().split("O");
    Assert.assertEquals(11, lines.length);
    Assert.assertEquals(3, blackTokenCount.length);
    Assert.assertEquals(3, whiteTokenCount.length);
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
  @Test
  public void testTextViewUpdates() {
    game.startGame(5);
    game.placeDisc(1, -2, 1);
    tv = new ReversiTextualView(game, gameLog);
    try {
      tv.render();
    } catch (IOException e) {
      System.out.println("Could not render: " + e);
    }

    String[] lines = gameLog.toString().split("\n");
    String[] blackTokenCount = gameLog.toString().split("X");
    String[] whiteTokenCount = gameLog.toString().split("O");
    Assert.assertEquals(11, lines.length);
    Assert.assertEquals(5, blackTokenCount.length);
    Assert.assertEquals(2, whiteTokenCount.length);
  }
}
