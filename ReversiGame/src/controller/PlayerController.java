package controller;

import java.util.Objects;

import digitalviews.DigitalWindow;
import model.Coordinate;
import model.MutableReversiModel;
import player.Player;

public class PlayerController implements  ReversiControllerInterface, Features{
  private MutableReversiModel model;
  private Player player;
  private DigitalWindow view;
  public PlayerController(MutableReversiModel model, Player player, DigitalWindow view){
    Objects.requireNonNull(model);
    Objects.requireNonNull(player);
    Objects.requireNonNull(view);
    this.model = model;
    this.player = player;
    this.view = view;
  }
  /**
   * Start the program, i.e. give control to the controller
   */
  @Override
  public void run() {
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

  }

  /**
   * skips the turn of the Reversi game.
   */
  @Override
  public void skipTurn() {

  }
}
