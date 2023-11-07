package basictests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Position;

public class PositionTests {
  Position pos1;
  @Before
  public void init() {
    pos1 = new Position(1,0,-1);
  }

  @Test
  public void testEqualsWorks() {
    Assert.assertEquals(pos1, new Position(1, 0, -1));
    Assert.assertNotEquals(pos1, new Position(1, 0, 1));
    Assert.assertNotEquals(pos1, null);
  }

  @Test
  public void testGettersWorks() {
    Assert.assertEquals(pos1.getQ(),1);
    Assert.assertEquals(pos1.getR(),0);
    Assert.assertEquals(pos1.getS(),-1);
  }
}
