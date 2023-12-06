package adaptionassignment;

import dustinraymondreversi.controller.ReversiModelFeatures;
import dustinraymondreversi.model.HexPosn;
import dustinraymondreversi.model.PlayerPiece;
import dustinraymondreversi.model.ReversiModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import model.DiscColor;
import model.GameCell;
import model.HexagonCell;
import model.LogicalCoordinate;
import model.ModelStatus;
import model.MutableReversi;
import model.ReadOnlyReversiModel;

/**
 * Adapts our client's model interface to our model interface.
 */
public class RaDusModelAdapter extends MutableReversi implements ReversiModel {


  /**
   * Creates a MutableReversi and sets all game values to zero until startGame is called.
   *
   * @param size is the intended radius of the game, in relation to the center cell.
   * @invariant a game cannot be played unless gameStarted is true.
   * @invariant size cannot be less than 1.
   */
  public RaDusModelAdapter(int size) {
    super(size);
  }

  /**
   * Sets up the model given an already existing model.
   *
   * @param copyModel the model which will be copied. This allows for us to copy game states.
   */
  public void setUpGame(ReadOnlyReversiModel copyModel) {
    List<HexagonCell> board = new ArrayList<>();

    for (LogicalCoordinate coord : copyModel.getAllCoordinates()) {
      board.add(new GameCell(copyModel.getColorAt(coord), coord));
    }
    super.resetBoard(board);
  }

  @Override
  public boolean isLegalMoveAt(HexPosn pos) {
    return super.getNumFlipsOnMove(new HexPosToLogicalCoordinate(pos), super.getCurrentTurn()) > 0;
  }

  @Override
  public boolean playerHasLegalMove() {
    return super.checkCurrentPlayerHasLegalMovesLeft();
  }

  @Override
  public boolean isGameOver() {
    return super.gameOver();
  }

  @Override
  public List<HexPosn> getAllValidCoordinates() {
    List<HexPosn> returnList = new ArrayList<>();
    List<LogicalCoordinate> allCoords = super.getAllCoordinates();
    for (LogicalCoordinate coord : allCoords) {
      returnList.add(new HexPosn(coord.getIntQ(), coord.getIntR()));
    }
    return returnList;
  }

  @Override
  public Optional<PlayerPiece> getPlayerAt(HexPosn c) throws IllegalArgumentException {
    if (super.getColorAt(new HexPosToLogicalCoordinate(c)).equals(DiscColor.WHITE)) {
      return Optional.of(PlayerPiece.PLAYER_ONE);
    } else if (super.getColorAt(new HexPosToLogicalCoordinate(c)).equals(DiscColor.BLACK)) {
      return Optional.of(PlayerPiece.PLAYER_TWO);
    }
    return Optional.empty();
  }

  @Override
  public int getSideLength() {
    return super.getBoardRadius() + 1;
  }

  @Override
  public Map<PlayerPiece, Integer> getScores() {
    Map<PlayerPiece, Integer> scores = new HashMap<>();
    scores.put(PlayerPiece.PLAYER_ONE, super.checkScoreOfPlayer(DiscColor.WHITE));
    scores.put(PlayerPiece.PLAYER_TWO, super.checkScoreOfPlayer(DiscColor.BLACK));
    return scores;
  }

  @Override
  public PlayerPiece getCurrentPlayer() {
    //for some reason this is always player two/black. need to fix
    if (super.getCurrentTurn() == DiscColor.WHITE) {
      return PlayerPiece.PLAYER_ONE;
    }
    return PlayerPiece.PLAYER_TWO;
  }

  @Override
  public List<PlayerPiece> getWinningPlayers() {
    return null;
  }

  @Override
  public void move(HexPosn c) throws IllegalArgumentException, IllegalStateException {
    super.placeDisc(new HexPosToLogicalCoordinate(c));
  }

  @Override
  public void passTurn() throws IllegalStateException {
    super.skipCurrentTurn();
  }

  @Override
  public void addModelListener(ReversiModelFeatures listener) {
    super.addFeaturesInterface((ModelStatus) listener);
  }
}
