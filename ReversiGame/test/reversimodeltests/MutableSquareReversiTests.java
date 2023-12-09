package reversimodeltests;

import model.DiscColor;
import model.MutableReversiModel;
import model.squarereversi.MutableSquareReversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import model.squarereversi.SquareCoordinate;
import textualview.TextualView;

/**
 * Tests potential issues with square reversi.
 */
public class MutableSquareReversiTests {
  private MutableReversiModel game;

  /**
   * initializes game which will be used for testing.
   */
  @Before
  public void init() {
    game = new MutableSquareReversi(8);
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
    Assert.assertThrows(NoSuchElementException.class, () -> game.placeDisc(
            new SquareCoordinate(-1, 4)));
    Assert.assertThrows(IllegalArgumentException.class, () -> game.placeDisc(
            new SquareCoordinate(0, 9)));
    Assert.assertThrows(NoSuchElementException.class, () -> game.placeDisc(
            new SquareCoordinate(-1, -1)));
    Assert.assertThrows(NoSuchElementException.class, () -> game.placeDisc(
            new SquareCoordinate(8, 8)));
    Assert.assertThrows(NoSuchElementException.class, () -> game.placeDisc(
            new SquareCoordinate(8, 5)));
    Assert.assertThrows(NoSuchElementException.class, () -> game.placeDisc(
            new SquareCoordinate(-1, 5)));
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
    Assert.assertEquals(DiscColor.GREY, game.getColorAt(new SquareCoordinate(3, 3)));
    Assert.assertFalse(game.gameOver());
  }

  @Test
  public void flipTilesActuallyFlips() {
    game = new MutableSquareReversi(4);
    game.setUpGame(game.getBoard());
    game.placeDisc(new SquareCoordinate(3, 1));
    Assert.assertEquals(DiscColor.BLACK, game.getColorAt(new SquareCoordinate(3, 1)));
    Assert.assertEquals(DiscColor.BLACK, game.getColorAt(new SquareCoordinate(2, 1)));
    Assert.assertEquals(DiscColor.BLACK, game.getColorAt(new SquareCoordinate(1, 1)));
  }

  @Test
  public void placeValidDoesNotFlipSurroundingTiles() {
    game = new MutableSquareReversi(4);
    game.setUpGame(game.getBoard());
    game.placeDisc(new SquareCoordinate(3, 1));
    Assert.assertEquals(DiscColor.GREY, game.getColorAt(new SquareCoordinate(3, 0)));
    Assert.assertEquals(DiscColor.GREY, game.getColorAt(new SquareCoordinate(3, 2)));
    Assert.assertEquals(DiscColor.GREY, game.getColorAt(new SquareCoordinate(2, 0)));
  }

  @Test
  public void testGameOverBlack() {
    game = new MutableSquareReversi(4);
    game.setUpGame(game.getBoard());
    game.placeDisc(new SquareCoordinate(3, 1));
    Assert.assertFalse(game.gameOver());
    game.skipCurrentTurn();
    Assert.assertFalse(game.gameOver());
    game.placeDisc(new SquareCoordinate(1, 3));
    Assert.assertTrue(game.gameOver());
  }

  @Test
  public void gameOverWhenTilesFiled() {
    game = new MutableSquareReversi(2);
    game.setUpGame(game.getBoard());
    Assert.assertTrue(game.gameOver());
  }

  @Test
  public void checkGivenPlayerHasMovesWorks() {
    game = new MutableSquareReversi(2);
    game.setUpGame(game.getBoard());
    Assert.assertFalse(game.checkCurrentPlayerHasLegalMovesLeft());
  }

  @Test
  public void checkGivenPlayerHasMovesGameOngoing() {
    game = new MutableSquareReversi(4);
    game.setUpGame(game.getBoard());
    game.placeDisc(new SquareCoordinate(3, 1));
    Assert.assertTrue(game.checkCurrentPlayerHasLegalMovesLeft());
    game.skipCurrentTurn();
    Assert.assertTrue(game.checkCurrentPlayerHasLegalMovesLeft());
    game.placeDisc(new SquareCoordinate(1, 3));
    Assert.assertFalse(game.checkCurrentPlayerHasLegalMovesLeft());
  }

  @Test
  public void testGetScoreDuringGame() {
    game = new MutableSquareReversi(4);
    game.setUpGame(game.getBoard());
    Assert.assertEquals(2, game.checkScoreOfPlayer(DiscColor.BLACK));
    Assert.assertEquals(2, game.checkScoreOfPlayer(DiscColor.WHITE));
    game.placeDisc(new SquareCoordinate(3, 1));
    Assert.assertEquals(4, game.checkScoreOfPlayer(DiscColor.BLACK));
    Assert.assertEquals(1, game.checkScoreOfPlayer(DiscColor.WHITE));
    game.placeDisc(new SquareCoordinate(1, 0));
    Assert.assertEquals(3, game.checkScoreOfPlayer(DiscColor.BLACK));
    Assert.assertEquals(3, game.checkScoreOfPlayer(DiscColor.WHITE));
    game.skipCurrentTurn();
    game.placeDisc(new SquareCoordinate(3, 2));
    game.skipCurrentTurn();
    game.placeDisc(new SquareCoordinate(3, 0));
    Assert.assertEquals(0, game.checkScoreOfPlayer(DiscColor.BLACK));
    Assert.assertEquals(8, game.checkScoreOfPlayer(DiscColor.WHITE));
  }
}
