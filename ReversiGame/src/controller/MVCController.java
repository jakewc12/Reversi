package controller;

import model.MutableReversiModel;
import digitalviews.ReversiView;

public class MVCController implements ReversiController{
  private MutableReversiModel model;
  private ReversiView view;

  public MVCController(MutableReversiModel model, ReversiView view){
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
