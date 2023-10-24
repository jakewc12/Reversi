package model;

import java.util.List;

public class MutableReversi implements MutableReversiModel {
  //true if it's player 1s turn, false if it's player 2s turn
  private boolean firstPlayersTurn;
  private List<GameCell> cells;

  @Override
  public void startGame() {
    //initalize cells
    firstPlayersTurn = true;
  }

  @Override
  public void placeDisc(int Q, int R, int S) {

  }

  @Override
  public void skipCurrentTurn() {
    firstPlayersTurn = !firstPlayersTurn;
  }

  @Override
  public Disc getDiscAt(int Q, int R, int S) {
    //is there an efficient way of traversing the list? if we use Q,R,S to get the disc, it would
    //quite possibly take O(n) time.
    return null;
  }

  @Override
  public String getCurrentTurn() {
    return null;
  }

  @Override
  public int getBoardHeight() {
    return 0;
  }

  @Override
  public int getBoardWidth() {
    return 0;
  }

  @Override
  public boolean gameOver() {
    return false;
  }
}
