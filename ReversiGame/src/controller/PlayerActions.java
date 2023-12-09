package controller;

import model.Coordinate;
import model.hexreversi.LogicalHexCoordinate;

/**
 * Features you want the controller to have so that the player can make moves for skip turn.
 */
public interface PlayerActions {

  /**
   * Places a disc on the Reversi game.
   *
   * @param logicalCoordinate the logicalCoordinate of the disc to be placed.
   */
  void placeDisc(Coordinate logicalCoordinate);

  /**
   * Skips the turn of the Reversi game.
   */
  void skipTurn();
}
