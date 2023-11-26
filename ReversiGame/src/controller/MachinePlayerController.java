package controller;

import digitalviews.DigitalWindow;
import java.util.Objects;
import model.Coordinate;
import model.ModelFeatures;
import model.MutableReversiModel;
import player.Player;

/**
 * A machine player controller that the input given from a machine player to inform the model a move
 * is made.
 */
public class MachinePlayerController implements ReversiControllerInterface, Features,
    ModelFeatures {

  private final MutableReversiModel model;
  private final Player player;
  private final DigitalWindow view;

  /**
   * Creates a machine player controller.
   *
   * @param model  the model that is being played on.
   * @param player the machine player which the controller will control.
   * @param view   the digital view of the game.
   */
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
    //if it is a human player, use view to choose moves.
    // If it is a machine player, use player to choose move

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
    player.playMove(model);
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
      if (model.getCurrentTurn().equals(player.getPlayerColor())) {
        player.playMove(model);
      }
    }
  }
}
