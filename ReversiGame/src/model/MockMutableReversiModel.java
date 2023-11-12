package model;

import java.util.ArrayList;
import java.util.List;

public class MockMutableReversiModel extends MutableReversi {

  private Appendable out;
  List<HexagonCell> cells = new ArrayList<>();

  public MockMutableReversiModel(int size, Appendable out) {
    super(size);
    this.out = out;
  }

  @Override
  public void startGame(List<HexagonCell> board) {
    super.startGame(board);
    this.cells = board;
  }

  private void append(String thing) {
    try {
      out.append(thing).append("\n");
    } catch (Exception e) {
      System.out.println("connection with appendable failed.");
    }
  }

  @Override
  public void skipCurrentTurn() {
    append("Turn skipped");
  }


  @Override
  public boolean isLegalMove(Coordinate coordinate) {
    for (HexagonCell cell : cells) {
      if(cell.getCoordinate().equals(coordinate) && cell.cellContents().getColor()!= DiscColor.GREY){
        throw new IllegalStateException(" move not legal ");
      }
    }
    return true;
  }

  @Override
  public void placeDisc(Coordinate coordinate) {
    append("Place disc called at " + coordinate);
    for (HexagonCell cell : cells) {
      if (cell.getCoordinate().equals(coordinate)) {
        cell.cellContents().changeColorTo(DiscColor.BLACK);
      }
    }
  }
}