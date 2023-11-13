package basictests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Coordinate;

public class CoordinateTests {
  Coordinate pos1;
  @Before
  public void init() {
    pos1 = new Coordinate(1,0,-1);
  }

  @Test
  public void testEqualsWorks() {
    Assert.assertEquals(pos1, new Coordinate(1, 0, -1));
    Assert.assertNotEquals(pos1, new Coordinate(1, 0, 1));
    Assert.assertNotEquals(pos1, null);
  }

  @Test
  public void testGettersWorks() {
    Assert.assertEquals(pos1.getIntQ(),1);
    Assert.assertEquals(pos1.getIntR(),0);
    Assert.assertEquals(pos1.getIntS(),-1);
  }
}
