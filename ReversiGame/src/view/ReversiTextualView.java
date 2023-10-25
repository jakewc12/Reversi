package view;

import java.io.IOException;
import model.ReadOnlyReversiModel;

/**
 * A simple text-based rendering of the Reversi game. This will render Black as X and white as O.
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

    int boardRadius = model.getBoardRadius();
    StringBuilder returnString = new StringBuilder();

    for (int q = -boardRadius; q < boardRadius; q++) {
      //Append the start with spaces
      for (int i = Math.abs(q); i < boardRadius - i; i++) {
        returnString.append(" ");
      }
      for (int r = -boardRadius; r < boardRadius; r++) {
        for (int s = -boardRadius; s < boardRadius; s++) {

          //valid tile should coordinates should equal zero and be within bounds of abs(radius)
          if (q + r + s != 0) {
            continue;
          }
          //If its black place an X, if white place an O, otherwise do underscore.
          switch (model.getDiscAt(q, r, s).getColor()) {
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
        if (q == boardRadius - 1) {
          returnString.append("\n");
        }
      }


    }
    return returnString.toString();
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
