package reversimodeltests;

import model.GameDisc.DiscColor;
import model.HexagonCell;
import model.MutableReversi;
import model.ReadOnlyReversiModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test meant for the ReadOnlyReversiModel class. These test should test read methods and throw
 * appropriate exceptions inside the class.
 */
public class ReversiReadTests {

  private ReadOnlyReversiModel game;

  /**
   * initalizes game which will be used for testing.
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
   * tests that getRadius and getBoardSize for ReadOnlyReversiModel works.
   */
  @Test
  public void getRadiusAndGetBoardSizeWorks() {
    game.startGame(game.getBoard());
    Assert.assertEquals(11, game.getBoardSize());
    Assert.assertEquals(5, game.getBoardRadius());
  }


  /**
   * tests that gameOver true for when only radius of one.
   */
  @Test
  public void gameOverTrueOnOneByOneBoard() {
    game = new MutableReversi(1);
    game.startGame(game.getBoard());
    Assert.assertTrue(game.gameOver());
  }

  /**
   * tests that blacks turn at start.
   */
  @Test
  public void getTurnBlackOnStart() {
    game.startGame(game.getBoard());
    Assert.assertEquals(DiscColor.BLACK, game.getCurrentTurn());
  }

  //Tests game start methods

  /**
   * Check that everything outside the center tiles ring is a grey tile. The ring around the origin
   * tile should be not grey. The origin tile itself will be grey.
   */
  @Test
  public void getDiscAtFunctionsCorrectlyOnGameStart() {
    game.startGame(game.getBoard());
    int boardRadius = 5;
    for (int q = -boardRadius; q < boardRadius; q++) {
      for (int r = -boardRadius; r < boardRadius; r++) {
        for (int s = -boardRadius; s < boardRadius; s++) {
          //valid tile should coordinates should equal zero and be within bounds of abs(radius)
          if (q + r + s != 0) {
            continue;
          }
          try {
            if (q == 0 && r == 0 && s == 0) {
              Assert.assertEquals(DiscColor.GREY, game.getColorAt(q, r, s));
            } else if (q >= -1 && q <= 1 && r >= -1 && r <= 1 && s >= -1 && s <= 1) {
              Assert.assertNotEquals(DiscColor.GREY, game.getColorAt(q, r, s));
            } else {
              Assert.assertEquals(DiscColor.GREY, game.getColorAt(q, r, s));
            }
          } catch (Exception e) {
            throw new AssertionError(
                    "An error occurred when getting a disc at (Q,R,S): ("
                            + q + ", " + r + ", " + s
                            + ")\nException was: " + e);
          }
        }
      }
    }
    try {
      Assert.assertEquals(DiscColor.GREY, game.getColorAt(0, 0, 0));
    } catch (Exception e) {
      throw new AssertionError(
              "An error occurred when getting a disc at q: " + 0 + " r: "
                      + 0 + "s: " + 0
                      + "\nException was: " + e);
    }
  }

  /**
   * tests that trying to do anything before throwing startGame throws IllegalStateException.
   */
  @Test
  public void nothingInReadFunctionsBeforeGameStart() {
    Assert.assertThrows(IllegalStateException.class, () -> game.getColorAt(0, 0, 0));
    Assert.assertThrows(IllegalStateException.class, () -> game.gameOver());
    Assert.assertThrows(IllegalStateException.class, () -> game.getBoardSize());
    Assert.assertThrows(IllegalStateException.class, () -> game.getCurrentTurn());
    Assert.assertThrows(IllegalStateException.class, () -> game.getBoardRadius());
  }

  /**
   * tests that cannot call startGame multiple times.
   */
  @Test
  public void cannotStartGameWhenGameIsStarted() {
    game.startGame(game.getBoard());
    Assert.assertThrows(IllegalStateException.class, () -> game.startGame(game.getBoard()));
  }

  @Test
  public void testGetBoardWorks() {
    game = new MutableReversi(1);
    Assert.assertEquals(game.getBoard().size(), 7);
  }

}
