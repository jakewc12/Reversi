package model;

/**
 * Any actions performed on a reversi board should be included here.
 */
public interface MutableReversiModel extends ReadOnlyReversiModel {

  /**
   * Places a disc of the users current turn on the board in a GameCell at coordinates Q,R,S.
   */
  void placeDisc(int Q, int R, int S);

  /**
   * Skip the turn of the current player.
   */
  void skipCurrentTurn();

}
