package model.squarereversi;

import java.util.Objects;
import model.Coordinate;

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


  /**
   * Represents the row.
   *
   * @return the row int.
   */
  @Override
  public int getIntR() {
    return row;
  }

  /**
   * Represents the column.
   *
   * @return the column int.
   */
  @Override
  public int getIntQ() {
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
    if (!(a instanceof SquareCoordinate other)) {
      return false;
    }
    return ((Math.abs(this.row - other.getIntR()) < 0.01) && (Math.abs(this.col - other.getIntQ())
        < 0.01));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.row, this.col);
  }
}
