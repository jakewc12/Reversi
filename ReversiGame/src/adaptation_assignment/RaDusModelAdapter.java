package adaptation_assignment;

import DustinRaymondReversi.controller.ReversiModelFeatures;
import DustinRaymondReversi.model.HexPosn;
import DustinRaymondReversi.model.PlayerPiece;
import DustinRaymondReversi.model.ReversiModel;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    return false;
  }

  @Override
  public boolean playerHasLegalMove() {
    return false;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public List<HexPosn> getAllValidCoordinates() {
    return null;
  }

  @Override
  public Optional<PlayerPiece> getPlayerAt(HexPosn c) throws IllegalArgumentException {
    return Optional.empty();
  }

  @Override
  public int getSideLength() {
    return 0;
  }

  @Override
  public Map<PlayerPiece, Integer> getScores() {
    return null;
  }

  @Override
  public PlayerPiece getCurrentPlayer() {
    return null;
  }

  @Override
  public List<PlayerPiece> getWinningPlayers() {
    return null;
  }

  @Override
  public void move(HexPosn c) throws IllegalArgumentException, IllegalStateException {

  }

  @Override
  public void passTurn() throws IllegalStateException {

  }

  @Override
  public void addModelListener(ReversiModelFeatures listener) {

  }
}
