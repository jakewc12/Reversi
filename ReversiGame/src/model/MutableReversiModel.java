package model;

/**
 * Any actions performed on a reversi board should be included here.
 */
public interface MutableReversiModel extends ReadOnlyReversiModel {

  /**
   * Checks to see if the move is valid. if it is, it flips all discs inbetween the placed disc and
   * the disc on its plane of the same color. When this happens, numBlackCells and numWhiteCells
   * should change accordingly
   *
   * @param coordinate the coordinate of the hex you are checking.
   * @throws IllegalArgumentException if coordinates are invalid
   * @throws IllegalStateException    if the move is illegal.
   */
  void placeDisc(Coordinate coordinate);

  /**
   * Skip the turn of the current player.
   *
   * @throws IllegalStateException if game isn't started.
   */
  void skipCurrentTurn();

  void addFeaturesInterface(ModelFeatures features);
}
