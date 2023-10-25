package model;

/**
 * Any actions performed on a reversi board should be included here.
 */
public interface MutableReversiModel extends ReadOnlyReversiModel {

  /**
   * Places a disc of the users current turn on the board in a GameCell at coordinates q,r,S.
   *
   * @throws IllegalStateException    if game isn't started.
   * @throws IllegalArgumentException if any of the inputs are invalid.
   */
  void placeDisc(int q, int r, int s);

  /**
   * Skip the turn of the current player.
   *
   * @throws IllegalStateException if game isn't started.
   */
  void skipCurrentTurn();

}
