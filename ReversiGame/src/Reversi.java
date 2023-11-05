import model.MutableReversi;
import model.MutableReversiModel;
import digitalviews.JReversiPanel;

public final class Reversi {
  public static void main(String[] args) {
    MutableReversiModel model = new MutableReversi(3);
    model.startGame(model.getBoard());
    JReversiPanel view = new JReversiPanel(model);
    view.setVisible(true);
  }
}