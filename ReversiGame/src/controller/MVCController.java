package controller;

import digitalviews.DigitalWindow;
import java.util.Scanner;
import model.Coordinate;
import model.MutableReversiModel;

/**
 * The MutableReversiController class implements the ReversiControllerInterface and serves as the
 * controller for a mutable Reversi game. It processes commands from the user through a
 * DigitalWindow view and updates the MutableReversiModel accordingly. This class handles commands
 * such as placing discs and skipping turns, providing feedback through the view. The controller
 * interacts with a mutable model, allowing for changes in the game state.
 */
public class MutableReversiController implements ReversiControllerInterface {

  private final MutableReversiModel model;
  private final DigitalWindow view;

  /**
   * Constructs a MutableReversiController with the specified model and view.
   *
   * @param model The MutableReversiModel representing the game state.
   * @param view  The DigitalWindow view for user interaction.
   */
  public MutableReversiController(MutableReversiModel model, DigitalWindow view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public String processCommand(String command) {
    StringBuilder output = new StringBuilder();
    Scanner scan = new Scanner(command);
    while (scan.hasNext()) {
      String in = scan.next();
      if (!model.gameOver()) {
        if (in.equalsIgnoreCase("place")) {
          int q = scan.nextInt();
          int r = scan.nextInt();
          int s = scan.nextInt();
          Coordinate coord = new Coordinate(q, r, s);
          try {
            model.placeDisc(coord);
            output.append("Placed at (").append(q).append(", ").append(r).append(", ").append(s)
                .append(")");
            System.out.println("Placed at (" + q + ", " + r + ", " + s + ")");
          } catch (IllegalArgumentException e) {
            output.append("Invalid move. Illegal numbers given for q, r, s");
            System.out.println("Invalid move. Illegal numbers given for q, r, s");
          } catch (IllegalStateException e) {
            output.append("Invalid move. Illegal move played");
            System.out.println("Invalid move. Illegal move played");
          }
        } else if (in.equals("skip")) {
          try {
            model.skipCurrentTurn();
          } catch (Exception e) {
            output.append("Connection with model failed");
          }
        } else {
          output.append("Invalid command given");
        }
      }
      view.refresh();
      view.makeVisible();
    }
    return output.toString();
  }

  @Override
  public void go() {
    this.view.setCommandCallback(this::accept);
    this.view.refresh();
    this.view.makeVisible();
  }

  /**
   * Accepts a command from the view and processes it.
   *
   * @param command The command to be processed.
   */
  public void accept(String command) {
    try {
      processCommand(command);
    } catch (Exception ex) {
      view.showErrorMessage(ex.getMessage());
    }
  }
}
