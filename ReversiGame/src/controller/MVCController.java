package controller;

import digitalviews.DigitalWindow;
import java.util.Scanner;
import model.Coordinate;
import model.MutableReversiModel;

public class MVCController implements ReversiControllerInterface, Features {

  private final MutableReversiModel model;
  private final DigitalWindow view;

  public MVCController(MutableReversiModel model, DigitalWindow view) {
    this.model = model;
    this.view = view;
    view.addFeaturesListener(this);
  }
  @Override
  public void go() {
    this.view.refresh();
    this.view.makeVisible();
  }
  @Override
  public void placeDisc(Coordinate coordinate) {
    model.placeDisc(coordinate);
  }

  @Override
  public void skipTurn() {
    model.skipCurrentTurn();
  }
}
