import model.MutableReversi;
import model.MutableReversiModel;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

/**
 * Test meant for the MutableReversi class. These test should test mutation or edit method and throw
 * appropriate exceptions inside the class.
 */
public class MutableReversiTests {

  private MutableReversiModel game = new MutableReversi();

  @Before
  public void init() {
    game = new MutableReversi();
  }

  @Test
  public void nothingInMutableFunctionsBeforeGameStart() {
    Assert.assertThrows(IllegalStateException.class, () -> game.placeDisc(0, 0, 0));
    Assert.assertThrows(IllegalStateException.class, () -> game.skipCurrentTurn());
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


}
