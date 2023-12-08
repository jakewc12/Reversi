package reversimodeltests;

import java.util.ArrayList;
import java.util.List;
import model.hexreversi.MutableHexReversi;
import model.hexreversi.HexCoordinate;
import model.DiscColor;
import model.GameCell;
import model.hexreversi.HexCell;
import model.MutableReversiModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import textualview.ReversiTextualView;
import textualview.TextualView;

/**
 * Test meant for the MutableHexReversi class. These test should test every method and thrown exception
 * inside the class.
 */
public class MutableHexReversiTests {

  private MutableReversiModel game;

  /**
   * initializes game which will be used for testing.
   */
  @Before
  public void init() {
    game = new MutableHexReversi(5);
  }

  /**
   * Ensure the size of the board is not less than 1.
   */
  @Test
  public void startGameThrowsOnInvalidSize() {
    Assert.assertThrows(IllegalArgumentException.class, () -> new MutableHexReversi(-1));
    Assert.assertThrows(IllegalArgumentException.class, () -> new MutableHexReversi(0));
  }

  /**
   * tests that the MutableHexReversi's implementation of getBoardSize and getBoardRadius work.
   */
  @Test
  public void getRadiusAndGetBoardSizeWorks() {
    game.setUpGame(game.getBoard());
    Assert.assertEquals(11, game.getBoardSize());
    Assert.assertEquals(5, game.getBoardRadius());
  }

