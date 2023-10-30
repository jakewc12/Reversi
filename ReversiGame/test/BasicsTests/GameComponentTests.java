package BasicsTests;

import model.GameCell;
import model.GameDisc;
import model.GameDisc.DiscColor;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the basic functionality of GameCell and GameDisc.
 */
public class GameComponentTests {

  private GameDisc disc;
  private GameCell cell;

  @Test
  public void testGreyDiscCannotBeFlipped() {
    disc = new GameDisc(GameDisc.DiscColor.GREY);
    Assert.assertThrows(IllegalArgumentException.class, () -> disc.flipDisc());
  }

  @Test
  public void testToStringWorks() {
    disc = new GameDisc(GameDisc.DiscColor.WHITE);
    Assert.assertEquals("WHITE", disc.toString());
    disc = new GameDisc(GameDisc.DiscColor.BLACK);
    Assert.assertEquals("BLACK", disc.toString());
    disc = new GameDisc(GameDisc.DiscColor.GREY);
    Assert.assertEquals("GREY", disc.toString());
  }

  @Test
  public void testGameCellToString() {
    disc = new GameDisc(DiscColor.GREY);
    cell = new GameCell(disc, 1, 1, 10);
    Assert.assertEquals("(1, 1, 10)", cell.toString());
    cell = new GameCell(disc, -1, 0, 0);
    Assert.assertEquals("(-1, 0, 0)", cell.toString());
    cell = new GameCell(disc, 1, 0, 0);
    Assert.assertEquals("(1, 0, 0)", cell.toString());
  }

  @Test
  public void testGameCellThrowsErrorOnEmptyContents() {
    Assert.assertThrows(IllegalArgumentException.class, () -> new GameCell(disc, 0, 0, 0));
  }

  @Test
  public void testGameCellGetQRSWorksAccordingly() {
    int q = 1;
    int r = 2;
    int s = 3;
    disc = new GameDisc(DiscColor.GREY);
    cell = new GameCell(disc, q, r, s);
    Assert.assertEquals(q, cell.getCoordinateQ());
    Assert.assertEquals(r, cell.getCoordinateR());
    Assert.assertEquals(s, cell.getCoordinateS());
  }
}
