package guiviewtest;

import org.junit.Assert;
import org.junit.Test;

import digitalviews.DigitalReversiWindow;
import digitalviews.HexManager;
import model.MutableReversi;

/**
 * tests any issues with the GUI rendering.
 */
public class WindowTests {

  /**
   * tests that the window throws with an invalid model.
   */
  @Test
  public void testInvalidModelThrows() {
    Assert.assertThrows(IllegalArgumentException.class, () -> new DigitalReversiWindow(null));
  }

  /**
   * tests that the hexmanager throws with invalid arguments.
   */
  @Test
  public void testManagerThrows() {
    Assert.assertThrows(IllegalArgumentException.class
            , () -> new HexManager(10, 1, null));
    Assert.assertThrows(IllegalArgumentException.class
            , () -> new HexManager(0, 1, new MutableReversi(1)));
    Assert.assertThrows(IllegalArgumentException.class
            , () -> new HexManager(0, 1, new MutableReversi(1)));
  }
}
