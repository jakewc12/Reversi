package ReversiModelTests;

import model.GameDisc.DiscColor;
import model.MutableReversi;
import model.MutableReversiModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Text;

import view.ReversiTextualView;
import view.TextualView;

/**
 * Test meant for the MutableReversi class. These test should test every method and thrown exception
 * inside the class.
 */
public class MutableReversiTests {

  private MutableReversiModel game = new MutableReversi();

  @Before
  public void init() {
    game = new MutableReversi();
  }

  /**
   * Ensure the size of the board is not less than 1.
   */
  @Test
  public void startGameThrowsOnInvalidSize() {
    Assert.assertThrows(IllegalArgumentException.class, () -> game.startGame(-1));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.startGame(0));
  }


  @Test
  public void getRadiusAndGetBoardSizeWorks() {
    game.startGame(5);
    Assert.assertEquals(11, game.getBoardSize());
    Assert.assertEquals(5, game.getBoardRadius());
  }

  @Test
  public void cannotPlaceGameDiscOnGameDisc() {
    game.startGame(5);
    Assert.assertSame(DiscColor.GREY, game.getColorAt(0, 0, 0));
    for (int q = -1; q <= 1; q++) {
      for (int r = -1; r <= 1; r++) {
        for (int s = -1; s <= 1; s++) {
          if (q == 0 && r == 0 && s == 0) {
            continue;
          }
          if (q + r + s != 0) {
            continue;
          }
          int finalQ = q;
          int finalR = r;
          int finalS = s;
          Assert.assertThrows(IllegalStateException.class,
                  () -> game.placeDisc(finalQ, finalR, finalS));
        }
      }
    }
  }

  @Test
  public void cannotPlaceGameDiscOffBoard() {
    game.startGame(5);
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(6, 6, 6));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(5, 5, 6));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(0, 0, 6));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(-6, 5, 5));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(-6, 0, 0));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(0, -6, 0));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(0, 0, -6));
  }

  @Test
  public void nothingFunctionsBeforeGameStart() {
    Assert.assertThrows(IllegalStateException.class, () -> game.getColorAt(0, 0, 0));
    Assert.assertThrows(IllegalStateException.class, () -> game.placeDisc(0, 0, 0));
    Assert.assertThrows(IllegalStateException.class, () -> game.skipCurrentTurn());
    Assert.assertThrows(IllegalStateException.class, () -> game.gameOver());
    Assert.assertThrows(IllegalStateException.class, () -> game.getBoardSize());
    Assert.assertThrows(IllegalStateException.class, () -> game.getCurrentTurn());
    Assert.assertThrows(IllegalStateException.class, () -> game.getBoardRadius());
  }

  @Test
  public void cannotPlaceIllegalDiscs() {
    game.startGame(5);
    Assert.assertThrows(IllegalStateException.class, () -> game.placeDisc(0, 0, 0));
    Assert.assertSame(DiscColor.GREY, game.getColorAt(0, 0, 0));
    Assert.assertThrows(IllegalStateException.class, () -> game.placeDisc(4, -2, -2));
    Assert.assertSame(DiscColor.GREY, game.getColorAt(4, -2, -2));
    Assert.assertThrows(IllegalStateException.class, () -> game.placeDisc(-2, 0, 2));
    Assert.assertThrows(IllegalStateException.class, () -> game.placeDisc(2, 0, -2));
  }

  @Test
  public void cannotStartGameWhenGameIsStarted() {
    game.startGame(5);
    Assert.assertThrows(IllegalStateException.class, () -> game.startGame(4));
  }

  @Test
  public void placeTileInvalidDoesNotSwapWrongTile() {
    game.startGame(2);
    Appendable out = new StringBuffer();
    TextualView tv = new ReversiTextualView(game, out);
    game.placeDisc(-2, 1, 1);
    Assert.assertEquals(DiscColor.WHITE, game.getColorAt(0, 1, -1));
    Assert.assertEquals(DiscColor.BLACK, game.getColorAt(-1, 0, 1));
    Assert.assertFalse(game.gameOver());
  }

  @Test
  public void placeTileMultiplePlanesChanged() {
    game.startGame(3);
    game.skipCurrentTurn();
    game.placeDisc(2,-1,-1);
    game.skipCurrentTurn();
    game.placeDisc(-1,2,-1);
    Assert.assertEquals(game.getColorAt(-1,1,0), DiscColor.WHITE);
    Assert.assertEquals(game.getColorAt(2,-1,-1),DiscColor.WHITE);
  }

  @Test
  public void placeValidDiscWorks() {
    game.startGame(2);
    game.placeDisc(-2, 1, 1);
    game.skipCurrentTurn();
  }

  @Test
  public void testGameOverAfterGameWonBlack() {
    game.startGame(3);
    TextualView tv = new ReversiTextualView(game);
    game.placeDisc(-2, 1, 1);
    game.skipCurrentTurn();
    game.placeDisc(2, -1, -1);
    Assert.assertEquals(DiscColor.BLACK, game.getColorAt(1, -1, 0));
    Assert.assertEquals(DiscColor.WHITE, game.getColorAt(0, 1, -1));
    Assert.assertFalse(game.gameOver());
    game.skipCurrentTurn();
    game.placeDisc(1,1,-2);
    Assert.assertTrue(game.gameOver());
  }

  @Test
  public void testGameOverAfterGameWonWhite() {
    game.startGame(2);
    Assert.assertFalse(game.gameOver());
    TextualView tv = new ReversiTextualView(game);
    game.skipCurrentTurn();
    game.placeDisc(1,-2,1);
    Assert.assertFalse(game.gameOver());
    game.skipCurrentTurn();
    Assert.assertFalse(game.gameOver());
    game.placeDisc(-1,2,-1);
    game.skipCurrentTurn();
    game.placeDisc(2,-1,-1);
    Assert.assertTrue(game.gameOver());
  }

  @Test
  public void testGameOverWhenAllSpotsFilled() {
    game = new MutableReversi(true,1);
    Assert.assertTrue(game.gameOver());
  }
}
