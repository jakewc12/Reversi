package model;

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
