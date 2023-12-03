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
import model.ModelStatus;
import model.MutableReversi;

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
    if (super.getColorAt(new HexPosToCoordinate(c)).equals(DiscColor.BLACK)) {
      return Optional.of(PlayerPiece.PLAYER_TWO);
    } else if (super.getColorAt(new HexPosToCoordinate(c)).equals(DiscColor.WHITE)) {
      return Optional.of(PlayerPiece.PLAYER_ONE);
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
    scores.put(PlayerPiece.PLAYER_TWO, super.checkScoreOfPlayer(DiscColor.BLACK));
    scores.put(PlayerPiece.PLAYER_ONE, super.checkScoreOfPlayer(DiscColor.WHITE));
    return scores;
  }

  @Override
  public PlayerPiece getCurrentPlayer() {
    if (super.getCurrentTurn() == DiscColor.BLACK) {
      return PlayerPiece.PLAYER_TWO;
    }
    return PlayerPiece.PLAYER_ONE;
  }

  @Override
  public List<PlayerPiece> getWinningPlayers() {
    List<PlayerPiece> winning = new ArrayList<>();
    if (super.checkScoreOfPlayer(DiscColor.BLACK) > super.checkScoreOfPlayer(DiscColor.WHITE)) {
      winning.add(PlayerPiece.PLAYER_TWO);
      return winning;
    } else if (super.checkScoreOfPlayer(DiscColor.WHITE) > super.checkScoreOfPlayer(DiscColor.BLACK)) {
      winning.add(PlayerPiece.PLAYER_ONE);
      return winning;
    }
    winning.add(PlayerPiece.PLAYER_TWO);
    winning.add(PlayerPiece.PLAYER_ONE);
    return winning;
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
    super.addFeaturesInterface((ModelStatus) listener);
  }
}