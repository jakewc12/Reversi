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
    this.output = thing;
  }

  @Override
  public String toString() {

    int boardRadius = model.getBoardRadius();
    StringBuilder returnString = new StringBuilder();

    for (int rr = -1 * boardRadius; rr <= boardRadius; rr++) {
      //Append the start with spaces
      for (int i = 0; i < boardRadius + Math.abs(rr); i++) {
        returnString.append(" ");
      }
      for (int qq = -1 * boardRadius; qq <= boardRadius; qq++) {
        for (int ss = -1 * boardRadius; ss <= boardRadius; ss++) {

          //valid tile should coordinates should equal zero and be within bounds of abs(radius)
          if (rr + qq + ss != 0) {
            continue;
          }
          //If its black place an X, if white place an O, otherwise do underscore.
          switch (model.getDiscAt(qq, rr, ss).getColor()) {
            case BLACK:
              returnString.append("X");
              break;
            case WHITE:
              returnString.append("O");
              break;
            default:
              returnString.append("_");
          }
          if (qq != boardRadius) {
            returnString.append(" ");
          }
        }
      }
      // add a new line at then end of the current line.
      returnString.append("\n");
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
