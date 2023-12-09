package helpers;

import model.squarereversi.MutableSquareReversi;

public class MockMutableSquareReversiModel extends MutableSquareReversi {
  /**
   * Creates a MutableHexReversi and sets all game values to zero until startGame is called.
   *
   * @param size is the intended radius of the game, in relation to the center cell.
   * @invariant a game cannot be played unless gameStarted is true.
   * @invariant size cannot be less than 1.
   */
  public MockMutableSquareReversiModel(int size) {
    super(size);
  }
}
