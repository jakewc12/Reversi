package adaptionassignment;

import dustinraymondreversi.model.HexPosn;
import java.util.Objects;
import model.Coordinate;

/**
 * Adapts our client's HexPos class to our Coordinate class.
 */
public class HexPosToLogicalCoordinate extends Coordinate {

  private final HexPosn pos;

  /**
   * Creates a HexPosToLogicalCoordiante, which extends Coordinate.
   * @param pos the HexPos to be adapted.
   */
  public HexPosToLogicalCoordinate(HexPosn pos) {
    super(pos.getQ(), pos.getR(), (-pos.getQ() - pos.getR()));
    Objects.requireNonNull(pos);
    this.pos = pos;
  }

  @Override
  public int getIntQ() {
    return this.pos.getQ();
  }

  @Override
  public int getIntR() {
    return this.pos.getR();
  }

  @Override
  public int getIntS() {
    return (-this.getIntQ() - this.getIntR());
  }
}
