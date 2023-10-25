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
    if (size <= 0) {
      throw new IllegalArgumentException("Invalid size given");
    }
    this.size = size;
    createAllCells();
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
    for (int q = -size; q < size; q++) {
      for (int r = -size; r < size; r++) {
        for (int s = -size; s < size; s++) {
          if ((q == 0 && r == -1 && s == 1) || (q == 1 && r == 0 && s == -1)
                  || (q == -1 && r == 1 && s == 0)) {
            cells.add(new GameCell(new GameDisc(GameDisc.DiscColor.BLACK), q, r, s));
          } else if ((q == 1 && r == -1 && s == 0) || (q == -1 && r == 0 && s == 1)
                  || (q == 0 && r == 1 && s == -1)) {
            cells.add(new GameCell(new GameDisc(GameDisc.DiscColor.WHITE), q, r, s));
          } else {
            cells.add(new GameCell(new GameDisc(GameDisc.DiscColor.GREY), q, r, s));
          }
        }
      }
    }
  }

  private boolean checkLegalMove(int Q, int R, int S) {
    return false;
  }


  @Override
  public void placeDisc(int Q, int R, int S) {
    checkGameStarted();
    checkValidCoordinates(Q, R, S);
    if (!checkLegalMove(Q, R, S)) {
      throw new IllegalStateException("Illegal move when inputting " + Q + ", " + R + ", " + S);
    }
    //TODO: make the move if it's legal
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
  public int getBoardRadius() {
    checkGameStarted();
    return size;
  }

  @Override
  public boolean gameOver() {
    checkGameStarted();
    return false;
  }
}
