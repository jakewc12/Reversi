import model.GameDisc.DiscColor;
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

  private ReadOnlyReversiModel game = new MutableReversi();

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

  //Tests game start methods

  /**
   * Check that everything outside the center tiles ring is a grey tile. The ring around the origin
   * tile should be not grey. The origin tile itself will be grey.
   */
  @Test
  public void getDiscAtFunctionsCorrectlyOnGameStart() {
    game.startGame(5);
    int boardRadius = 5;
    for (int q = -boardRadius; q < boardRadius; q++) {
      for (int r = -boardRadius; r < boardRadius; r++) {
        for (int s = -boardRadius; s < boardRadius; s++) {
          if (q + r + s != 0) {
            continue;
          }
          try {
            if (q == 0 && r == 0 && s == 0) {
              Assert.assertNotEquals(DiscColor.GREY, game.getDiscAt(q, r, s).getColor());
            } else if (q >= -1 && q <= 1 && r >= -1 && r <= 1 && s >= -1 && s <= 1) {
              Assert.assertNotEquals(DiscColor.GREY, game.getDiscAt(q, r, s).getColor());
            } else {
              Assert.assertEquals(DiscColor.GREY, game.getDiscAt(q, r, s).getColor());
            }
          } catch (Exception e) {
            throw new AssertionError(
                "An error occurred when getting a disc at (Q,R,S): (" + q + ", " + r + ", " + s
                    + ")\nException was: " + e);
          }
        }
      }
    }
    try {
      Assert.assertEquals(DiscColor.GREY, game.getDiscAt(0, 0, 0).getColor());
    } catch (Exception e) {
      throw new AssertionError(
          "An error occurred when getting a disc at q: " + 0 + " r: " + 0 + "s: " + 0
              + "\nException was: " + e);
    }
  }

  @Test
  public void nothingInReadFunctionsBeforeGameStart() {
    Assert.assertThrows(IllegalStateException.class, () -> game.getDiscAt(0, 0, 0));
    Assert.assertThrows(IllegalStateException.class, () -> game.gameOver());
    Assert.assertThrows(IllegalStateException.class, () -> game.getBoardSize());
    Assert.assertThrows(IllegalStateException.class, () -> game.getCurrentTurn());
    Assert.assertThrows(IllegalStateException.class, () -> game.getBoardRadius());
  }

  @Test
  public void cannotStartGameWhenGameIsStarted() {
    game.startGame(5);
    Assert.assertThrows(IllegalStateException.class, () -> game.startGame(4));
  }


}
