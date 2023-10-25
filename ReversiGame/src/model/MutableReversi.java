package model;

import java.util.List;

public class MutableReversi implements MutableReversiModel {
  //true if it's player 1s turn, false if it's player 2s turn
  private boolean firstPlayersTurn;
  //width is how far from the center cell every edge cell is
  private int size;
  private boolean gameStarted;
  private List<GameCell> cells;

  public MutableReversi() {
    gameStarted = false;
  }
  @Override
  public void startGame(int size) {
    if (gameStarted) {
      throw new IllegalStateException("Game already started");
    }
    if(size<=0){
      throw new IllegalArgumentException("Invalid size given");
    }
    this.size = size;
    //initalize cells
    firstPlayersTurn = true;
    gameStarted = true;
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

  /**
   * Size is means the distince from an edge cell to the middle most cell.
   * for example, if a board has a height of 7 cells, size would be 3 cells.
   */
  private void createAllCells() {
    for(int q = -size; q <size; q++){
      for(int r = -size; r < size; r++){
        for(int s = -size; s < size; s++){

        }
      }
    }
  }

  private void checkLegalMove(int Q, int R, int S){

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
    return getBoardRadius() * 2 + 1;
  }
  @Override
  public int getBoardRadius(){
    return size;
  }
  @Override
  public boolean gameOver() {
    checkGameStarted();
    return false;
  }




}
