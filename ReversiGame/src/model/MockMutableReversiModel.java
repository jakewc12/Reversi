package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MockMutableReversiModel implements MutableReversiModel {
  private Appendable out;
  private int size;
  private List<HexagonCell> cells = new ArrayList<>();

  public MockMutableReversiModel(int size, Appendable out) {
    this.out = out;
    this.size = size;
  }

  @Override
  public void placeDisc(int q, int r, int s) {
    cells.add(new GameCell(new GameDisc(DiscColor.GREY),q,r,s));
  }

  @Override
  public void skipCurrentTurn() {
    try {
      out.append("turn skipped");
    } catch (Exception e) {
      System.out.println("connection with appendable failed.");
    }
  }

  @Override
  public DiscColor getColorAt(int q, int r, int s) {
    for (HexagonCell cell : cells) {
      if (cell.getCoordinateQ() == q && cell.getCoordinateR() == r && cell.getCoordinateS() == s) {
        return cell.cellContents().getColor();
      }
    }
    return null;
  }

  @Override
  public DiscColor getCurrentTurn() {
    return null;
  }

  @Override
  public int getBoardSize() {
    return 0;
  }

  @Override
  public int getBoardRadius() {
    return size;
  }

  @Override
  public boolean gameOver() {
    return false;
  }

  @Override
  public void startGame(List<HexagonCell> board) {

  }

  @Override
  public List<HexagonCell> getBoard() {
    return null;
  }

  @Override
  public boolean checkLegalMove(int q, int r, int s) {
    if(getColorAt(q,r,s)!=null){
      return false;
    }
    try {
      out.append("Place disc called at (" + q + ", " + r + ", " + s + ")");
    } catch (Exception e) {
      System.out.println("connection with appendable failed.");
    }

    return true;
  }

  @Override
  public boolean checkCurrentPlayerHasLegalMovesLeft() {
    return false;
  }

  @Override
  public int checkScoreOfPlayer(DiscColor color) {
    return 0;
  }
}
