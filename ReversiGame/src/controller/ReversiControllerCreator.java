package controller;

import digitalviews.DigitalWindow;
import model.MutableReversi;
import model.MutableReversiModel;
import player.HumanPlayer;
import player.Player;

public class ReversiControllerCreator {
  public ReversiController create(MutableReversiModel model, Player player, DigitalWindow view){
    if(player instanceof HumanPlayer){
      return new HumanPlayerController(model, player, view);
    }else{
      return new MachinePlayerController(model, player, view);
    }
  }
}
