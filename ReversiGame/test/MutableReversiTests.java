import model.GameDisc.DiscColor;
import model.MutableReversi;
import model.MutableReversiModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(0, -6, 0));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(0, 0, -6));

    //q+r+s must equal 0 for it to be on the board
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(0, 0, 4));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(1, 0, 0));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(-1, 0, 0));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(-1, 1, 1));
  }

  // Assumes this as start game board X as black O as white
  //       _ _ _ _ _ _
  //      _ _ _ _ _ _ _
  //     _ _ _ _ _ _ _ _
  //    _ _ _ _ _ _ _ _ _
  //   _ _ _ _ X O _ _ _ _
  //  _ _ _ _ O _ X _ _ _ _
  //   _ _ _ _ X O _ _ _ _
  //    _ _ _ _ _ _ _ _ _
  //     _ _ _ _ _ _ _ _
  //      _ _ _ _ _ _ _
  //       _ _ _ _ _ _
  @Test
  public void reversiFlipsMultiplePieces() {
    game.startGame(5);
    game.placeDisc(1, -2, 1);
    Assert.assertEquals(DiscColor.BLACK, game.getDiscAt(1, -2, 1).getColor());
    Assert.assertEquals(DiscColor.BLACK, game.getDiscAt(1, -1, 0).getColor());
    Assert.assertEquals(DiscColor.BLACK, game.getDiscAt(1, 0, -1).getColor());
    game.placeDisc(2, -3, 1);
    Assert.assertEquals(DiscColor.WHITE, game.getDiscAt(2, -3, 1).getColor());
    Assert.assertEquals(DiscColor.WHITE, game.getDiscAt(1, -2, 1).getColor());
    Assert.assertEquals(DiscColor.WHITE, game.getDiscAt(0, -1, 1).getColor());
    Assert.assertEquals(DiscColor.WHITE, game.getDiscAt(-1, 0, 1).getColor());
  }

  @Test
  public void testInvalidMovesThrowExceptions() {
    game.startGame(5);
    //places piece on board but not near any pieces.
    Assert.assertThrows(IllegalStateException.class, () -> game.placeDisc(4, -4, 0));
    //places piece near black piece but piece to move is black.
    Assert.assertThrows(IllegalStateException.class, () -> game.placeDisc(2, 0, -2));
    //places piece near white piece, but it is not correctly connected to any black piece.
    Assert.assertThrows(IllegalStateException.class, () -> game.placeDisc(2, -2, 0));
    //places piece near white piece, but it is not correctly connected to any black piece.
    Assert.assertThrows(IllegalStateException.class, () -> game.placeDisc(0, 0, 0));
  }
}
