package basictests;

import model.hexreversi.HexCoordinate;
import model.DiscColor;
import model.hexreversi.HexCell;
import model.GameDisc;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the basic functionality of HexCell and GameDisc.
 */
public class GameComponentTests {

  private GameDisc disc;
  private HexCell cell;

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
    cell = new HexCell(DiscColor.GREY, new HexCoordinate(1, 1, 10));
    Assert.assertEquals("(1, 1, 10)", cell.toString());
    cell = new HexCell(DiscColor.GREY, new HexCoordinate(-1, 0, 0));
    Assert.assertEquals("(-1, 0, 0)", cell.toString());
    cell = new HexCell(DiscColor.GREY, new HexCoordinate(1, 0, 0));
    Assert.assertEquals("(1, 0, 0)", cell.toString());
  }

  /**
   * tests that using an invalid disc for HexCell throws an IllegalArgumentException.
   */
  @Test
  public void testGameCellThrowsErrorOnEmptyContents() {
    Assert.assertThrows(IllegalArgumentException.class
        , () -> new HexCell(null, new HexCoordinate(0, 0, 0)));
  }

  /**
   * tests that GameCells getCoordinateQ, getCoordinatesR, getCoordinateS all work correctly.
   */
  @Test
  public void testGameCellGetQRSWorksAccordingly() {
    int q = 1;
    int r = 2;
    int s = 3;
    cell = new HexCell(DiscColor.GREY, new HexCoordinate(q, r, s));
    Assert.assertEquals(q, cell.getCoordinateQ());
    Assert.assertEquals(r, cell.getCoordinateR());
    Assert.assertEquals(s, cell.getCoordinateS());
  }
}
