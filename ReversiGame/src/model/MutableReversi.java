package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.GameDisc.DiscColor;

/**
 * Meant to simulate the logic of a game of reversi. The game of reversi does not start until start
 * game is called with an adequate board size.
 */
public class MutableReversi implements MutableReversiModel {

  //true if it's player BLACK's turn, false if it's WHITE's turn
  private boolean firstPlayersTurn;
  //width is how far from the center cell every edge cell is
  private int size;
  private boolean gameStarted;
  private List<GameCell> blackCells = new ArrayList<>();
  private List<GameCell> whiteCells = new ArrayList<>();
  private List<GameCell> cells = new ArrayList<>();

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
    firstPlayersTurn = true;
    gameStarted = true;
  }

  private void checkValidCoordinates(int q, int r, int s) {
    if (Math.abs(q) > size || Math.abs(r) > size || Math.abs(s) > size) {
      throw new IllegalArgumentException("Invalid coordinates given");
    }
  }

  private void checkGameStarted() {
    if (!gameStarted) {
      throw new IllegalStateException("Game not started");
    }
  }

  /**
   * checks to see if all of the cells in the board have a BLACK or WHITE disc.
   *
   * @return false if any cell in cells has a grey disc, else returns true.
   */
  private boolean checkIfAllCellsFilled() {
    for (GameCell cell : cells) {
      if (cell.cellContents().getColor() != DiscColor.GREY) {
        return false;
      }
    }
    return true;
  }

  /**
   * Size is means the distance from an edge cell to the middle most cell. for example, if a board
   * has a height of 7 cells, size would be 3 cells.
   */
  private void createAllCells() {
    for (int q = -size; q <= size; q++) {
      for (int r = -size; r <= size; r++) {
        for (int s = -size; s <= size; s++) {
          if (q + r + s == 0) {
            if ((q == 0 && r == -1 && s == 1) || (q == 1 && r == 0 && s == -1)
                    || (q == -1 && r == 1 && s == 0)) {
              GameCell newCell = new GameCell(new GameDisc(GameDisc.DiscColor.BLACK), q, r, s);
              cells.add(newCell);
              blackCells.add(newCell);
            } else if ((q == 1 && r == -1 && s == 0) || (q == 0 && r == 1 && s == -1)
                    || (q == -1 && r == 0 && s == 1)) {
              GameCell newCell = new GameCell(new GameDisc(GameDisc.DiscColor.WHITE), q, r, s);
              cells.add(newCell);
              whiteCells.add(newCell);
            } else {
              cells.add(new GameCell(new GameDisc(DiscColor.GREY), q, r, s));
            }
          }
        }
      }
    }
  }

  private boolean checkLegalMove(int q, int r, int s) {
    //we need to check if there are any of the same color tiles on each plane, horizontal, vertical, or diagonal
    return false;
  }

  private List<GameCell> getAllCellsInSamePlane(int plane, String typePlane) {
    List<GameCell> cellsInPlane = new ArrayList<>();
    for (GameCell cell : cells) {
      if (Objects.equals(typePlane, "Q")) {
        if (cell.getCoordinateQ() == plane) {
          if (cell.cellContents().getColor() != DiscColor.GREY) {
            cellsInPlane.add(cell);
          }
        }
      } else if (Objects.equals(typePlane, "R")) {
        if (cell.getCoordinateR() == plane) {
          if (cell.cellContents().getColor() != DiscColor.GREY) {
            cellsInPlane.add(cell);
          }
        }
      } else {
        if (cell.getCoordinateS() == plane) {
          if (cell.cellContents().getColor() != DiscColor.GREY) {
            cellsInPlane.add(cell);
          }
        }
      }
    }
    return cellsInPlane;
  }


  @Override
  public void placeDisc(int q, int r, int s) {
    checkGameStarted();
    checkValidCoordinates(q, r, s);
    if (!checkLegalMove(q, r, s)) {
      throw new IllegalStateException("Illegal move when inputting " + q + ", " + r + ", " + s);
    }
    //TODO: make the move if it's legal
  }

  @Override
  public void skipCurrentTurn() {
    checkGameStarted();
    firstPlayersTurn = !firstPlayersTurn;
  }

  @Override
  public Disc getDiscAt(int q, int r, int s) {
    checkGameStarted();
    //is there an efficient way of traversing the list? if we use Q,R,S to get the disc, it would
    //quite possibly take O(n) time.
    checkValidCoordinates(q, r, s);
    for (GameCell cell : cells) {
      if (cell.getCoordinateQ() == q && cell.getCoordinateR() == r && cell.getCoordinateS() == s) {
        return cell.cellContents();
      }
    }
    return null;
  }

  @Override
  public DiscColor getCurrentTurn() {
    checkGameStarted();
    if (firstPlayersTurn) {
      return DiscColor.BLACK;
    } else {
      return DiscColor.WHITE;
    }
  }

  @Override
  public int getBoardSize() {
    checkGameStarted();
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
    if (checkIfAllCellsFilled()) {
      return true;
    }
    if (blackCells.size() == 0 || whiteCells.size() == 0) {
      return true;
    }
    for (GameCell cell : blackCells) {
      if (getAllCellsInSamePlane(cell.getCoordinateQ(), "Q").size() > 0 &&
              getAllCellsInSamePlane(cell.getCoordinateR(), "R").size() > 0
              && getAllCellsInSamePlane(cell.getCoordinateS(), "S").size() > 0) {
        return false;
      }
    }
    for (GameCell cell : whiteCells) {
      if (getAllCellsInSamePlane(cell.getCoordinateQ(), "Q").size() > 0 &&
              getAllCellsInSamePlane(cell.getCoordinateR(), "R").size() > 0
              && getAllCellsInSamePlane(cell.getCoordinateS(), "S").size() > 0) {
        return false;
      }
    }
    return true;
  }
}
