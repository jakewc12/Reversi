package TextViewTests;

import model.GameCell;
import model.MutableReversi;
import model.MutableReversiModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import view.ReversiTextualView;
import view.TextualView;


/**
 * A Test class meant to test the Reversi Textual view.
 */
public class ViewTests {

  private MutableReversiModel game;
  private TextualView tv;

  private Appendable out;

  @Before
  public void init() {
    game = new MutableReversi();
    out = new StringBuilder();
  }

  @Test
  public void testTextViewOnStart() {
    game.startGame(3);
    tv = new ReversiTextualView(game, out);
    System.out.println(tv.toString());
    String[] lines = out.toString().split("\n");
    String[] blackTokenCount = out.toString().split("X");
    String[] whiteTokenCount = out.toString().split("O");
    Assert.assertEquals(10, lines.length);
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
    tv = new ReversiTextualView(game, out);
    String[] lines = out.toString().split("\n");
    String[] blackTokenCount = out.toString().split("X");
    String[] whiteTokenCount = out.toString().split("O");
    Assert.assertEquals(10, lines.length);
    Assert.assertEquals(5, blackTokenCount.length);
    Assert.assertEquals(2, whiteTokenCount.length);
  }
}
