package helpers;

import controller.PlayerActions;
import digitalviews.DigitalWindow;
import java.awt.event.KeyListener;
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
  public KeyListener getListener() {
    return null;
  }

  @Override
  public void showErrorMessage(Player player) {
    append("Error message shown to " + player);
  }

  /**
   * Shows a pop-up telling the player that it is its turn.
   */
  @Override
  public void notifyPlayerItsTurn() {
    //needed to implement the class.
  }

  /**
   * Removes notification telling player its turn if the move is done.
   */
  @Override
  public void turnOffNotificationOnceTurnIsDone() {
    //needed to implement the class.
  }
}
