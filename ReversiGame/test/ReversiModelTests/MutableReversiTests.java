package ReversiModelTests;

import model.GameCell;
import model.MutableReversi;
import model.MutableReversiModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
          if (q >= -1 && q <= 1 && r >= -1 && r <= 1 && s >= -1 && s <= 1) {
            Assert.assertNotNull(game.getDiscAt(q, r, s).getColor());
          } else {
            Assert.assertNull(game.getDiscAt(q, r, s).getColor());
          }
        }
      }
    }
    Assert.assertNull(game.getDiscAt(0, 0, 0).getColor());
  }

  @Test
  public void cannotPlaceGameDiscOnGameDisc() {
    game.startGame(5);
    game.placeDisc(0, 0, 0);
    Assert.assertThrows(IllegalStateException.class, () -> game.placeDisc(0, 0, 0));
    for (int q = -1; q <= 1; q++) {
      for (int r = -1; r <= 1; r++) {
        for (int s = -1; s <= 1; s++) {
          if (q == 0 && r == 0 && s == 0) {
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