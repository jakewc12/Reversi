package Helpers;

import model.ReadOnlyReversiModel;
import view.ReversiTextualView;
import view.TextualView;

public class TestHelper {

  public static void printModelBoard(ReadOnlyReversiModel model) {
    TextualView tv = new ReversiTextualView(model);
    System.out.println(tv);
  }
}
