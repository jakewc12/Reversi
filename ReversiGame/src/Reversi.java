import controller.MVCController;
import controller.ReversiControllerInterface;
import digitalviews.DigitalReversiWindow;
import digitalviews.DigitalWindow;
import model.MutableReversi;
import model.MutableReversiModel;
import view.ReversiTextualView;
import view.TextualView;

/**
 * the main class for reversi.
 */
public final class Reversi {

  public static void main(String[] args) {
    MutableReversiModel model = new MutableReversi(3);
    TextualView tv = new ReversiTextualView(model);

    model.startGame(model.getBoard());
    DigitalWindow view = new DigitalReversiWindow(model);
    view.makeVisible();
    //ReversiControllerInterface controller = new MVCController(model, view);
    //controller.go();
  }
}