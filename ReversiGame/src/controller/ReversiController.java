package controller;

import digitalviews.DigitalWindow;
import dustinraymondreversi.controller.ReversiPlayerActions;
import dustinraymondreversi.model.HexPosn;
import java.util.Objects;
import model.Coordinate;
import model.ModelStatus;
import model.MutableReversiModel;
import model.hexreversi.HexCoordinate;
import player.HumanPlayer;
import player.MachinePlayer;
import player.Player;

/**
 * allows for the model to be played using the view.
 */
public class ReversiController implements Controller, PlayerActions, ModelStatus,
    ReversiPlayerActions {

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
   * Places a disc on the board at the specified logicalCoordinate.
   *
   * @param logicalCoordinate The logicalCoordinate where the disc should be placed.
   */
  @Override
  public void placeDisc(Coordinate logicalCoordinate) {
    if (player instanceof HumanPlayer) {
      if (this.player.getPlayerColor().equals(model.getCurrentTurn())) {
        try {
          model.placeDisc(logicalCoordinate);
        } catch (Exception ignore) {
          //if the move is illegal.
          System.out.println(ignore);
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

  @Override
  public void attemptMove(HexPosn pos) {
    model.placeDisc(new HexCoordinate(pos.getQ(), pos.getR(), (-pos.getQ() - pos.getR())));
  }

  @Override
  public void attemptPass() {
    model.skipCurrentTurn();
  }
}
