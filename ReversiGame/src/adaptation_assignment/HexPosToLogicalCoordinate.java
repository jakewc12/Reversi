package adaptation_assignment;

import java.util.Objects;

import DustinRaymondReversi.model.HexPosn;
import model.Coordinate;
import model.LogicalCoordinate;

public class HexPosToLogicalCoordinate extends Coordinate {
  private HexPosn pos;

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
