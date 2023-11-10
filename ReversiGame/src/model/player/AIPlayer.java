package model.player;

import model.DiscColor;
import model.MutableReversiModel;

public class AIPlayer implements Player {

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
  public void playMove(MutableReversiModel model) {
  //TODO: implement play move using strategies.
    model.skipCurrentTurn();
  }
}
