package controller;

import model.Coordinate;

/**
 * Features you want the controller to have so that the player can make moves for skip turn.
 */
public interface PlayerActions {

  /**
   * Places a disc on the Reversi game.
   *
   * @param coordinate the coordinate of the disc to be placed.
   */
  void placeDisc(Coordinate coordinate);

  /**
   * Skips the turn of the Reversi game.
   */
  void skipTurn();
}
