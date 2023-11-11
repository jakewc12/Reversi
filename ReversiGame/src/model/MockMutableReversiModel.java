package model;

public class MockMutableReversiModel extends MutableReversi {

  private Appendable out;

  public MockMutableReversiModel(int size, Appendable out) {
    super(size);
    this.out = out;
  }

  private void append(String thing) {
    try {
      out.append(thing);
    } catch (Exception e) {
      System.out.println("connection with appendable failed.");
    }
  }

  @Override
  public void skipCurrentTurn() {
    append("Turn skipped");
  }

  @Override
  public void placeDisc(Coordinate coordinate) {
    append("placed disc at: " + coordinate);
  }


  @Override
  public boolean isLegalMove(Coordinate coordinate) {
    append("Place disc called at " + coordinate);
    return super.isLegalMove(coordinate);
  }
}