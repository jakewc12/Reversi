package player;

import java.util.Objects;

import model.DiscColor;
import model.MutableReversiModel;

public class HumanPlayer implements Player{
  private final MutableReversiModel model;
  private final DiscColor playerColor;
  public HumanPlayer(MutableReversiModel model, DiscColor playerColor){
    Objects.requireNonNull(model);
    Objects.requireNonNull(playerColor);
    this.model = model;
    this.playerColor = playerColor;
  }

  /**
   * Returns the disc color associated with this player.
   *
   * @return The disc color (WHITE or BLACK) associated with the player.
   */
  @Override
  public DiscColor getPlayerColor() {
    return playerColor;
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
