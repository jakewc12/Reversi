package adaptation_assignment;

import DustinRaymondReversi.controller.ReversiModelFeatures;
import DustinRaymondReversi.model.HexPosn;
import DustinRaymondReversi.model.PlayerPiece;
import DustinRaymondReversi.model.ReversiModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import model.Coordinate;
import model.DiscColor;
import model.GameCell;
import model.HexagonCell;
import model.MutableReversi;
import model.ReadOnlyReversiModel;

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

  public void setUpGame(ReadOnlyReversiModel copyModel) {
    List<HexagonCell> board = new ArrayList<>();

    for (Coordinate coord : copyModel.getAllCoordinates()) {
      board.add(new GameCell(copyModel.getColorAt(coord), coord));
    }
    super.setUpGame(board);
  }

  @Override
  public boolean isLegalMoveAt(HexPosn pos) {
    return super.isLegalMove(new HexPosToCoordinate(pos));
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
    List<Coordinate> allCoords = super.getAllCoordinates();
    for (Coordinate coord : allCoords) {
      returnList.add(new HexPosn(coord.getIntQ(), coord.getIntR()));
    }
    return returnList;
  }

  @Override
  public Optional<PlayerPiece> getPlayerAt(HexPosn c) throws IllegalArgumentException {
    return Optional.empty();
  }

  @Override
  public int getSideLength() {
    return super.getBoardRadius();
  }

  @Override
  public Map<PlayerPiece, Integer> getScores() {
    Map<PlayerPiece, Integer> scores = new HashMap<>();
    scores.put(PlayerPiece.PLAYER_ONE, super.checkScoreOfPlayer(DiscColor.BLACK));
    scores.put(PlayerPiece.PLAYER_TWO, super.checkScoreOfPlayer(DiscColor.WHITE));
    return scores;
  }

  @Override
  public PlayerPiece getCurrentPlayer() {
    if (super.getCurrentTurn() == DiscColor.BLACK) {
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
    super.placeDisc(new HexPosToCoordinate(c));
  }

  @Override
  public void passTurn() throws IllegalStateException {
    super.skipCurrentTurn();
  }

  @Override
  public void addModelListener(ReversiModelFeatures listener) {

  }
}
