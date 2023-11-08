import controller.MVCController;
import controller.ReversiController;
import digitalviews.DigitalReversiWindow;
import digitalviews.DigitalWindow;
import model.MutableReversi;
import model.MutableReversiModel;
import view.ReversiTextualView;
import view.TextualView;

public final class Reversi {
  public static void main(String[] args) {
    MutableReversiModel model = new MutableReversi(4);
    TextualView tv = new ReversiTextualView(model);

    model.startGame(model.getBoard());
    System.out.println(tv.toString());
    DigitalWindow view = new DigitalReversiWindow(model);
    ReversiController controller = new MVCController(model, view);
    controller.go();
    //view.makeVisible();
  }
}