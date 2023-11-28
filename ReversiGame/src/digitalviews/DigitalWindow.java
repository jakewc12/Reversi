package digitalviews;


import controller.PlayerActions;
import java.awt.event.KeyListener;
import player.Player;

/**
 * All the methods for the window.
 */

public interface DigitalWindow {

  /**
   * Make the view visible. This is usually called after the view is constructed.
   */
  void makeVisible();

  /**
   * Signals the view to draw or redraw itself.
   */
  void refresh();

  /**
   * Adds features to the window to allow for key interaction.
   *
   * @param features the features to be added to the view.
   */
  void addFeaturesListener(PlayerActions features);

  /**
   * returns the key listener of the view.
   *
   * @return the key listener.
   */
  KeyListener getListener();

  /**
   * tells the player the error.
   */
  void showErrorMessage(Player player);
}
