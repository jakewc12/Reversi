package GUIviewtest;

import org.junit.Assert;
import org.junit.Test;

import digitalviews.DigitalReversiWindow;
import digitalviews.HexManager;
import model.MutableReversi;

public class WindowTests {

  @Test
  public void testInvalidModelThrows() {
    Assert.assertThrows(IllegalArgumentException.class, () -> new DigitalReversiWindow(null));
  }

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
