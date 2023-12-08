package model.hexreversi;

import java.util.Objects;

/**
 * This class represents a position system of (Q,R,S).
 */
public class HexCoordinate implements LogicalHexCoordinate {

  private final int intQ;
  private final int intR;
  private final int intS;

  /**
   * Creates a cube coordinate of position q,r,s.
   */
  public HexCoordinate(int q, int r, int s) {
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
    if (!(a instanceof LogicalHexCoordinate)) {
      return false;
    }
    HexCoordinate other = (HexCoordinate) a;
    return ((Math.abs(this.intQ - other.getIntQ()) < 0.01) && (Math.abs(this.intR - other.getIntR())
        < 0.01)
        && Math.abs(this.intS - other.getIntS()) < 0.01);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.intQ, this.intR, this.intS);
  }
}