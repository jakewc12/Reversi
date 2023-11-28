package controller;

import model.Coordinate;

/**
 * Features you want the controller to have so that the view can tell the controller to do the
 * features when the time comes.
 */
public interface Features {

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
