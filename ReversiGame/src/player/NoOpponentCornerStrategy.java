package player;

import java.util.Optional;
import model.Coordinate;
import model.ReadOnlyReversiModel;

/**
 * The NoOpponentCornerStrategy class implements the ReversiStrategy interface and represents a
 * strategy where the player does not choose any move. This strategy is typically used when the
 * player does not have a specific move to play or when a particular strategy is not applicable.
 */
public class NoOpponentCornerStrategy implements ReversiStrategy {

  /**
   * Chooses a move based on the current game state and player information. In this strategy, no
   * move is chosen, and an empty Optional is returned.
   *
   * @param model The ReadOnlyReversiModel representing the current game state.
   * @param who   The Player object representing the player applying the strategy.
   * @return An empty Optional as no move is chosen in this strategy.
   */
  @Override
  public Optional<Coordinate> chooseMove(ReadOnlyReversiModel model, Player who) {
    //TODO: Implement
    return Optional.empty();
  }
}
