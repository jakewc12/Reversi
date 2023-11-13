package controller;

import model.Coordinate;

/**
 * The Features interface defines common actions that can be performed in a Reversi game.
 * Implementations of this interface provide methods for placing a disc on the board and skipping a
 * turn.
 */
public interface Features {

  /**
   * Places a disc on the board at the specified coordinate.
   *
   * @param coordinate The coordinate where the disc should be placed.
   */
  void placeDisc(Coordinate coordinate);

  /**
   * Skips the current turn in the game.
   */
  void skipTurn();
}
