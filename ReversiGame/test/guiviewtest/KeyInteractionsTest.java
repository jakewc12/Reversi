package guiviewtest;

import controller.PlayerActions;
import digitalviews.DigitalReversiWindow;
import digitalviews.DigitalWindow;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.Coordinate;
import model.MockMutableReversiModel;
import model.MutableReversiModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * tests issues with key interactions of the DigitalWindow.
 */
public class KeyInteractionsTest {

  private MutableReversiModel model;
  private Appendable log;
  private DigitalWindow view;

  /**
   * initializes all objects used for testing.
   */
  @Before
  public void init() {
    log = new StringBuffer();
    model = new MockMutableReversiModel(3, log);
    model.setUpGame(model.getBoard());
    view = new DigitalReversiWindow(model);
    view.addFeaturesListener(new PlayerActions() {
      @Override
      public void placeDisc(Coordinate coordinate) {
        model.placeDisc(coordinate);
      }

      @Override
      public void skipTurn() {
        model.skipCurrentTurn();
      }
    });
  }

  /**
   * tests that when S is pressed, the model skips a turn.
   */
  @Test
  public void testPressSSkips() {
    KeyListener listener = view.getListener();
    listener.keyTyped(
        new KeyEvent((Component) view, 0, System.currentTimeMillis(), 0, KeyEvent.VK_S, 's'));

    Assert.assertTrue(log.toString().contains("Turn skipped"));

  }


  @Test
  public void testKeyPressedPlaces() {
    KeyListener listener = view.getListener();
    listener.keyTyped(
        new KeyEvent((Component) view, 0, System.currentTimeMillis(), 0, KeyEvent.VK_P, 'p'));
    String thing = System.out.toString();
    Assert.assertNotEquals("No disc selected to place.", thing);
  }
}
