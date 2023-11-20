package controller;

import java.util.Objects;

import digitalviews.DigitalWindow;
import model.Coordinate;
import model.ModelFeatures;
import model.MutableReversiModel;
import player.Player;

public class MachinePlayerController implements ReversiController, Features, ModelFeatures {
  private final MutableReversiModel model;
  private final Player player;
  private final DigitalWindow view;

  public MachinePlayerController(MutableReversiModel model, Player player, DigitalWindow view) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(player);
    Objects.requireNonNull(view);
    model.addFeaturesInterface(this);
    this.model = model;
    this.player = player;
    this.view = view;

  }

  /**
   * Start the program, i.e. give control to the controller
   */
  @Override
  public void run() {
    //if it is a human player, use view to choose moves. if it is a machine player, use player to choose move

    this.view.refresh();
    this.view.makeVisible();
  }

  /**
   * Places a disc on the Reversi game.
   *
   * @param coordinate the coordinate of the disc to be placed.
   */
  @Override
  public void placeDisc(Coordinate coordinate) {
    //machine play moves is handled in the notifyPlayerItsTurn
  }

  /**
   * skips the turn of the Reversi game.
   */
  @Override
  public void skipTurn() {
    model.skipCurrentTurn();
  }

  @Override
  public void notifyPlayerItsTurn() {
    this.run();
    if (!model.gameOver()) {
      if (model.getCurrentTurn().equals(player.getColor())) {
        player.playMove(model);
      }
    }
  }
}