  /**
   * tests that a new GameDisc cannot be placed on an already existing GameDisc location.
   */
  @Test
  public void cannotPlaceGameDiscOnGameDisc() {
    game.setUpGame(game.getBoard());
    Assert.assertSame(DiscColor.GREY, game.getColorAt(new HexCoordinate(0, 0, 0)));
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
              () -> game.placeDisc(new HexCoordinate(finalQ, finalR, finalS)));
        }
      }
    }
  }

  /**
   * tests that placing discs at invalid coordinates throws IllegalArgumentException.
   */
  @Test
  public void cannotPlaceGameDiscOffBoard() {
    game.setUpGame(game.getBoard());
    Assert.assertThrows(IllegalArgumentException.class,
        () -> game.placeDisc(new HexCoordinate(6, 6, 6)));
    Assert.assertThrows(IllegalArgumentException.class,
        () -> game.placeDisc(new HexCoordinate(5, 5, 6)));
    Assert.assertThrows(IllegalArgumentException.class,
        () -> game.placeDisc(new HexCoordinate(0, 0, 6)));
    Assert.assertThrows(IllegalArgumentException.class,
        () -> game.placeDisc(new HexCoordinate(-6, 5, 5)));
    Assert.assertThrows(IllegalArgumentException.class,
        () -> game.placeDisc(new HexCoordinate(-6, 0, 0)));
    Assert.assertThrows(IllegalArgumentException.class,
        () -> game.placeDisc(new HexCoordinate(0, -6, 0)));
    Assert.assertThrows(IllegalArgumentException.class,
        () -> game.placeDisc(new HexCoordinate(0, 0, -6)));
  }
  

  /**
   * tests that placing any disc no colored WHITE or GREY throws an IllegalStateException.
   */
  @Test
  public void cannotPlaceIllegalDiscs() {
    game.setUpGame(game.getBoard());
    Assert.assertThrows(IllegalStateException.class, () -> game.placeDisc(new HexCoordinate(0, 0, 0)));
    Assert.assertSame(DiscColor.GREY, game.getColorAt(new HexCoordinate(0, 0, 0)));
    Assert.assertThrows(IllegalStateException.class,
        () -> game.placeDisc(new HexCoordinate(4, -2, -2)));
    Assert.assertSame(DiscColor.GREY, game.getColorAt(new HexCoordinate(4, -2, -2)));
    Assert.assertThrows(IllegalStateException.class,
        () -> game.placeDisc(new HexCoordinate(-2, 0, 2)));
    Assert.assertThrows(IllegalStateException.class,
        () -> game.placeDisc(new HexCoordinate(2, 0, -2)));
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
  public void placeTileInvalidDoesNotSwapWrongTile() {
    game = new MutableHexReversi(2);
    game.setUpGame(game.getBoard());
    game.placeDisc(new HexCoordinate(-2, 1, 1));
    Assert.assertEquals(DiscColor.WHITE, game.getColorAt(new HexCoordinate(0, 1, -1)));
    Assert.assertEquals(DiscColor.BLACK, game.getColorAt(new HexCoordinate(-1, 0, 1)));
    Assert.assertFalse(game.gameOver());
  }

  /**
   * tests that if a placeTile flips tiles in several different directions/planes they all flip.
   */
  @Test
  public void placeTileMultiplePlanesChanged() {
    game = new MutableHexReversi(3);
    game.setUpGame(game.getBoard());
    game.placeDisc(new HexCoordinate(2, -1, -1));
    game.placeDisc(new HexCoordinate(3, -2, -1));
    game.placeDisc(new HexCoordinate(3, -1, -2));
    game.placeDisc(new HexCoordinate(1, -2, 1));
    game.placeDisc(new HexCoordinate(-1, -1, 2));
    Assert.assertEquals(game.getColorAt(new HexCoordinate(0, -1, 1)), DiscColor.BLACK);
    Assert.assertEquals(game.getColorAt(new HexCoordinate(-1, 0, 1)), DiscColor.BLACK);
  }

  /**
   * tests that placeDisc works correctly.
   */
  @Test
  public void placeValidDiscWorks() {
    game = new MutableHexReversi(2);
    game.setUpGame(game.getBoard());
    game.placeDisc(new HexCoordinate(-2, 1, 1));
    game.skipCurrentTurn();
    Assert.assertEquals(game.getColorAt(new HexCoordinate(-2, 1, 1)), DiscColor.BLACK);
  }

  @Test
  public void placeValidDoesNotFlipSurroundingTiles() {
    game = new MutableHexReversi(2);
    game.setUpGame(game.getBoard());
    game.placeDisc(new HexCoordinate(2, -1, -1));
    Assert.assertEquals(game.getColorAt(new HexCoordinate(2, -1, -1)), DiscColor.BLACK);
    Assert.assertNotEquals(game.getColorAt(new HexCoordinate(2, -2, 0)), DiscColor.BLACK);
    Assert.assertNotEquals(game.getColorAt(new HexCoordinate(1, -2, 1)), DiscColor.BLACK);
    Assert.assertNotEquals(game.getColorAt(new HexCoordinate(2, 0, -2)), DiscColor.BLACK);
  }

  /**
   * tests that gameOver returns true when all white discs are gone.
   */
  @Test
  public void testGameOverAfterGameWonBlack() {
    game = new MutableHexReversi(3);
    game.setUpGame(game.getBoard());
    game.placeDisc(new HexCoordinate(-2, 1, 1));
    game.skipCurrentTurn();
    game.placeDisc(new HexCoordinate(2, -1, -1));
    Assert.assertEquals(DiscColor.BLACK, game.getColorAt(new HexCoordinate(1, -1, 0)));
    Assert.assertEquals(DiscColor.WHITE, game.getColorAt(new HexCoordinate(0, 1, -1)));
    Assert.assertFalse(game.gameOver());
    game.skipCurrentTurn();
    game.placeDisc(new HexCoordinate(1, 1, -2));
    Assert.assertTrue(game.gameOver());
  }

  /**
   * tests that gameOver returns true when all black discs are gone.
   */
  @Test
  public void testGameOverAfterGameWonWhite() {
    game = new MutableHexReversi(2);
    game.setUpGame(game.getBoard());
    Assert.assertFalse(game.gameOver());
    game.skipCurrentTurn();
    game.placeDisc(new HexCoordinate(1, -2, 1));
    Assert.assertFalse(game.gameOver());
    game.skipCurrentTurn();
    Assert.assertFalse(game.gameOver());
    game.placeDisc(new HexCoordinate(-1, 2, -1));
    game.skipCurrentTurn();
    game.placeDisc(new HexCoordinate(2, -1, -1));
    Assert.assertTrue(game.gameOver());
  }

  /**
   * tests that gameOver returns true when all gameDiscs colors are either BLACK or WHITE.
   */
  @Test
  public void testGameOverWhenAllSpotsFilled() {
    List<GameCell> board = new ArrayList<>();
    List<GameCell> preMadeBoard = game.getBoard();
    for (GameCell cell : preMadeBoard) {
      board.add(new HexCell(DiscColor.BLACK, cell.getCoordinate()));
    }
    game.setUpGame(board);
    Assert.assertTrue(game.gameOver());
  }

  /**
   * tests that when legal moves exists, game over is false and when legal moves dont,
   * gameovertrue.
   */
  @Test
  public void testGameOverWhenGamePlaying() {
    game = new MutableHexReversi(3);
    game.setUpGame(game.getBoard());
    TextualView tv = new ReversiTextualView(game);

    game.placeDisc(new HexCoordinate(2, -1, -1));
    Assert.assertFalse(game.gameOver());
    game.placeDisc(new HexCoordinate(3, -2, -1));
    game.placeDisc(new HexCoordinate(3, -1, -2));
    game.placeDisc(new HexCoordinate(1, -2, 1));
    game.placeDisc(new HexCoordinate(-1, -1, 2));
    game.placeDisc(new HexCoordinate(-2, 1, 1));
    game.placeDisc(new HexCoordinate(-1, 2, -1));
    game.placeDisc(new HexCoordinate(1, 1, -2));
    game.placeDisc(new HexCoordinate(-3, 2, 1));
    Assert.assertFalse(game.gameOver());
    game.placeDisc(new HexCoordinate(-1, -2, 3));
    Assert.assertFalse(game.gameOver());
    game.placeDisc(new HexCoordinate(2, 1, -3));
    Assert.assertFalse(game.gameOver());
    game.placeDisc(new HexCoordinate(-1, 3, -2));
    Assert.assertFalse(game.gameOver());
    game.placeDisc(new HexCoordinate(-2, -1, 3));
    Assert.assertFalse(game.gameOver());
    game.skipCurrentTurn();
    game.placeDisc(new HexCoordinate(-2, 3, -1));
    Assert.assertFalse(game.gameOver());
    game.placeDisc(new HexCoordinate(-3, 1, 2));
    Assert.assertFalse(game.gameOver());
    game.skipCurrentTurn();
    game.placeDisc(new HexCoordinate(1, 2, -3));
    Assert.assertFalse(game.gameOver());
    game.placeDisc(new HexCoordinate(3, -3, 0));
    game.placeDisc(new HexCoordinate(-3, 0, 3));
    game.placeDisc(new HexCoordinate(0, 3, -3));
    game.placeDisc(new HexCoordinate(-3, 3, 0));
    game.placeDisc(new HexCoordinate(1, -3, 2));
    Assert.assertFalse(game.gameOver());
    game.placeDisc(new HexCoordinate(2, -3, 1));
    game.skipCurrentTurn();
    game.placeDisc(new HexCoordinate(0, -3, 3));
    Assert.assertTrue(game.gameOver());
  }

  @Test
  public void testCheckGivenPlayerHasMovesWorksWhenNoMovesLeft() {
    game = new MutableHexReversi(1);
    game.setUpGame(game.getBoard());
    Assert.assertFalse(game.checkCurrentPlayerHasLegalMovesLeft());
    game.skipCurrentTurn();
    Assert.assertFalse(game.checkCurrentPlayerHasLegalMovesLeft());
  }

  @Test
  public void testCheckPlayerHasMovesWorksThroughoutGame() {
    game = new MutableHexReversi(3);
    game.setUpGame(game.getBoard());
    Assert.assertTrue(game.checkCurrentPlayerHasLegalMovesLeft());
    game.placeDisc(new HexCoordinate(2, -1, -1));
    Assert.assertTrue(game.checkCurrentPlayerHasLegalMovesLeft());
    game.skipCurrentTurn();
    game.placeDisc(new HexCoordinate(-2, 1, 1));
    game.skipCurrentTurn();
    game.placeDisc(new HexCoordinate(1, 1, -2));
    Assert.assertFalse(game.checkCurrentPlayerHasLegalMovesLeft());
  }

  @Test
  public void testGetScoreWorksAsGameProgresses() {
    game = new MutableHexReversi(3);
    game.setUpGame(game.getBoard());
    Assert.assertEquals(3, game.checkScoreOfPlayer(DiscColor.WHITE));
    Assert.assertEquals(3, game.checkScoreOfPlayer(DiscColor.BLACK));
    game.placeDisc(new HexCoordinate(2, -1, -1));
    Assert.assertEquals(5, game.checkScoreOfPlayer(DiscColor.BLACK));
    Assert.assertEquals(2, game.checkScoreOfPlayer(DiscColor.WHITE));
    game.placeDisc(new HexCoordinate(1, -2, 1));
    Assert.assertEquals(4, game.checkScoreOfPlayer(DiscColor.WHITE));
    game.skipCurrentTurn();
    Assert.assertEquals(4, game.checkScoreOfPlayer(DiscColor.WHITE));
    Assert.assertEquals(4, game.checkScoreOfPlayer(DiscColor.BLACK));
    game.placeDisc(new HexCoordinate(1, 1, -2));
    Assert.assertEquals(7, game.checkScoreOfPlayer(DiscColor.WHITE));
    Assert.assertEquals(2, game.checkScoreOfPlayer(DiscColor.BLACK));
    game.skipCurrentTurn();
    game.skipCurrentTurn();
    game.placeDisc(new HexCoordinate(2, 1, -3));
    Assert.assertEquals(5, game.checkScoreOfPlayer(DiscColor.BLACK));
    Assert.assertEquals(5, game.checkScoreOfPlayer(DiscColor.WHITE));
    game.skipCurrentTurn();
    game.placeDisc(new HexCoordinate(1, -3, 2));
    Assert.assertEquals(9, game.checkScoreOfPlayer(DiscColor.BLACK));
    Assert.assertEquals(2, game.checkScoreOfPlayer(DiscColor.WHITE));
    game.skipCurrentTurn();
    game.placeDisc(new HexCoordinate(-2, 1, 1));
    Assert.assertEquals(12, game.checkScoreOfPlayer(DiscColor.BLACK));
    Assert.assertEquals(0, game.checkScoreOfPlayer(DiscColor.WHITE));
  }
}
