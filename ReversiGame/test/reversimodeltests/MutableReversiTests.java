package reversimodeltests;

import java.lang.reflect.Constructor;
import model.GameDisc.DiscColor;
import model.MutableReversi;
import model.MutableReversiModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import view.ReversiTextualView;
import view.TextualView;

/**
 * Test meant for the MutableReversi class. These test should test every method and thrown exception
 * inside the class.
 */
public class MutableReversiTests {

  private MutableReversiModel game;

  /**
   * initializes game which will be used for testing.
   */
  @Before
  public void init() {
    game = new MutableReversi(5);
  }

  /**
   * Ensure the size of the board is not less than 1.
   */
  @Test
  public void startGameThrowsOnInvalidSize() {
    Assert.assertThrows(IllegalArgumentException.class, () -> new MutableReversi(-1));
    Assert.assertThrows(IllegalArgumentException.class, () -> new MutableReversi(0));
  }

  /**
   * tests that the MutableReversi's implementation of getBoardSize and getBoardRadius work.
   */
  @Test
  public void getRadiusAndGetBoardSizeWorks() {
    game.startGame();
    Assert.assertEquals(11, game.getBoardSize());
    Assert.assertEquals(5, game.getBoardRadius());
  }

  /**
   * tests that a new GameDisc cannot be placed on an already existing GameDisc location.
   */
  @Test
  public void cannotPlaceGameDiscOnGameDisc() {
    game.startGame();
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

  /**
   * tests that placing discs at invalid coordinates throws IllegalArgumentException.
   */
  @Test
  public void cannotPlaceGameDiscOffBoard() {
    game.startGame();
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(6, 6, 6));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(5, 5, 6));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(0, 0, 6));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(-6, 5, 5));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(-6, 0, 0));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(0, -6, 0));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(0, 0, -6));
  }

  /**
   * tests that trying to do anything before throwing startGame throws IllegalStateException.
   */
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

  /**
   * tests that placing any disc no colored WHITE or GREY throws an IllegalStateException.
   */
  @Test
  public void cannotPlaceIllegalDiscs() {
    game.startGame();
    Assert.assertThrows(IllegalStateException.class, () -> game.placeDisc(0, 0, 0));
    Assert.assertSame(DiscColor.GREY, game.getColorAt(0, 0, 0));
    Assert.assertThrows(IllegalStateException.class, () -> game.placeDisc(4, -2, -2));
    Assert.assertSame(DiscColor.GREY, game.getColorAt(4, -2, -2));
    Assert.assertThrows(IllegalStateException.class, () -> game.placeDisc(-2, 0, 2));
    Assert.assertThrows(IllegalStateException.class, () -> game.placeDisc(2, 0, -2));
  }

  /**
   * tests that calling startGame multiple times throws an IllegalStateException.
   */
  @Test
  public void cannotStartGameWhenGameIsStarted() {
    game.startGame();
    Assert.assertThrows(IllegalStateException.class, () -> game.startGame());
  }

  /**
   * tests that if a placeTile move is illegal, no tiles are swapped.
   */
  @Test
  public void placeTileInvalidDoesNotSwapWrongTile() {
    game = new MutableReversi(2);
    game.startGame();
    game.placeDisc(-2, 1, 1);
    Assert.assertEquals(DiscColor.WHITE, game.getColorAt(0, 1, -1));
    Assert.assertEquals(DiscColor.BLACK, game.getColorAt(-1, 0, 1));
    Assert.assertFalse(game.gameOver());
  }

  /**
   * tests that if a placeTile flips tiles in several different directions/planes they all flip.
   */
  @Test
  public void placeTileMultiplePlanesChanged() {
    game = new MutableReversi(3);
    game.startGame();
    game.skipCurrentTurn();
    game.placeDisc(2, -1, -1);
    game.skipCurrentTurn();
    game.placeDisc(-1, 2, -1);
    Assert.assertEquals(game.getColorAt(-1, 1, 0), DiscColor.WHITE);
    Assert.assertEquals(game.getColorAt(2, -1, -1), DiscColor.WHITE);
  }

  /**
   * tests that placeDisc works correctly.
   */
  @Test
  public void placeValidDiscWorks() {
    game = new MutableReversi(2);
    game.startGame();
    game.placeDisc(-2, 1, 1);
    game.skipCurrentTurn();
    Assert.assertEquals(game.getColorAt(-2, 1, 1), DiscColor.BLACK);
  }

  @Test
  public void placeValidDoesNotFlipSurroundingTiles() {
    game = new MutableReversi(2);
    game.startGame();
    game.placeDisc(2, -1, -1);
    Assert.assertEquals(game.getColorAt(2, -1, -1), DiscColor.BLACK);
    Assert.assertNotEquals(game.getColorAt(2, -2, 0), DiscColor.BLACK);
    Assert.assertNotEquals(game.getColorAt(1, -2, 1), DiscColor.BLACK);
    Assert.assertNotEquals(game.getColorAt(2, 0, -2), DiscColor.BLACK);
  }

  /**
   * tests that gameOver returns true when all white discs are gone.
   */
  @Test
  public void testGameOverAfterGameWonBlack() {
    game = new MutableReversi(3);
    game.startGame();
    game.placeDisc(-2, 1, 1);
    game.skipCurrentTurn();
    game.placeDisc(2, -1, -1);
    Assert.assertEquals(DiscColor.BLACK, game.getColorAt(1, -1, 0));
    Assert.assertEquals(DiscColor.WHITE, game.getColorAt(0, 1, -1));
    Assert.assertFalse(game.gameOver());
    game.skipCurrentTurn();
    game.placeDisc(1, 1, -2);
    Assert.assertTrue(game.gameOver());
  }

  /**
   * tests that gameOver returns true when all black discs are gone.
   */
  @Test
  public void testGameOverAfterGameWonWhite() {
    game = new MutableReversi(2);
    game.startGame();
    Assert.assertFalse(game.gameOver());
    game.skipCurrentTurn();
    game.placeDisc(1, -2, 1);
    Assert.assertFalse(game.gameOver());
    game.skipCurrentTurn();
    Assert.assertFalse(game.gameOver());
    game.placeDisc(-1, 2, -1);
    game.skipCurrentTurn();
    game.placeDisc(2, -1, -1);
    Assert.assertTrue(game.gameOver());
  }

  /**
   * tests that gameOver returns true when all gameDiscs colors are either BLACK or WHITE.
   */
  @Test
  public void testGameOverWhenAllSpotsFilled() {
    try {
      Constructor<MutableReversi> pcc = MutableReversi.class.getDeclaredConstructor(int.class,
          boolean.class);
      pcc.setAccessible(true);
      game = pcc.newInstance(1, true);
    } catch (Exception e) {
      throw new RuntimeException("an error occurred when trying to access the private constructor");
    }
    Assert.assertTrue(game.gameOver());
  }

  @Test
  public void testPlaceDiscThrowsErrorsCorrectly() {
    game = new MutableReversi(3);
    game.startGame();
    TextualView tv = new ReversiTextualView(game);
    game.placeDisc(2, -1, -1);
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(3, -2, 1));
  }
}
