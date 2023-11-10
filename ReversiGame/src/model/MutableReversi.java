package model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import model.GameCell.Direction;

/**
 * Meant to simulate the logic of a game of reversi. The game of reversi does not start until start
 * game is called with an adequate board size.
 */
public class MutableReversi implements MutableReversiModel {

  //width is how far from the center cell every edge cell is
  private final int size;
  //true if it's player BLACK's turn, false if it's WHITE's turn
  private boolean blacksTurn;
  private boolean gameStarted;
  private int numBlackTiles;
  private int numWhiteTiles;
  private List<HexagonCell> cells = new ArrayList<>();

  /**
   * Creates a MutableReversi and sets all game values to zero until startGame is called.
   *
   * @param size is the intended radius of the game, in relation to the center cell.
   * @invariant a game cannot be played unless gameStarted is true.
   * @invariant size cannot be less than 1.
   */
  public MutableReversi(int size) {
    if (size <= 0) {
      throw new IllegalArgumentException("Invalid size given");
    }
    this.size = size;
    gameStarted = false;
  }

  /**
   * A test only constructor only accessible through reflection. Do not use unless you are
   * attempting to test code.
   *
   * @param size the size of the board you want to create.
   */
  private MutableReversi(int size, boolean rigged) {
    this.size = size;
    gameStarted = true;
    blacksTurn = true;
    createAllCellsRigged();
  }

  /**
   * Initializes all values.
   *
   * @throws IllegalArgumentException if size is negative.
   * @throws IllegalStateException    if the game has already started.
   */
  @Override
  public void startGame(List<HexagonCell> board) {
    if (gameStarted) {
      throw new IllegalStateException("Game already started");
    }
    if (board.isEmpty()) {
      throw new IllegalArgumentException("Board is empty for startGame");
    }
    this.cells = board;
    blacksTurn = true;
    gameStarted = true;
  }

  /**
   * Size is means the distance from an edge cell to the middle most cell. for example, if a board
   * has a total height of 7 cells, size would be 3 cells. Also changes numBlackCells and
   * numWhitesCells whenever a black or white cell is added
   */
  @Override
  public List<HexagonCell> getBoard() {
    List<HexagonCell> localCells = new ArrayList<>();
    for (int q = -size; q <= size; q++) {
      for (int r = -size; r <= size; r++) {
        for (int s = -size; s <= size; s++) {
          if (q + r + s == 0) {
            if ((q == 0 && r == -1 && s == 1) || (q == 1 && r == 0 && s == -1) || (q == -1 && r == 1
                && s == 0)) {
              GameCell newCell = new GameCell(new GameDisc(DiscColor.BLACK),
                  new Coordinate(q, r, s));
              localCells.add(newCell);
              numBlackTiles++;
            } else if ((q == 1 && r == -1 && s == 0) || (q == 0 && r == 1 && s == -1) || (q == -1
                && r == 0 && s == 1)) {
              GameCell newCell = new GameCell(new GameDisc(DiscColor.WHITE),
                  new Coordinate(q, r, s));
              localCells.add(newCell);
              numWhiteTiles++;
            } else {
              localCells.add(new GameCell(new GameDisc(DiscColor.GREY), new Coordinate(q, r, s)));
            }
          }
        }
      }
    }
    return localCells;
  }

  @Override
  public boolean checkLegalMove(Coordinate coordinate) {
    return checkLegalMove(coordinate.getQ(), coordinate.getR(), coordinate.getS());
  }

  private boolean checkLegalMove(int q, int r, int s) {
    checkValidCoordinates(q, r, s);
    if (this.getDiscAt(q, r, s).getColor() != DiscColor.GREY) {
      //throw new IllegalStateException("Cannot place a disc on an occupied disc");
      return false;
    }
    this.getDiscAt(q, r, s).changeColorTo(getCurrentTurn());
    if (getAllFlips(getHexAt(q, r, s), getCurrentTurn()).isEmpty()) {
      this.getDiscAt(q, r, s).changeColorTo(DiscColor.GREY);
      //throw new IllegalStateException("Illegal move when inputting " + q + ", " + r + ", " + s);
      return false;
    }
    return true;
  }

  @Override
  public boolean checkCurrentPlayerHasLegalMovesLeft() {
    return checkGivenPlayerHasLegalMoveLeft(blacksTurn);
  }

  @Override
  public int checkScoreOfPlayer(DiscColor color) {
    //there should be a better way to update score. maybe everytime a move is made, update it then
    //instead of doing this.
    if (color == DiscColor.BLACK) {
      return numBlackTiles;
    } else {
      return numWhiteTiles;
    }
    /*
    int count = 0;
    for (HexagonCell cell : cells) {
      if (cell.cellContents().getColor() == color) {
        count++;
      }
    }
    return count;

     */
  }

