package controller;

import java.util.Objects;

import digitalviews.DigitalWindow;


import model.Coordinate;
import model.ModelFeatures;
import model.MutableReversi;
import model.MutableReversiModel;
import player.HumanPlayer;
import player.MachinePlayer;
import player.Player;

/**
 * allows for the model to be played using the view.
 */
public class ReversiControllerImp implements ReversiController, Features, ModelFeatures {

  private final MutableReversiModel model;
  private final DigitalWindow view;
  private final Player player;

  /**
   * creates a new MutableReversiController.
   *
   * @param model the model to be played.
   * @param view  the view of the mode..
   */
  public ReversiControllerImp(MutableReversiModel model, Player player, DigitalWindow view) {
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
    if(player instanceof HumanPlayer) {
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
  public void notifyPlayerItsTurn() {
    this.run();
    if(player instanceof MachinePlayer){
      if (!model.gameOver()) {
        if (model.getCurrentTurn().equals(player.getPlayerColor())) {
          player.playMove(model);
        }
      }
    }
  }
}
