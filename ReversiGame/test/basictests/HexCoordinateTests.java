package basictests;

import model.hexreversi.HexCoordinate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * The HexCoordinateTests class contains JUnit tests for the HexCoordinate class.
 */
public class HexCoordinateTests {

  private HexCoordinate pos1;

  /**
   * Initializes the HexCoordinate object before each test.
   */
  @Before
  public void init() {
    pos1 = new HexCoordinate(1, 0, -1);
  }

  /**
   * Tests the equality of HexCoordinate objects using the equals method.
   */
  @Test
  public void testEqualsWorks() {
    Assert.assertEquals(pos1, new HexCoordinate(1, 0, -1));
    Assert.assertNotEquals(pos1, new HexCoordinate(1, 0, 1));
    Assert.assertNotEquals(pos1, null);
  }

  /**
   * Tests the getter methods of the HexCoordinate class.
   */
  @Test
  public void testGettersWorks() {
    Assert.assertEquals(pos1.getIntQ(), 1);
    Assert.assertEquals(pos1.getIntR(), 0);
    Assert.assertEquals(pos1.getIntS(), -1);
  }
}
