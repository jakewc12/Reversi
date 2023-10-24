package model;

public class GameDisc implements Disc {
  private final DiscColor color;


  public GameDisc(DiscColor color){
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
    BLACK("BLACK"), WHITE("WHITE");
    public final String asString;

    DiscColor(String color) {
      this.asString = color;
    }
  }
}
