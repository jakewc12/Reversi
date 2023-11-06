package controller;

import digitalviews.DigitalWindow;
import model.MutableReversiModel;

public class MVCController implements ReversiController{
  private MutableReversiModel model;
  private DigitalWindow view;

  public MVCController(MutableReversiModel model, DigitalWindow view){
    this.model = model;
    this.view = view;
  }
  @Override
  public String processCommand(String command) {
    return null;
  }

  @Override
  public void go() {

  }
}
