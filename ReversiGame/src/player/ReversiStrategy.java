package player;

import java.util.Optional;
import model.Coordinate;
import model.hexreversi.LogicalHexCoordinate;
import model.ReadOnlyReversiModel;

/**
 * The ReversiStrategy interface defines the common behavior for strategies in a Reversi game.
 * Implementations of this interface determine the best move for a player based on the current game
 * state and the player's information.
 */
public interface ReversiStrategy {

  /**
   * Chooses the best move for a player based on the current game state and player information.
   *
   * @param model The ReadOnlyReversiModel representing the current game state.
   * @param who   The Player object representing the player using the strategy.
   * @return An Optional containing the chosen move if found by the strategy or an empty optional.
   */
  Optional<Coordinate> chooseMove(ReadOnlyReversiModel model, Player who);
}

