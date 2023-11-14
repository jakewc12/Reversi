package basictests;

import model.Coordinate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * The CoordinateTests class contains JUnit tests for the Coordinate class.
 */
public class CoordinateTests {

  private Coordinate pos1;

  /**
   * Initializes the Coordinate object before each test.
   */
  @Before
  public void init() {
    pos1 = new Coordinate(1, 0, -1);
  }

  /**
   * Tests the equality of Coordinate objects using the equals method.
   */
  @Test
  public void testEqualsWorks() {
    Assert.assertEquals(pos1, new Coordinate(1, 0, -1));
    Assert.assertNotEquals(pos1, new Coordinate(1, 0, 1));
    Assert.assertNotEquals(pos1, null);
  }

  /**
   * Tests the getter methods of the Coordinate class.
   */
  @Test
  public void testGettersWorks() {
    Assert.assertEquals(pos1.getIntQ(), 1);
    Assert.assertEquals(pos1.getIntR(), 0);
    Assert.assertEquals(pos1.getIntS(), -1);
  }
}
