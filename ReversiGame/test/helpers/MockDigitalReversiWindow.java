package helpers;

import controller.PlayerActions;
import digitalviews.DigitalWindow;
import model.DiscColor;
import model.ReadOnlyReversiModel;
import player.Player;

/**
 * A mock version of ReversiWindow.
 */
public class MockDigitalReversiWindow implements DigitalWindow {

  Appendable log;

  /**
   * Creates a new DigitalReversiWindow.
   *
   * @param model the Reversi to be used.
   */
  public MockDigitalReversiWindow(ReadOnlyReversiModel model, Appendable log) {
    this.log = log;
  }

  private void append(String message) {
    try {
      log.append(message).append("\n");
    } catch (Exception e) {
      throw new IllegalArgumentException("Message failed to append");
    }
  }

  @Override
  public void makeVisible() {
    append("Made window visible");
  }

  @Override
  public void refresh() {
    append("Refreshed window");
  }

  @Override
  public void addFeaturesListener(PlayerActions features) {
    append("Feature added to view");
  }

  @Override
  public void showErrorMessage(DiscColor player) {
    append("Error message shown to " + player);
  }

}
