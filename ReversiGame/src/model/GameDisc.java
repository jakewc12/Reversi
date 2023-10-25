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
  public void flipColor(DiscColor color) {
    this.color = color;
  }

  @Override
  public DiscColor getColor() {
    return this.color;
  }

  @Override
  public String toString() {
    return this.color.asString;
  }

  /**
   * A class meant to represent Reversi Disc colors. Player colors are white and black and no disc
   * placed is grey.
   */
  public enum DiscColor {
    BLACK("BLACK"), WHITE("WHITE"), GREY("GREY");
    public final String asString;

    DiscColor(String asString) {
      this.asString = asString;
    }
  }
}
