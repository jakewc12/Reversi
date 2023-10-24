package model;

/**
 * A reversi disc that is either Black or white if a player has placed a piece. Grey if a player
 * has not placed a piece.
 */
public class GameDisc implements Disc {

  private final DiscColor color;


  public GameDisc(DiscColor color) {
    this.color = color;
  }

  @Override
  public void flipColor() {

  }

  @Override
  public DiscColor getColor() {
    return this.color;
  }

  @Override
  public String toString() {
    return this.color.asString;
  }

  public enum DiscColor {
    BLACK("BLACK"), WHITE("WHITE"), GREY("GREY"), HIGHLIGHTED("HIGHLIGHTED");
    public final String asString;

    DiscColor(String asString) {
      this.asString = asString;
    }
  }
}
