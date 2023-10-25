package view;

import java.io.IOException;
import model.ReadOnlyReversiModel;

/**
 * A simple text-based rendering of the Reversi game.
 */
public class ReversiTextualView implements TextualView {

  private final ReadOnlyReversiModel model;

  private Appendable output;

  public ReversiTextualView(ReadOnlyReversiModel model) {
    this.model = model;
  }

  public ReversiTextualView(ReadOnlyReversiModel model, Appendable thing) {
    this.model = model;
    output = thing;
  }

  @Override
  public String toString() {
    return "Not Implemented";
  }

  @Override
  public void render() {
    try {
      output.append(this.toString());
    } catch (IOException e) {
      throw new IllegalStateException("Could not render textview.");
    }

  }
}
