package model;

public class GameCell implements HexagonCell{
  private GameDisc contents;
  private final int qCoordinate;
  private final int rCoordinate;
  private final int sCoordinate;

  public GameCell(GameDisc contents, int qCoordinate, int rCoordinate, int sCoordinate){

    this.contents = contents;
    this.qCoordinate = qCoordinate;
    this.rCoordinate = rCoordinate;
    this.sCoordinate = sCoordinate;
  }

  @Override
  public GameDisc cellContents() {
    return contents;
  }

  @Override
  public int getQ() {
    return qCoordinate;
  }

  @Override
  public int getR() {
    return rCoordinate;
  }

  @Override
  public int getS() {
    return sCoordinate;
  }
}
