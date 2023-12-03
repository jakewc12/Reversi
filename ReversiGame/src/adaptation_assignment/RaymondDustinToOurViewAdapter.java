package adaptation_assignment;

import controller.ReversiController;
import digitalviews.DigitalWindow;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import DustinRaymondReversi.controller.ReversiPlayerActions;
import controller.PlayerActions;
import player.Player;
import DustinRaymondReversi.view.ReversiGUIView;

public final class RaymondDustinToOurViewAdapter implements DigitalWindow {
  private final ReversiGUIView viewToBeAdapted;
  private List<PlayerActions> listeners = new ArrayList<>();
  public RaymondDustinToOurViewAdapter(ReversiGUIView view){
    Objects.requireNonNull(view);
    this.viewToBeAdapted = view;
  }
  /**
   * Make the view visible. This is usually called after the view is constructed.
   */
  @Override
  public void makeVisible() {
    viewToBeAdapted.display(true);
  }

  /**
   * Adds the given features set to this view, allowing that features set to
   * be notified whenever a particular event occurs in the view (i.e. a player
   * performs a specific mouse or key action that corresponds to a particular
   * feature of the Reversi game).
   *
   * @param features the listener to add
   * @throws IllegalArgumentException if the listener is null
   */

  /**
   * Signals the view to draw or redraw itself.
   */
  @Override
  public void refresh() {
    viewToBeAdapted.refresh();
  }

  /**
   * Adds features to the window to allow for key interaction.
   *
   * @param features the features to be added to the view.
   */
  @Override
  public void addFeaturesListener(ReversiController features) {
    listeners.add(features);
    viewToBeAdapted.addFeaturesListener(features);
  }

  /**
   * tells the player the error.
   *
   * @param player
   */
  @Override
  public void showErrorMessage(Player player) {
    viewToBeAdapted.showError(player.getPlayerColor().toString());
  }
}
