package dustinraymondreversi.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interface for a read-only Reversi model, cannot modify any data in the model itself.
 */
public interface ReadOnlyReversiModel {

  /**
   * Checks if there is a legal move at the given HexPosn for the current player.
   *
   * @return - true if the move is legal, false if not.
   */
  boolean isLegalMoveAt(HexPosn pos);

  /**
   * Returns whether the cuurent player has a legal move.
   *
   * @return - true if current player has legal move, false if not.
   */
  boolean playerHasLegalMove();

  /**
   * Returns whether the game is over.
   *
   * @return - true if game is over, false if not.
   */
  boolean isGameOver();

  /**
   * Returns a list containing every valid coordinate in the game, where a piece could potentially
   * be placed (disregarding the legality of the move, only considering bounds).
   *
   * @return the list of coordinates
   */
  List<HexPosn> getAllValidCoordinates();

  /**
   * Returns whichever player is occupying the cell located at the given coordinates, or empty if
   * there is no player at the coordinates.
   *
   * @param c - the given coordinate in the board to search.
   * @return - the model.PlayerPiece enum ot the given cell coordinate, Optional empty enum if no p.
   * @throws IllegalArgumentException - if the given coordinates are invalid
   */
  Optional<PlayerPiece> getPlayerAt(HexPosn c) throws IllegalArgumentException;

  /**
   * Returns the side length of the board.
   *
   * @return - side length of board, as an int.
   */
  int getSideLength();

  /**
   * Return all the scores of each player in the game.
   *
   * @return - a map containing each model.PlayerPiece enum as key, their score as the value
   */
  Map<PlayerPiece, Integer> getScores();

  /**
   * Returns the player whose turn it is.
   *
   * @return - the model.PlayerPiece enum whose turn it is.
   */
  PlayerPiece getCurrentPlayer();

  /**
   * Returns whichever player or players at the moment currently have the highest score. If multiple
   * players current are tied for first place, they are all returned by this method.
   *
   * @return - the winning players
   */
  List<PlayerPiece> getWinningPlayers();

}
