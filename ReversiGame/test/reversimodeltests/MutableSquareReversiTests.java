package reversimodeltests;

import model.DiscColor;
import model.MutableReversiModel;
import model.hexreversi.HexCoordinate;
import model.hexreversi.MutableHexReversi;
import model.squarereversi.MutableSquareReversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import model.squarereversi.SquareCoordinate;
import textualview.SquareReversiTextualView;
import textualview.TextualView;

public class MutableSquareReversiTests {
  private MutableReversiModel game;
  private TextualView tv;
  private StringBuilder gameLog;

  /**
   * initializes game which will be used for testing.
   */
  @Before
  public void init() {
    game = new MutableSquareReversi(8);
  }

  @Test
  public void show() {
    tv = new SquareReversiTextualView(game);
    game.setUpGame(game.getBoard());
    game.startGame();
    System.out.println(tv);
  }

  /**
   * Ensure the size of the board is not less than 1.
   */
  @Test
  public void startGameThrowsOnInvalidSize() {
    Assert.assertThrows(IllegalArgumentException.class, () -> new MutableSquareReversi(-1));
    Assert.assertThrows(IllegalArgumentException.class, () -> new MutableSquareReversi(0));
  }

  /**
   * tests that the MutableHexReversi's implementation of getBoardSize and getBoardRadius work.
   */
  @Test
  public void getRadiusAndGetBoardSizeWorks() {
    game.setUpGame(game.getBoard());
    Assert.assertEquals(8, game.getBoardRadius());
    Assert.assertEquals(8, game.getBoardSize());
  }

  /**
   * tests that placing discs at invalid coordinates throws IllegalArgumentException.
   */
  @Test
  public void cannotPlaceGameDiscOffBoard() {
    game.setUpGame(game.getBoard());
    Assert.assertThrows(NoSuchElementException.class,
            () -> game.placeDisc(new SquareCoordinate(-1, 4)));
    Assert.assertThrows(IllegalArgumentException.class,
            () -> game.placeDisc(new SquareCoordinate(0, 9)));
    Assert.assertThrows(NoSuchElementException.class,
            () -> game.placeDisc(new SquareCoordinate(-1, -1)));
    Assert.assertThrows(NoSuchElementException.class,
            () -> game.placeDisc(new SquareCoordinate(8, 8)));
    Assert.assertThrows(NoSuchElementException.class,
            () -> game.placeDisc(new SquareCoordinate(8, 5)));
    Assert.assertThrows(NoSuchElementException.class,
            () -> game.placeDisc(new SquareCoordinate(-1, 5)));
  }

  /**
   * tests that calling startGame multiple times throws an IllegalStateException.
   */
  @Test
  public void cannotStartGameWhenGameIsStarted() {
    game.setUpGame(game.getBoard());
    Assert.assertThrows(IllegalStateException.class, () -> game.setUpGame(game.getBoard()));
  }


  /**
   * tests that if a placeTile move is illegal, no tiles are swapped.
   */

  @Test
  public void cannotPlaceIllegalDiscs() {
    game = new MutableSquareReversi(4);
    game.setUpGame(game.getBoard());
    Assert.assertThrows(IllegalStateException.class
            , () -> game.placeDisc(new SquareCoordinate(3, 3)));
    Assert.assertEquals(DiscColor.GREY, game.getColorAt(new SquareCoordinate(3,3)));
    Assert.assertFalse(game.gameOver());
  }

  @Test
  public void flipTilesActuallyFlips() {
    game = new MutableSquareReversi(4);
    game.setUpGame(game.getBoard());
    game.placeDisc(new SquareCoordinate(3,1));
    Assert.assertEquals(DiscColor.BLACK, game.getColorAt(new SquareCoordinate(3,1)));
    Assert.assertEquals(DiscColor.BLACK, game.getColorAt(new SquareCoordinate(2,1)));
    Assert.assertEquals(DiscColor.BLACK, game.getColorAt(new SquareCoordinate(1,1)));
  }

  @Test
  public void placeValidDoesNotFlipSurroundingTiles() {
    game = new MutableSquareReversi(4);
    game.setUpGame(game.getBoard());
    game.placeDisc(new SquareCoordinate(3,1));
    Assert.assertEquals(DiscColor.GREY, game.getColorAt(new SquareCoordinate(3,0)));
    Assert.assertEquals(DiscColor.GREY, game.getColorAt(new SquareCoordinate(3,2)));
    Assert.assertEquals(DiscColor.GREY, game.getColorAt(new SquareCoordinate(2,0)));
  }
}
