package model.squarereversi;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import model.Disc;
import model.DiscColor;
import model.GameCell;
import model.ModelStatus;
import model.MutableReversiModel;
import model.hexreversi.HexDirections;
import model.hexreversi.HexCell;
import model.hexreversi.HexCoordinate;
import model.hexreversi.LogicalHexCoordinate;

/**
 * Meant to simulate the logic of a game of reversi. The game of reversi does not start until start
 * game is called with an adequate board size.
 */
public class MutableSquareReversi implements MutableReversiModel {

  //width is how far from the center cell every edge cell is
  private final int size;
  private final List<ModelStatus> features = new ArrayList<>();
  //true if it's player BLACK's turn, false if it's WHITE's turn
  private boolean blacksTurn;
  private boolean gameStarted;
  private int numPlayersPassedInARow;
  private List<GameCell> cells = new ArrayList<>();

  /**
   * Creates a MutableHexReversi and sets all game values to zero until startGame is called.
   *
   * @param size is the intended radius of the game, in relation to the center cell.
   * @invariant a game cannot be played unless gameStarted is true.
   * @invariant size cannot be less than 1.
   */
  public MutableSquareReversi(int size) {
    if (size <= 0) {
      throw new IllegalArgumentException("Invalid size given");
    }
    this.size = size;
  }

