package player;

import java.util.Objects;
import java.util.Optional;
import model.DiscColor;
import model.MutableReversiModel;

/**
 * A class meant to represent a human player for the game reversi.
 */
public class HumanPlayer implements Player {

  private final DiscColor playerColor;

  /**
   * Creates a human player for ReversiGame.
   *
   * @param model       The model the player will play on.
   * @param playerColor The color of the discs the player will place.
   */
  public HumanPlayer(MutableReversiModel model, DiscColor playerColor) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(playerColor);
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
    //for human players, move is handled in the view. This is needed to implement the interface.
  }

  /**
   * Returns optional empty because there is no strategy.
   *
   * @return optional empty.
   */
  @Override
  public Optional<ReversiStrategy> getStrategy() {
    return Optional.empty();
  }
}
