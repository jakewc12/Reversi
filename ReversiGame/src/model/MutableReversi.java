package model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import model.GameCell.Direction;
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

  /**
   * Creates a MutableReversi and sets all game values to zero until startGame is called.
   */
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
      throw new IllegalArgumentException(
          "Invalid coordinates given. Max coordinate size is: " + size);
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

  /**
   * Checks to see if a series of cells can be flipped.
   *
   * @param line a line of discs that you want checked to see if there is a move possible in them.
   * @return discs which can be flipped if the move is legal
   */
  private ArrayList<Disc> getInLineFlipsPossible(List<Disc> line) {
    DiscColor currentColor = getCurrentTurn();
    ArrayList<Disc> toFlip = new ArrayList<>();
    ArrayList<Disc> current = new ArrayList<>();
    int count = 0;
    for (Disc disc : line) {
      if (count == 0 && disc.getColor() == currentColor) {
        count = 1;
      }
      if (disc.getColor() != currentColor && disc.getColor() != DiscColor.GREY && count >= 1) {
        count++;
        current.add(disc);
      }
      if (disc.getColor() == DiscColor.GREY) {
        count = 0;
        current.clear();
      }
      if (count > 1 && disc.getColor() == currentColor) {
        toFlip.addAll(current);
        count = 1;
        current.clear();
      }
    }

    return toFlip;
  }

  /**
   * Get all the possible flips if the target cell was placed.
   *
   * @param targetCell the cell you want to place on the board.
   * @return the surrounding cells which will flip if the targetCell was placed.
   */
  private ArrayList<Disc> getAllFlips(GameCell targetCell) {
    ArrayList<Disc> toFlip = new ArrayList<>();
    //Check Horizontal
    toFlip.addAll(getInLineFlipsPossible(getAllCellsInSamePlane(targetCell, "Q")));
    //Check Right diagonal
    toFlip.addAll(getInLineFlipsPossible(getAllCellsInSamePlane(targetCell, "R")));
    //Check Left diagonal
    toFlip.addAll(getInLineFlipsPossible(getAllCellsInSamePlane(targetCell, "S")));
    return toFlip;
  }


  private List<Disc> getAllCellsInSamePlane(GameCell targetCell, String typePlane) {
    ArrayList<Disc> returnList = new ArrayList<>();
    // Intake cell and get surrounding cells on that plane. Surrounding is non-grey cells and
    // ends on grey.
    switch (typePlane) {
      case "Q":
      case "q":
        //get surrounding cells on q plane
        returnList.addAll(getCellsInDirection(targetCell, Direction.TOPLEFT));
        returnList.add(targetCell.cellContents());
        returnList.addAll(getCellsInDirection(targetCell, Direction.BOTTOMRIGHT));
        break;
      case "R":
      case "r":
        //get surrounding cells on r plane
        returnList.addAll(getCellsInDirection(targetCell, Direction.DEADLEFT));
        returnList.add(targetCell.cellContents());
        returnList.addAll(getCellsInDirection(targetCell, Direction.DEADRIGHT));
        break;
      case "S":
      case "s":
        //get surrounding cells on s plane
        returnList.addAll(getCellsInDirection(targetCell, Direction.TOPRIGHT));
        returnList.add(targetCell.cellContents());
        returnList.addAll(getCellsInDirection(targetCell, Direction.BOTTOMLEFT));
        break;
      default:
        throw new IllegalArgumentException("Valid commands for typePlane are q,r,s");
    }
    System.out.println(returnList);
    return returnList;
  }

  private List<Disc> getCellsInDirection(GameCell targetCell, Direction direction) {
    ArrayList<Disc> returnList = new ArrayList<>();
    GameCell currentCell = getHexAt(targetCell.getCellNeighbor(direction));
    while (currentCell.cellContents().getColor() != DiscColor.GREY) {
      returnList.add(currentCell.cellContents());
      try {
        currentCell = getHexAt(currentCell.getCellNeighbor(direction));
      } catch (IllegalArgumentException e) {
        break;
      }
    }
    return returnList;
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
    if (this.getDiscAt(q, r, s).getColor() != DiscColor.GREY) {
      throw new IllegalStateException("Cannot place a disc on an occupied disk");
    }
    this.getDiscAt(q, r, s).changeColorTo(getCurrentTurn());
    if (getAllFlips(getHexAt(q, r, s)).isEmpty()) {
      this.getDiscAt(q, r, s).changeColorTo(DiscColor.GREY);
      throw new IllegalStateException("Illegal move when inputting " + q + ", " + r + ", " + s);
    }
    ArrayList<Disc> flipDiscs = getAllFlips(getHexAt(q, r, s));

    this.getDiscAt(q, r, s).changeColorTo(getCurrentTurn());
    for (Disc disc : flipDiscs) {
      disc.flipDisc();
    }
  }

  @Override
  public void skipCurrentTurn() {
    checkGameStarted();
    firstPlayersTurn = !firstPlayersTurn;
  }

  private Disc getDiscAt(int q, int r, int s) {
    checkGameStarted();
    checkValidCoordinates(q, r, s);
    return getHexAt(q, r, s).cellContents();
  }

  private GameCell getHexAt(int q, int r, int s) {
    checkGameStarted();
    checkValidCoordinates(q, r, s);
    for (GameCell cell : cells) {
      if (cell.getCoordinateQ() == q && cell.getCoordinateR() == r && cell.getCoordinateS() == s) {
        return cell;
      }
    }
    throw new NoSuchElementException("Could not find cell at: " + q + ", " + r + ", " + s);
  }

  private GameCell getHexAt(GameCell cell) {
    int q = cell.getCoordinateQ();
    int r = cell.getCoordinateR();
    int s = cell.getCoordinateS();
    return getHexAt(q, r, s);
  }

  @Override
  public DiscColor getColorAt(int q, int r, int s) {
    checkGameStarted();
    checkValidCoordinates(q, r, s);
    return getDiscAt(q, r, s).getColor();
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
        if (getAllCellsInSamePlane(cell, "Q").size() > 0
            && getAllCellsInSamePlane(cell, "R").size() > 0
            && getAllCellsInSamePlane(cell, "S").size() > 0
          /* need to check if the placed tile is legal*/) {
          return false;
        }
      }
    }
    return true;
  }
}
