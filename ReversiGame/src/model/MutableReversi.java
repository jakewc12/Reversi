package model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import model.hexreversi.HexDirections;
import model.hexreversi.LogicalHexCoordinate;

/**
 * The abstract class {@code MutableReversi} serves as the foundation for implementing mutable
 * Reversi game models. It provides methods for setting up and playing Reversi games, handling
 * player turns, checking legal moves, and more.
 */
public abstract class MutableReversi implements MutableReversiModel {

  //width is how far from the center cell every edge cell is
  private final int size;
  private final List<ModelStatus> features = new ArrayList<>();
  //true if it's player BLACK's turn, false if it's WHITE's turn
  private boolean blacksTurn;
  private boolean gameStarted;
  private int timesPlayersPassedConsecutively;
  private List<GameCell> cells = new ArrayList<>();

  /**
   * Creates a MutableHexReversi and sets all game values to zero until startGame is called.
   *
   * @param size is the intended radius of the game, in relation to the center cell.
   * @invariant a game cannot be played unless gameStarted is true.
   * @invariant size cannot be less than 1.
   */
  public MutableReversi(int size) {
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
  public boolean isLegalMove(Coordinate coord) {
    checkValidCoordinates(coord);
    if (this.getDiscAt(coord).getColor() != DiscColor.GREY) {
      return false;
    }
    if (getAllFlips(getHexAt(coord), getCurrentTurn()).isEmpty()) {
      this.getDiscAt(coord).changeColorTo(DiscColor.GREY);
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
    for (Coordinate logicalCoordinate : getAllCoordinates()) {
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

  protected void checkValidCoordinates(Coordinate genericCoord) {
    LogicalHexCoordinate coord = (LogicalHexCoordinate) genericCoord;
    int total = coord.getIntQ() + coord.getIntS() + coord.getIntR();
    if (Math.abs(coord.getIntQ()) > size || Math.abs(coord.getIntR()) > size
        || Math.abs(coord.getIntS()) > size || ((total) != 0)) {
      throw new IllegalArgumentException(
          "Invalid coordinates given for size. Max coordinate size is: " + size + "\n coordinates "
              + "were " + coord);
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
  protected ArrayList<Disc> getInLineFlipsPossible(List<GameCell> line, DiscColor currentColor) {
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
  public ArrayList<Disc> getAllFlips(GameCell targetCell, DiscColor currentColor) {
    ArrayList<Disc> toFlip = new ArrayList<>();
    if (targetCell.cellContents().getColor() != DiscColor.GREY) {
      return toFlip;
    }
    //Check Horizontal
    toFlip.addAll(getInLineFlipsPossible(getAllCellInDirection(targetCell, HexDirections.TOP_LEFT),
        currentColor));
    toFlip.addAll(
        getInLineFlipsPossible(getAllCellInDirection(targetCell, HexDirections.BOTTOM_RIGHT),
            currentColor));

    //Check Right diagonal
    toFlip.addAll(getInLineFlipsPossible(getAllCellInDirection(targetCell, HexDirections.DEAD_LEFT),
        currentColor));
    toFlip.addAll(
        getInLineFlipsPossible(getAllCellInDirection(targetCell, HexDirections.DEAD_RIGHT),
            currentColor));

    //Check Left diagonal
    toFlip.addAll(getInLineFlipsPossible(getAllCellInDirection(targetCell, HexDirections.TOP_RIGHT),
        currentColor));
    toFlip.addAll(
        getInLineFlipsPossible(getAllCellInDirection(targetCell, HexDirections.BOTTOM_LEFT),
            currentColor));

    return toFlip;
  }

  @Override
  public int getNumFlipsOnMove(Coordinate logicalCoordinate, DiscColor playerColor) {
    checkGameStarted();
    GameCell targetCell = getHexAt(logicalCoordinate);
    return getAllFlips(targetCell, playerColor).size();
  }

  @Override
  public List<Coordinate> getAllCoordinates() {
    //checkGameStarted();
    List<Coordinate> returnList = new ArrayList<>();
    for (GameCell cell : cells) {
      returnList.add(cell.getCoordinate());
    }
    return returnList;
  }

  protected List<GameCell> getAllCellInDirection(GameCell targetCell, Direction hexDirections) {
    ArrayList<GameCell> returnList = new ArrayList<>();
    returnList.add(targetCell);
    GameCell currentCell;
    try {
      currentCell = getHexAt(targetCell.getCellNeighbor(hexDirections));
    } catch (Exception e) {
      return returnList;
    }

    while (currentCell.cellContents().getColor() != DiscColor.GREY) {
      returnList.add(currentCell);
      try {
        currentCell = getHexAt(currentCell.getCellNeighbor(hexDirections));
      } catch (Exception e) {
        break;
      }
    }
    return returnList;
  }


  @Override
  public void placeDisc(Coordinate coord) {
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
    timesPlayersPassedConsecutively = 0;
  }

  @Override
  public void skipCurrentTurn() {
    checkGameStarted();
    blacksTurn = !blacksTurn;
    updateFeaturesInterface();
    timesPlayersPassedConsecutively++;
  }

  private Disc getDiscAt(Coordinate coord) {
    checkGameStarted();
    checkValidCoordinates(coord);
    return getHexAt(coord).cellContents();
  }

  private GameCell getHexAt(Coordinate coord) {
    checkGameStarted();
    checkValidCoordinates(coord);

    for (GameCell cell : cells) {
      if (coord.equals(cell.getCoordinate())) {
        return cell;
      }
    }
    throw new NoSuchElementException("Could not find cell at: " + coord);
  }


  @Override
  public DiscColor getColorAt(Coordinate logicalCoordinate) {
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
    return getBoardRadius() * 2 + 1;
  }


  @Override
  public int getBoardRadius() {
    return size;
  }


  @Override
  public boolean gameOver() {
    checkGameStarted();
    if (timesPlayersPassedConsecutively == 4) {
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
