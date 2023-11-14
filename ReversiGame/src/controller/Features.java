package controller;

import model.Coordinate;

public interface Features {
  /**
   * Places a disc on the Reversi game.
   * @param coordinate the coordinate of the disc to be placed.
   */
  void placeDisc(Coordinate coordinate);

  /**
   * skips the turn of the Reversi game.
   */
  void skipTurn();
}
