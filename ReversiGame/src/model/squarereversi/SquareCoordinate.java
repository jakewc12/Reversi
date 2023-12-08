package model.squarereversi;

import java.util.Objects;
import model.hexreversi.Coordinate;

/**
 * This class represents a position system of (Q,R,S).
 */
public class SquareCoordinate implements Coordinate {

  private final int row;
  private final int col;


  public SquareCoordinate(int row, int col) {
    this.row = row;
    this.col = col;
  }


  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  @Override
  public String toString() {
    return "(" + row + ", " + col + ")";
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof SquareCoordinate)) {
      return false;
    }
    SquareCoordinate other = (SquareCoordinate) a;
    return ((Math.abs(this.row - other.getRow()) < 0.01) && (Math.abs(this.col - other.getCol())
        < 0.01));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.row, this.col);
  }
}
