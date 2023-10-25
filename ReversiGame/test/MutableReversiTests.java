import model.GameDisc.DiscColor;
import model.MutableReversi;
import model.MutableReversiModel;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

public class MutableReversiTests {

  private MutableReversiModel game = new MutableReversi();

  @Before
  public void init() {
    game = new MutableReversi();
  }

  /**
   * Ensure the size of the board is not less than 9.
   */
  @Test
  public void startGameThrowsOnInvalidSize() {
    Assert.assertThrows(IllegalArgumentException.class, () -> game.startGame(-1));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.startGame(0));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.startGame(3));
  }


  @Test
  public void getRadiusAndGetBoardSizeWorks() {
    game.startGame(5);
    Assert.assertEquals(11, game.getBoardSize());
    Assert.assertEquals(5, game.getBoardRadius());
  }

  @Test
  public void getDiscAtFunctionsCorrectlyOnGameStart() {
    game.startGame(5);
    for (int q = 0; q < 5; q++) {
      for (int r = 0; r < 5; r++) {
        for (int s = 0; s < 5; s++) {
          Assert.assertEquals(DiscColor.GREY, game.getDiscAt(q, r, s).getColor());
        }
      }
    }
  }

  @Test
  public void nothingFunctionsBeforeGameStart() {
    Assert.assertThrows(IllegalStateException.class, () -> game.getDiscAt(0, 0, 0));
    Assert.assertThrows(IllegalStateException.class, () -> game.placeDisc(0, 0, 0));
    Assert.assertThrows(IllegalStateException.class, () -> game.skipCurrentTurn());
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
