package guiviewtest;

import digitalviews.DigitalReversiWindow;
import digitalviews.ShapeManager;
import model.hexreversi.MutableHexReversi;
import org.junit.Assert;
import org.junit.Test;

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
        , () -> new ShapeManager(10, 1, null));
    Assert.assertThrows(IllegalArgumentException.class
        , () -> new ShapeManager(0, 1, new MutableHexReversi(1)));
    Assert.assertThrows(IllegalArgumentException.class
        , () -> new ShapeManager(0, 1, new MutableHexReversi(1)));
  }
}
