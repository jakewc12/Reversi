package textualview;

import java.io.IOException;
import model.ReadOnlyReversiModel;
import model.squarereversi.SquareCoordinate;

/**
 * A simple text-based rendering of the Reversi game. This will render Black as X and white as O.
 */
public class SquareReversiTextualView implements TextualView {

  private final ReadOnlyReversiModel model;

  private Appendable output;

  /**
   * creates a HexReversiTextualView.
   *
   * @param model is the ReadOnlyReversiModel which it generates text based on.
   */

  public SquareReversiTextualView(ReadOnlyReversiModel model) {
    this.model = model;
  }

  /**
   * Creates a HexReversiTextualView with an appendable.
   *
   * @param model is the ReadOnlyReversiModel which it generates text based on.
   * @param r     is an appendable which allows for output.
   */

  public SquareReversiTextualView(ReadOnlyReversiModel model, Appendable r) {
    this.model = model;
    this.output = r;
  }

  /**
   * Renders a text-based model of the model with piles of foundation, draw, and cascade. Grey discs
   * are represented with _, White discs are represented with O, and Black discs are. represented
   * with X.
   *
   * @return the formatted model of the game.
   */
  @Override
  public String toString() {

    int boardLength = model.getBoardSize();
    StringBuilder returnString = new StringBuilder();

    for (int row = 0; row < boardLength; row++) {
      //Append the start with spaces
      for (int col = 0; col < boardLength; col++) {
        //If its black place an X, if white place an O, otherwise do underscore.
        switch (model.getColorAt(new SquareCoordinate(col, row))) {
          case BLACK:
            returnString.append("X");
            break;
          case WHITE:
            returnString.append("O");
            break;
          default:
            returnString.append("_");
        }
      }
      // add a new line at then end of the current line.
      returnString.append("\n");
    }
    return returnString.toString();
  }

  /**
   * Renders a model in some manner (e.g. as text, or as graphics, etc.).
   */
  @Override
  public void render() {
    try {
      output.append(this.toString());
    } catch (IOException e) {
      throw new IllegalStateException("Could not render textview.");
    }

  }
}