  /**
   * Checks if the given player, either black or white, has a legal move left and returns true if
   * yes, else no.
   *
   * @param blacksTurn if it's black or whites turn.
   * @return if the given player has a legal move left.
   */
  private boolean checkGivenPlayerHasLegalMoveLeft(boolean blacksTurn) {
    for (HexagonCell cell : cells) {
      if (cell.cellContents().getColor().equals(DiscColor.GREY)) {
        if (blacksTurn) {
          cell.cellContents().changeColorTo(DiscColor.BLACK);
          if (!getAllFlips(cell, DiscColor.BLACK).isEmpty()) {
            cell.cellContents().changeColorTo(DiscColor.GREY);
            return true;
          }

        } else {
          cell.cellContents().changeColorTo(DiscColor.WHITE);
          if (!getAllFlips(cell, DiscColor.WHITE).isEmpty()) {
            cell.cellContents().changeColorTo(DiscColor.GREY);
            return true;
          }
        }
        cell.cellContents().changeColorTo(DiscColor.GREY);
      }
    }
    return false;
  }

  private void checkValidCoordinates(int q, int r, int s) {
    if (Math.abs(q) > size || Math.abs(r) > size || Math.abs(s) > size || ((q + r + s) != 0)) {
      throw new IllegalArgumentException(
          "Invalid coordinates given. Max coordinate size is: " + size + "\n coordinates were (" + q
              + ", " + r + ", " + s + ")");
    }
  }

  private void checkGameStarted() {
    if (!gameStarted) {
      throw new IllegalStateException("Game not started");
    }
  }

  /**
   * Checks to see if all the cells in the board have a BLACK or WHITE disc.
   *
   * @return false if any cell in cells has a grey disc, else returns true.
   */
  private boolean checkIfAllCellsFilled() {
    for (HexagonCell cell : cells) {
      if (cell.cellContents().getColor() == DiscColor.GREY) {
        return false;
      }
    }
    return true;
  }


  private void createAllCellsRigged() {
    for (int q = -size; q <= size; q++) {
      for (int r = -size; r <= size; r++) {
        for (int s = -size; s <= size; s++) {
          if (q + r + s == 0) {
            Coordinate currentCoord = new Coordinate(q,r,s);
            if ((q == 0 && r == -1 && s == 1) || (q == 1 && r == 0 && s == -1) || (q == -1 && r == 1
                && s == 0)) {
              GameCell newCell = new GameCell(new GameDisc(DiscColor.BLACK), currentCoord);
              cells.add(newCell);
              numBlackTiles++;
            } else if ((q == 1 && r == -1 && s == 0) || (q == 0 && r == 1 && s == -1) || (q == -1
                && r == 0 && s == 1) || (q == 0 && s == 0 && r == 0)) {
              GameCell newCell = new GameCell(new GameDisc(DiscColor.WHITE), currentCoord);
              cells.add(newCell);
              numWhiteTiles++;
            } else {
              cells.add(new GameCell(new GameDisc(DiscColor.GREY), currentCoord));
            }
          }
        }
      }
    }
  }

