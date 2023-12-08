package adaptionassignment;

import dustinraymondreversi.controller.ReversiPlayerActions;
import dustinraymondreversi.view.ReversiGUIView;
import controller.PlayerActions;
import digitalviews.DigitalWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.DiscColor;
import player.Player;

/**
 * Adapts our client's view to our view.
 */
public final class RaDusToOurViewAdapter implements DigitalWindow {

  private final ReversiGUIView viewToBeAdapted;
  private final List<PlayerActions> listeners = new ArrayList<>();

  public RaDusToOurViewAdapter(ReversiGUIView view) {
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
  public void addFeaturesListener(PlayerActions features) {
    viewToBeAdapted.addFeaturesListener((ReversiPlayerActions) features);
    listeners.add(features);
  }

  /**
   * tells the player the error.
   *
   * @param player the player that caused the error.
   */
  @Override
  public void showErrorMessage(DiscColor player) {
    viewToBeAdapted.showError(player.toString());
  }
}
