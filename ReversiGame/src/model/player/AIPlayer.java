package model.player;

import model.DiscColor;
import model.MutableReversiModel;
import model.Position;

public class AIPlayer implements Player{
  private DiscColor color;
  private ReversiStrategy strategy;
  public AIPlayer(DiscColor color, ReversiStrategy strategy) {
    this.color = color;
    this.strategy = strategy;
  }
  @Override
  public DiscColor getColor() {
    return this.color;
  }

  @Override
  public Position play(MutableReversiModel model) {
    try{
      //return move that gets most number of pieces
    }catch(IllegalStateException e) {
      model.skipCurrentTurn();
    }
    return null;
  }
}
