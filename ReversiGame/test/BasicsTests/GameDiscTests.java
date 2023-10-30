package BasicsTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.GameDisc;

public class GameDiscTests {
  private GameDisc disc;

  @Test
  public void testGreyDiscCannotBeFlipped() {
    disc = new GameDisc(GameDisc.DiscColor.GREY);
    Assert.assertThrows(IllegalArgumentException.class, ()->disc.flipDisc());
  }

  @Test
  public void testToStringWorks() {
    disc = new GameDisc(GameDisc.DiscColor.WHITE);
    Assert.assertEquals("WHITE",disc.toString());
    disc = new GameDisc(GameDisc.DiscColor.BLACK);
    Assert.assertEquals("BLACK", disc.toString());
    disc = new GameDisc(GameDisc.DiscColor.GREY);
    Assert.assertEquals("GREY", disc.toString());
  }
}
