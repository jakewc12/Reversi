package digitalviews;

import javax.swing.*;

import controller.ReversiController;
import model.ReadOnlyReversiModel;

public class SimpleReversiView extends JFrame implements ReversiView {
  private final JReversiPanel panel;
  public SimpleReversiView(ReadOnlyReversiModel model) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panel = new JReversiPanel(model);
    this.add(panel);
    this.pack();
  }
  @Override
  public void addClickListener(ReversiController listener) {

  }

  @Override
  public void refresh() {
    this.panel.repaint();
  }

  @Override
  public void makeVisible() {

  }
  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);

  }
}
