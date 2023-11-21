package basictests;

import model.Coordinate;
import model.DiscColor;
import model.GameCell;
import model.GameDisc;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the basic functionality of GameCell and GameDisc.
 */
public class GameComponentTests {

  private GameDisc disc;
  private GameCell cell;

  /**
   * tests that grey discs cannot be flipped.
   */
  @Test
  public void testGreyDiscCannotBeFlipped() {
    disc = new GameDisc(DiscColor.GREY);
    Assert.assertThrows(IllegalArgumentException.class, () -> disc.flipDisc());
  }

  /**
   * tests that GameDiscs toString method works.
   */
  @Test
  public void testToStringWorks() {
    disc = new GameDisc(DiscColor.WHITE);
    Assert.assertEquals("WHITE", disc.toString());
    disc = new GameDisc(DiscColor.BLACK);
    Assert.assertEquals("BLACK", disc.toString());
    disc = new GameDisc(DiscColor.GREY);
    Assert.assertEquals("GREY", disc.toString());
  }

  /**
   * tests that GameCells toString method works.
   */
  @Test
  public void testGameCellToStringWorks() {
    cell = new GameCell(DiscColor.GREY, new Coordinate(1, 1, 10));
    Assert.assertEquals("(1, 1, 10)", cell.toString());
    cell = new GameCell(DiscColor.GREY, new Coordinate(-1, 0, 0));
    Assert.assertEquals("(-1, 0, 0)", cell.toString());
    cell = new GameCell(DiscColor.GREY, new Coordinate(1, 0, 0));
    Assert.assertEquals("(1, 0, 0)", cell.toString());
  }

  /**
   * tests that using an invalid disc for GameCell throws an IllegalArgumentException.
   */
  @Test
  public void testGameCellThrowsErrorOnEmptyContents() {
    Assert.assertThrows(IllegalArgumentException.class
        , () -> new GameCell(null, new Coordinate(0, 0, 0)));
  }

  /**
   * tests that GameCells getCoordinateQ, getCoordinatesR, getCoordinateS all work correctly.
   */
  @Test
  public void testGameCellGetQRSWorksAccordingly() {
    int q = 1;
    int r = 2;
    int s = 3;
    cell = new GameCell(DiscColor.GREY, new Coordinate(q, r, s));
    Assert.assertEquals(q, cell.getCoordinateQ());
    Assert.assertEquals(r, cell.getCoordinateR());
    Assert.assertEquals(s, cell.getCoordinateS());
  }
}
