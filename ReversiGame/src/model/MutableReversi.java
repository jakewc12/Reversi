package model;

import java.util.List;

public class MutableReversi implements MutableReversiModel {
  //true if it's player 1s turn, false if it's player 2s turn
  private boolean firstPlayersTurn;
  //width is how far from the center cell every edge cell is
  private final int size;
  private boolean gameStarted;
  private List<GameCell> cells;

  public MutableReversi(int size) {
    this.size = size;
  }

  private void checkValidCoordinates(int Q, int R, int S) {
    if (Math.abs(Q) > size || Math.abs(R) > size || Math.abs(S) > size) {
      throw new IllegalArgumentException("Invalid coordinates given");
    }
  }

  private void checkGameStarted() {
    if (!gameStarted) {
      throw new IllegalStateException("Game not started");
    }
  }

  private void createAllCells() {

  }

  @Override
  public void startGame() {
    if (gameStarted) {
      throw new IllegalStateException("Game already started");
    }
    //initalize cells
    firstPlayersTurn = true;
    gameStarted = true;
  }

  @Override
  public void placeDisc(int Q, int R, int S) {
    checkGameStarted();
    checkValidCoordinates(Q, R, S);
  }

  @Override
  public void skipCurrentTurn() {
    checkGameStarted();
    firstPlayersTurn = !firstPlayersTurn;
  }

  @Override
  public Disc getDiscAt(int Q, int R, int S) {
    checkGameStarted();

    //is there an efficient way of traversing the list? if we use Q,R,S to get the disc, it would
    //quite possibly take O(n) time.
    checkValidCoordinates(Q, R, S);
    return null;
  }

  @Override
  public String getCurrentTurn() {
    checkGameStarted();
    if (firstPlayersTurn) {
      return "player 1";
    } else {
      return "player 2";
    }
  }

  @Override
  public int getBoardSize() {
    checkGameStarted();
    //from top to bottom
    return size * 2 + 1;
  }

  @Override
  public boolean gameOver() {
    checkGameStarted();
    return false;
  }
}
