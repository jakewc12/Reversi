package model;

import java.util.Objects;

/**
 * This class represents a position system of (Q,R,S).
 */
public class Coordinate {

  private final int intQ;
  private final int intR;
  private final int intS;

  /**
   * Creates a cube coordinate of position q,r,s.
   */
  public Coordinate(int q, int r, int s) {
    this.intQ = q;
    this.intR = r;
    this.intS = s;
  }

  public int getIntQ() {
    return intQ;
  }

  public int getIntR() {
    return intR;
  }

  public int getIntS() {
    return intS;
  }

  @Override
  public String toString() {
    return "(" + intQ + ", " + intR + ", " + intS + ")";
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Coordinate)) {
      return false;
    }
    Coordinate other = (Coordinate) a;
    return ((Math.abs(this.intQ - other.intQ) < 0.01) && (Math.abs(this.intR - other.intR) < 0.01)
        && Math.abs(this.intS - other.intS) < 0.01);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.intQ, this.intR, this.intS);
  }
}
