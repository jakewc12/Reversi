package controller;

import digitalviews.DigitalWindow;
import java.util.Objects;
import model.Coordinate;
import model.ModelStatus;
import model.MutableReversiModel;
import player.HumanPlayer;
import player.MachinePlayer;
import player.Player;

/**
 * allows for the model to be played using the view.
 */
public class ReversiController implements Controller, PlayerActions, ModelStatus {

  private final MutableReversiModel model;
  private final DigitalWindow view;
  private final Player player;

  /**
   * creates a new MutableReversiController.
   *
   * @param model the model to be played.
   * @param view  the view of the mode.
   */
  public ReversiController(MutableReversiModel model, Player player, DigitalWindow view) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(player);
    Objects.requireNonNull(view);
    this.model = model;
    this.view = view;
    this.player = player;
    view.addFeaturesListener(this);
    model.addFeaturesInterface(this);
  }

  /**
   * executes this controller.
   */
  @Override
  public void run() {
    this.view.refresh();
    this.view.makeVisible();
  }

  /**
   * Places a disc on the board at the specified coordinate.
   *
   * @param coordinate The coordinate where the disc should be placed.
   */
  @Override
  public void placeDisc(Coordinate coordinate) {
    if (player instanceof HumanPlayer) {
      if (this.player.getPlayerColor().equals(model.getCurrentTurn())) {
        try {
          model.placeDisc(coordinate);
        } catch (Exception ignore) {
          //if the move is illegal.
          view.showErrorMessage(this.player);
        }
      }
      this.run();
    }
  }

  /**
   * Skips the current turn in the game.
   */
  @Override
  public void skipTurn() {
    model.skipCurrentTurn();
  }

  @Override
  public void moveWasPlayed() {
    this.run();
    if (player instanceof MachinePlayer) {
      if (!model.gameOver()) {
        if (model.getCurrentTurn().equals(player.getPlayerColor())) {
          player.playMove(model);
        }
      }
    }
  }
}