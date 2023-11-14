package controller;

import digitalviews.DigitalWindow;
import java.util.Scanner;
import model.Coordinate;
import model.MutableReversiModel;

public class MutableReversiController implements ReversiControllerInterface, Features {

  private final MutableReversiModel model;
  private final DigitalWindow view;

  public MutableReversiController(MutableReversiModel model, DigitalWindow view) {
    this.model = model;
    this.view = view;
    view.addFeaturesListener(this);
  }
  @Override
  public void go() {
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
    try{
      model.placeDisc(coordinate);
    }catch(Exception ignore){}

  }

  /**
   * Skips the current turn in the game.
   */
  @Override
  public void skipTurn() {
    model.skipCurrentTurn();
  }
}