  @Override
  public void setUpGame(List<GameCell> board) {
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

  public void resetBoard(List<GameCell> board) {
    this.cells = board;
    gameStarted = true;
  }

  @Override
  public void startGame() {
    updateFeaturesInterface();
  }

  @Override
  public List<GameCell> getBoard() {
    List<GameCell> localCells = new ArrayList<>();
    for (int q = -size; q <= size; q++) {
      for (int r = -size; r <= size; r++) {
        for (int s = -size; s <= size; s++) {
          if (q + r + s == 0) {
            LogicalHexCoordinate currentCoord = new HexCoordinate(q, r, s);
            if ((q == 0 && r == -1 && s == 1) || (q == 1 && r == 0 && s == -1) || (q == -1 && r == 1
                    && s == 0)) {
              HexCell newCell = new HexCell(DiscColor.BLACK, currentCoord);
              localCells.add(newCell);
            } else if ((q == 1 && r == -1 && s == 0) || (q == 0 && r == 1 && s == -1) || (q == -1
                    && r == 0 && s == 1)) {
              HexCell newCell = new HexCell(DiscColor.WHITE, currentCoord);
              localCells.add(newCell);
            } else {
              localCells.add(new HexCell(DiscColor.GREY, currentCoord));
            }
          }
        }
      }
    }
    return localCells;
  }

  @Override
  public boolean isLegalMove(LogicalHexCoordinate logicalCoordinate) {
    checkValidCoordinates(logicalCoordinate);
    if (this.getDiscAt(logicalCoordinate).getColor() != DiscColor.GREY) {
      return false;
    }
    if (getAllFlips(getHexAt(logicalCoordinate), getCurrentTurn()).isEmpty()) {
      this.getDiscAt(logicalCoordinate).changeColorTo(DiscColor.GREY);
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
    int count = 0;
    for (LogicalHexCoordinate logicalCoordinate : getAllCoordinates()) {
      if (getColorAt(logicalCoordinate) == color) {
        count++;
      }
    }
    return count;
  }

  /**
   * Checks if the given player, either black or white, has a legal move left and returns true if
   * yes, else no.
   *
   * @param blacksTurn if it's black or whites turn.
   * @return if the given player has a legal move left.
   */
  private boolean checkGivenPlayerHasLegalMoveLeft(boolean blacksTurn) {
    for (GameCell cell : cells) {
      if (cell.cellContents().getColor().equals(DiscColor.GREY)) {
        if (blacksTurn) {
          if (!getAllFlips(cell, DiscColor.BLACK).isEmpty()) {
            return true;
          }

        } else {
          if (!getAllFlips(cell, DiscColor.WHITE).isEmpty()) {
            return true;
          }
        }
        cell.cellContents().changeColorTo(DiscColor.GREY);
      }
    }
    return false;
  }

  private void checkValidCoordinates(LogicalHexCoordinate coord) {
    int total = coord.getIntQ() + coord.getIntS() + coord.getIntR();
    if (Math.abs(coord.getIntQ()) > size || Math.abs(coord.getIntR()) > size
            || Math.abs(coord.getIntS()) > size || ((total) != 0)) {
      throw new IllegalArgumentException(
              "Invalid coordinates given for size. Max coordinate size is: " + size
                      + "\n coordinates " + "were " + coord);
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
    for (GameCell cell : cells) {
      if (cell.cellContents().getColor() == DiscColor.GREY) {
        return false;
      }
    }
    return true;
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
  private ArrayList<Disc> getInLineFlipsPossible(List<GameCell> line, DiscColor currentColor) {
    ArrayList<Disc> toFlip = new ArrayList<>();
    ArrayList<Disc> current = new ArrayList<>();

    for (GameCell gameCell : line) {
      Disc disc = gameCell.cellContents();
      if (disc.getColor() != currentColor && disc.getColor() != DiscColor.GREY) {
        current.add(disc);
      }
      if (disc.getColor() == DiscColor.GREY) {
        current.clear();

      }
      if (disc.getColor() == currentColor) {
        toFlip.addAll(current);
        return toFlip;
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
  private ArrayList<Disc> getAllFlips(GameCell targetCell, DiscColor currentColor) {
    ArrayList<Disc> toFlip = new ArrayList<>();
    if (targetCell.cellContents().getColor() != DiscColor.GREY) {
      return toFlip;
    }
    //Check Horizontal
    toFlip.addAll(
            getInLineFlipsPossible(getAllHexInDirection(targetCell, HexDirections.TOP_LEFT)
                    , currentColor));
    toFlip.addAll(getInLineFlipsPossible(getAllHexInDirection(targetCell, HexDirections.BOTTOM_RIGHT),
            currentColor));

    //Check Right diagonal
    toFlip.addAll(getInLineFlipsPossible(getAllHexInDirection(targetCell, HexDirections.DEAD_LEFT),
            currentColor));
    toFlip.addAll(getInLineFlipsPossible(getAllHexInDirection(targetCell, HexDirections.DEAD_RIGHT),
            currentColor));

    //Check Left diagonal
    toFlip.addAll(getInLineFlipsPossible(getAllHexInDirection(targetCell, HexDirections.TOP_RIGHT),
            currentColor));
    toFlip.addAll(getInLineFlipsPossible(getAllHexInDirection(targetCell, HexDirections.BOTTOM_LEFT),
            currentColor));

    return toFlip;
  }

  @Override
  public int getNumFlipsOnMove(LogicalHexCoordinate logicalCoordinate, DiscColor playerColor) {
    checkGameStarted();
    GameCell targetCell = getHexAt(logicalCoordinate);
    return getAllFlips(targetCell, playerColor).size();
  }

  @Override
  public List<LogicalHexCoordinate> getAllCoordinates() {
    //checkGameStarted();
    List<LogicalHexCoordinate> returnList = new ArrayList<>();
    for (GameCell cell : cells) {
      returnList.add((LogicalHexCoordinate) cell.getCoordinate());
    }
    return returnList;
  }

  private List<GameCell> getAllHexInDirection(GameCell targetCell, HexDirections hexDirections) {
    ArrayList<GameCell> returnList = new ArrayList<>();
    returnList.add(targetCell);
    GameCell currentCell;
    try {
      currentCell = getHexAt((LogicalHexCoordinate) targetCell.getCellNeighbor(hexDirections));
    } catch (Exception e) {
      return returnList;
    }

    while (currentCell.cellContents().getColor() != DiscColor.GREY) {
      returnList.add(currentCell);
      try {
        currentCell = getHexAt((LogicalHexCoordinate) currentCell.getCellNeighbor(hexDirections));
      } catch (Exception e) {
        break;
      }
    }
    return returnList;
  }


  @Override
  public void placeDisc(LogicalHexCoordinate coord) {
    checkGameStarted();
    if (!isLegalMove(coord)) {
      throw new IllegalStateException("Illegal move when inputting " + coord);
    }
    ArrayList<Disc> flipDiscs = getAllFlips(getHexAt(coord), getCurrentTurn());
    for (Disc disc : flipDiscs) {
      disc.flipDisc();
    }
    getDiscAt(coord).changeColorTo(getCurrentTurn());
    blacksTurn = !blacksTurn;
    updateFeaturesInterface();
    numPlayersPassedInARow = 0;
  }

  @Override
  public void skipCurrentTurn() {
    checkGameStarted();
    blacksTurn = !blacksTurn;
    updateFeaturesInterface();
    numPlayersPassedInARow++;
  }

  private Disc getDiscAt(LogicalHexCoordinate coord) {
    checkGameStarted();
    checkValidCoordinates(coord);
    return getHexAt(coord).cellContents();
  }

  private GameCell getHexAt(LogicalHexCoordinate coord) {
    checkGameStarted();
    checkValidCoordinates(coord);

    for (GameCell cell : cells) {
      if (cell.getCoordinate() == coord) {
        return cell;
      }
    }
    throw new NoSuchElementException("Could not find cell at: " + coord);
  }


  @Override
  public DiscColor getColorAt(LogicalHexCoordinate logicalCoordinate) {
    checkGameStarted();
    checkValidCoordinates(logicalCoordinate);
    return getDiscAt(logicalCoordinate).getColor();
  }


  @Override
  public DiscColor getCurrentTurn() {
    //checkGameStarted();
    if (blacksTurn) {
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
    if (numPlayersPassedInARow == 4) {
      return true;
    }
    if (checkIfAllCellsFilled()) {
      return true;
    }
    //if any cell can be made black or white and have moves then return false
    return !checkGivenPlayerHasLegalMoveLeft(blacksTurn) && !checkGivenPlayerHasLegalMoveLeft(
            !blacksTurn);
  }

  public void addFeaturesInterface(ModelStatus features) {
    this.features.add(features);
  }

  private void updateFeaturesInterface() {
    for (ModelStatus feature : features) {
      feature.moveWasPlayed();
    }
  }

}
