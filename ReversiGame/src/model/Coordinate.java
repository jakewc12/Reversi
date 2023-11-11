package model;

import java.util.Objects;

/**
 * This class represents a position system of (Q,R,S)
 */
public class Coordinate {

  private final int q;
  private final int r;
  private final int s;

  /**
   * Initialize this object to the specified position
   */
  public Coordinate(int q, int r, int s) {
    this.q = q;
    this.r = r;
    this.s = s;
  }

  public int getQ() {
    return q;
  }

  public int getR() {
    return r;
  }

  public int getS() {
    return s;
  }

  @Override
  public String toString() {
    return "(" + q + ", " + r + ", " + s +")";
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Coordinate that)) {
      return false;
    }

    return ((Math.abs(this.q - that.q) < 0.01) && (Math.abs(this.r - that.r) < 0.01) &&
            Math.abs(this.s - that.s) < 0.01);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.q, this.r, this.s);
  }
}
