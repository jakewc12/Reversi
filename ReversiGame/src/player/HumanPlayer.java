package player;

import java.util.Objects;

import model.DiscColor;
import model.MutableReversiModel;

public class HumanPlayer implements Player{
  private MutableReversiModel model;
  public HumanPlayer(MutableReversiModel model){
    Objects.requireNonNull(model);
    this.model = model;
  }

  /**
   * Returns the disc color associated with this player.
   *
   * @return The disc color (WHITE or BLACK) associated with the player.
   */
  @Override
  public DiscColor getColor() {
    return null;
  }

  /**
   * Plays a move on the MutableReversiModel based on the player's input.
   *
   * @param model The MutableReversiModel representing the current game state.
   */
  @Override
  public void playMove(MutableReversiModel model) {

  }
}
