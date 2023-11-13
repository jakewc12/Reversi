import controller.MutableReversiController;
import controller.ReversiControllerInterface;
import digitalviews.DigitalReversiWindow;
import digitalviews.DigitalWindow;
import model.MutableReversi;
import model.MutableReversiModel;
import view.ReversiTextualView;
import view.TextualView;

public final class Reversi {

  public static void main(String[] args) {
    MutableReversiModel model = new MutableReversi(3);
    TextualView tv = new ReversiTextualView(model);

    model.startGame(model.getBoard());
    System.out.println(tv);
    DigitalWindow view = new DigitalReversiWindow(model);
    view.makeVisible();
    ReversiControllerInterface controller = new MutableReversiController(model, view);
    controller.go();
  }
}