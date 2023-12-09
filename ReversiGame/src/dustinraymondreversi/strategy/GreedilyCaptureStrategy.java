package dustinraymondreversi.strategy;

import dustinraymondreversi.model.HexPosn;
import dustinraymondreversi.model.ReadOnlyReversiModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Reversi strategy that chooses the move that captures the greatest number of opponent
 * pieces out of all the possible moves. This strategy does not consider the opponent's possible
 * responses.
 */
public class GreedilyCaptureStrategy implements ReversiStrategy {

  @Override
  public List<HexPosn> chooseMoves(ReadOnlyReversiModel game) {
    if (game == null) {
      throw new IllegalArgumentException("Given game cannot be null.");
    }

    List<HexPosn> chosenPosns = new ArrayList<>();
    int chosenScoreGained = 0;

    // iterates through every possible position in the board to play at and
    // checks how many pieces are captured by playing at that move; if the move
    // is not legal, the number of piece captured is 0
    for (HexPosn pos : game.getAllValidCoordinates()) {
      int scoreGained = this.numPiecesCapturedAt(game, pos);
      if (scoreGained == 0) {
        continue;
      }
      //it thinks that it is the wrong player.
      if (!game.isLegalMoveAt(pos)) {
        continue;
      }
      if (scoreGained > chosenScoreGained) {
        chosenScoreGained = scoreGained;

        //clears the list of best positions and adds this position to the list
        chosenPosns.clear();
        chosenPosns.add(pos);
      } else if (scoreGained == chosenScoreGained) {
        //adds this position to the list of chosen positions.
        chosenPosns.add(pos);
      }
    }

    // NOTE: if the chosen score is greater than 0, this means that a valid
    // position in the board has been chosen, so chosenPos will not be null
    // in this case
    return chosenPosns;
  }

  /**
   * Returns the number of pieces that would be captured by attempting to play at the given position
   * in the given game. If the attempted move is illegal, the number of pieces that would be
   * captured is 0. This method assumes that the given position belongs to the game board.
   *
   * @param game the game to attempt to play a move in
   * @param pos  the position at which to attempt to play a move
   * @return the number of potential captures
   */
  private int numPiecesCapturedAt(ReadOnlyReversiModel game, HexPosn pos) {
    // if there is already a piece at the position, no move can be played, so no pieces
    // can be captured
    if (game.getPlayerAt(pos).isPresent()) {
      return 0;
    }
    List<HexPosn> board = game.getAllValidCoordinates();
    int capturedPieceCount = 0;

    // checks for captures in each of the six directions
    for (HexPosn.NeighborDirection direction : HexPosn.NeighborDirection.values()) {
      HexPosn curPos = pos.getNeighbor(direction);
      int numCapturedInDirection = 0;

      // while the next piece in that direction is an enemy piece
      boolean hitOwnPiece = false;
      while (board.contains(curPos) && game.getPlayerAt(curPos).isPresent()) {
        // we break out of the loop if we hit our own piece, because that signifies a
        // valid capture; all the other possible exit conditions result in an invalid
        // capture attempt in that direction
        if (game.getPlayerAt(curPos).get().equals(game.getCurrentPlayer())) {
          hitOwnPiece = true;
          break;
        }
        numCapturedInDirection += 1;
        curPos = curPos.getNeighbor(direction);
      }

      // if a valid capture has been made in the chosen direction, add the number of pieces
      // that was just captured to the total casualty count
      if (hitOwnPiece) {
        capturedPieceCount += numCapturedInDirection;
      }
    }

    return capturedPieceCount;
  }
}
