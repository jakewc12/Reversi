package controller;

import digitalviews.DigitalWindow;


import model.Coordinate;
import model.MutableReversiModel;

/**
 * allows for the model to be played using the view.
 */
public class MutableReversiController implements ReversiControllerInterface, Features {

  private final MutableReversiModel model;
  private final DigitalWindow view;

  /**
   * creates a new MutableReversiController.
   *
   * @param model the model to be played.
   * @param view  the view of the mode..
   */
  public MutableReversiController(MutableReversiModel model, DigitalWindow view) {
    this.model = model;
    this.view = view;
    view.addFeaturesListener(this);
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
    try {
      model.placeDisc(coordinate);
    } catch (Exception ignore) {
      //if the move is illegal.
    }
  }

  /**
   * Skips the current turn in the game.
   */
  @Override
  public void skipTurn() {
    model.skipCurrentTurn();
  }
}
