package model;

/**
 * A reversi disc that is either Black or white if a player has placed a piece. Grey if a player has
 * not placed a piece.
 */
public class GameDisc implements Disc {

  private DiscColor color;


  public GameDisc(DiscColor color) {
    this.color = color;
  }

  @Override
  public void changeColorTo(DiscColor color) {
    this.color = color;
  }

  @Override
  public void flipDisc() {
    if (color == DiscColor.BLACK) {
      color = DiscColor.WHITE;
    } else if (color == DiscColor.WHITE) {
      color = DiscColor.BLACK;
    } else {
      throw new IllegalArgumentException("Cannot flip a disc that is not black or white");
    }
  }

  @Override
  public DiscColor getColor() {
    return this.color;
  }

  @Override
  public String toString() {
    return this.color.asString;
  }
}
