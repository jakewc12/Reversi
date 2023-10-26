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
  private int numBlackCells;
  private int numWhiteCells;
  private final List<GameCell> cells = new ArrayList<>();

  public MutableReversi() {
    gameStarted = false;
    numWhiteCells = 0;
    numBlackCells = 0;
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
   * checks to see if all the cells in the board have a BLACK or WHITE disc.
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
   * has a total height of 7 cells, size would be 3 cells. Also changes numBlackCells and
   * numWhitesCells whenever a black or white cell is added
   */
  private void createAllCells() {
    for (int q = -size; q <= size; q++) {
      for (int r = -size; r <= size; r++) {
        for (int s = -size; s <= size; s++) {
          if (q + r + s == 0) {
            if ((q == 0 && r == -1 && s == 1) || (q == 1 && r == 0 && s == -1) || (q == -1 && r == 1
                && s == 0)) {
              GameCell newCell = new GameCell(new GameDisc(GameDisc.DiscColor.BLACK), q, r, s);
              cells.add(newCell);
              numBlackCells++;
            } else if ((q == 1 && r == -1 && s == 0) || (q == 0 && r == 1 && s == -1) || (q == -1
                && r == 0 && s == 1)) {
              GameCell newCell = new GameCell(new GameDisc(GameDisc.DiscColor.WHITE), q, r, s);
              cells.add(newCell);
              numWhiteCells++;
            } else {
              cells.add(new GameCell(new GameDisc(DiscColor.GREY), q, r, s));
            }
          }
        }
      }
    }
  }

  private boolean movePossibleInLine(List<GameCell> line) {
    DiscColor currentColor = getCurrentTurn();
    int count = 0;
    for (GameCell cell : line) {
      if (count == 0 && cell.cellContents().getColor() == currentColor) {
        count = 1;
      }
      if (cell.cellContents().getColor() != currentColor
          && cell.cellContents().getColor() != DiscColor.GREY && count >= 1) {
        count++;
      }
      if (cell.cellContents().getColor() == DiscColor.GREY) {
        count = 0;
      }
      if (count > 0 && cell.cellContents().getColor() == currentColor) {
        return true;
      }
    }
    return false;
  }

  private ArrayList<Disc> getInLineFlipsPossible(List<GameCell> line) {
    DiscColor currentColor = getCurrentTurn();
    ArrayList<Disc> toFlip = new ArrayList<>();
    int count = 0;
    for (GameCell cell : line) {
      if (count == 0 && cell.cellContents().getColor() == currentColor) {
        count = 1;
      }
      if (cell.cellContents().getColor() != currentColor
          && cell.cellContents().getColor() != DiscColor.GREY && count >= 1) {
        count++;
        toFlip.add(cell.cellContents());
      }
      if (cell.cellContents().getColor() == DiscColor.GREY) {
        count = 0;
        toFlip.clear();
      }
      if (count > 0 && cell.cellContents().getColor() == currentColor) {
        return toFlip;
      }
    }
    toFlip.clear();
    return toFlip;
  }
  private boolean getAllFlips(int q, int r, int s) {

    //Check Horizontal
    if (movePossibleInLine(getAllCellsInSamePlane(q, "Q"))) {
      return true;
    }
    //Check Right diagonal
    if (movePossibleInLine(getAllCellsInSamePlane(r, "R"))) {
      return true;
    }
    //Check Left diagonal
    return movePossibleInLine(getAllCellsInSamePlane(s, "S"));
  }

  private boolean checkLegalMove(int q, int r, int s) {

    //Check Horizontal
    if (movePossibleInLine(getAllCellsInSamePlane(q, "Q"))) {
      return true;
    }
    //Check Right diagonal
    if (movePossibleInLine(getAllCellsInSamePlane(r, "R"))) {
      return true;
    }
    //Check Left diagonal
    return movePossibleInLine(getAllCellsInSamePlane(s, "S"));
  }

  private List<GameCell> getAllCellsInSamePlane(int plane, String typePlane) {
    List<GameCell> cellsInPlane = new ArrayList<>();
    for (GameCell cell : cells) {
      if (Objects.equals(typePlane, "Q")) {
        if (cell.getCoordinateQ() == plane) {
          cellsInPlane.add(cell);
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


  /**
   * Checks to see if the move is valid. if it is, it flips all discs inbetween the placed disc and
   * the disc on its plane of the same color. When this happens, numBlackCells and numWhiteCells
   * should change accordingly
   *
   * @param q coordinate q
   * @param r coordinate r
   * @param s coordinate s
   * @throws IllegalArgumentException if coordinates are invalid
   * @throws IllegalStateException    if the move is illegal
   */
  @Override
  public void placeDisc(int q, int r, int s) {
    checkGameStarted();
    checkValidCoordinates(q, r, s);
    if (getDiscAt(q, r, s).getColor() != DiscColor.GREY) {
      throw new IllegalStateException("Cannot place a disc on an occupied disk");
    }
    if (!checkLegalMove(q, r, s)) {
      throw new IllegalStateException("Illegal move when inputting " + q + ", " + r + ", " + s);
    }
    //TODO: make the move if it's legal and flip the discs
  }

  @Override
  public void skipCurrentTurn() {
    checkGameStarted();
    firstPlayersTurn = !firstPlayersTurn;
  }

  @Override
  public Disc getDiscAt(int q, int r, int s) {
    checkGameStarted();
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
    if (numWhiteCells == 0 || numBlackCells == 0) {
      return true;
    }
    for (GameCell cell : cells) {
      if (!cell.cellContents().getColor().equals(DiscColor.GREY)) {
        if (getAllCellsInSamePlane(cell.getCoordinateQ(), "Q").size() > 0
            && getAllCellsInSamePlane(cell.getCoordinateR(), "R").size() > 0
            && getAllCellsInSamePlane(cell.getCoordinateS(), "S").size() > 0
          /* need to check if the placed tile is legal*/) {
          return false;
        }
      }
    }
    return true;
  }
}