  /**
   * Checks to see if a series of discs can be flipped. If they can then it returns a list of discs
   * which should flip if the move is made, which does not include the target cell.
   *
   * @param currentColor the color of the disc you want placed
   * @param line         a line of discs that you want checked to see if there is a move possible in
   *                     them.
   * @return discs which can be flipped if the move is legal
   */
  private ArrayList<Disc> getInLineFlipsPossible(List<HexagonCell> line, DiscColor currentColor) {
    //DiscColor currentColor = getCurrentTurn();
    ArrayList<Disc> toFlip = new ArrayList<>();
    ArrayList<Disc> current = new ArrayList<>();
    int count = 0;
    for (HexagonCell cell : line) {
      Disc disc = cell.cellContents();
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
   * @param currentColor the color of the disc you want placed
   * @param targetCell   the cell you want to place on the board.
   * @return the surrounding cells which will flip if the targetCell was placed.
   */
  private ArrayList<Disc> getAllFlips(HexagonCell targetCell, DiscColor currentColor) {
    ArrayList<Disc> toFlip = new ArrayList<>();
    //Check Horizontal
    toFlip.addAll(
        getInLineFlipsPossible(getAllHexInDirection(targetCell, Direction.TOP_LEFT), currentColor));
    toFlip.addAll(getInLineFlipsPossible(getAllHexInDirection(targetCell, Direction.BOTTOM_RIGHT),
        currentColor));

    //Check Right diagonal
    toFlip.addAll(getInLineFlipsPossible(getAllHexInDirection(targetCell, Direction.DEAD_LEFT),
        currentColor));
    toFlip.addAll(getInLineFlipsPossible(getAllHexInDirection(targetCell, Direction.DEAD_RIGHT),
        currentColor));

    //Check Left diagonal
    toFlip.addAll(getInLineFlipsPossible(getAllHexInDirection(targetCell, Direction.TOP_RIGHT),
        currentColor));
    toFlip.addAll(getInLineFlipsPossible(getAllHexInDirection(targetCell, Direction.BOTTOM_LEFT),
        currentColor));
    return toFlip;
  }

  private List<HexagonCell> getAllHexInDirection(HexagonCell targetCell, Direction direction) {
    ArrayList<HexagonCell> returnList = new ArrayList<>();
    returnList.add(targetCell);
    HexagonCell currentCell;
    try {
      currentCell = getHexAt(targetCell.getCellNeighbor(direction));
    } catch (IllegalArgumentException e) {
      return returnList;
    }

    while (currentCell.cellContents().getColor() != DiscColor.GREY) {
      returnList.add(currentCell);
      try {
        currentCell = getHexAt(currentCell.getCellNeighbor(direction));
      } catch (IllegalArgumentException e) {
        break;
      }
    }
    return returnList;
  }


  @Override
  public void placeDisc(Coordinate coord) {
    checkGameStarted();
    int q = coord.getQ();
    int r = coord.getR();
    int s = coord.getS();

    if (!checkLegalMove(q, r, s)) {
      throw new IllegalStateException("Illegal move when inputting " + q + ", " + r + ", " + s);
    }
    ArrayList<Disc> flipDiscs = getAllFlips(getHexAt(q, r, s), getCurrentTurn());
    for (Disc disc : flipDiscs) {
      disc.flipDisc();
    }
    if (blacksTurn) {
      numWhiteTiles -= flipDiscs.size();
      numBlackTiles += flipDiscs.size() + 1;
    } else {
      numWhiteTiles += flipDiscs.size() + 1;
      numBlackTiles -= flipDiscs.size();
    }
    blacksTurn = !blacksTurn;
  }

  /**
   * Skip the turn of the current player.
   *
   * @throws IllegalStateException if game isn't started.
   */
  @Override
  public void skipCurrentTurn() {
    checkGameStarted();
    blacksTurn = !blacksTurn;
  }

  private Disc getDiscAt(int q, int r, int s) {
    checkGameStarted();
    checkValidCoordinates(q, r, s);
    return getHexAt(q, r, s).cellContents();
  }

  private HexagonCell getHexAt(int q, int r, int s) {
    checkGameStarted();
    checkValidCoordinates(q, r, s);
    for (HexagonCell cell : cells) {
      if (cell.getCoordinateQ() == q && cell.getCoordinateR() == r && cell.getCoordinateS() == s) {
        return cell;
      }
    }
    throw new NoSuchElementException("Could not find cell at: " + q + ", " + r + ", " + s);
  }

  /**
   * Gets the coordinates of a game cell on the board using a empty game cell that is usually
   * returned by getCellNeighbor.
   *
   * @param cell the cell containing the coordinates you want to find.
   * @return a GameCell that is in the MutableReversi board.
   */
  private HexagonCell getHexAt(HexagonCell cell) {
    int q = cell.getCoordinateQ();
    int r = cell.getCoordinateR();
    int s = cell.getCoordinateS();
    checkValidCoordinates(q, r, s);
    return getHexAt(q, r, s);
  }

  /**
   * returns the disc color at the specified coordinate.
   *
   * @param coordinate the coordinates for what color you want to get.
   * @return disc at specified location.
   * @throws IllegalStateException    if game hasn't started.
   * @throws IllegalArgumentException coordinates are illegal.
   */
  @Override
  public DiscColor getColorAt(Coordinate coordinate) {
    int q = coordinate.getQ();
    int r = coordinate.getR();
    int s = coordinate.getS();

    checkGameStarted();
    checkValidCoordinates(q, r, s);
    return getDiscAt(q, r, s).getColor();
  }

  /**
   * Returns the color of whos turn it is. This is denoted by DiscColor.
   *
   * @return a DiscColor representing who's turn it currently is.
   * @throws IllegalStateException if game hasn't started.
   */
  @Override
  public DiscColor getCurrentTurn() {
    checkGameStarted();
    if (blacksTurn) {
      return DiscColor.BLACK;
    } else {
      return DiscColor.WHITE;
    }
  }

  /**
   * Gets the total height of the board.
   *
   * @return the total height of the board including empty cells.
   * @throws IllegalStateException if game hasn't started
   */
  @Override
  public int getBoardSize() {
    checkGameStarted();
    return getBoardRadius() * 2 + 1;
  }

  /**
   * Gets the radius of the board from the center cell, not including it.
   *
   * @return the total width of the board including empty cells.
   * @throws IllegalStateException if game hasn't started.
   */
  @Override
  public int getBoardRadius() {
    checkGameStarted();
    return size;
  }

  /**
   * Checks if the game is over by seeing if all the cells are filled or there are new legal moves
   * left.
   *
   * @return true if the game is over and false there are legal moves to be played.
   * @throws IllegalStateException if game hasn't started.
   */
  @Override
  public boolean gameOver() {
    checkGameStarted();
    if (checkIfAllCellsFilled()) {
      return true;
    }
    //if any cell can be made black or white
    // and have moves then return false and set the color back to grey
    return !checkGivenPlayerHasLegalMoveLeft(blacksTurn) && !checkGivenPlayerHasLegalMoveLeft(
        !blacksTurn);
  }
}
