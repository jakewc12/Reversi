package digitalviews;

import java.awt.*;

import javax.swing.*;

import model.ReadOnlyReversiModel;

public class DigitalReversiWindow extends JFrame implements DigitalWindow {
  private ReadOnlyReversiModel model;
  private DigitalReversiBoard board;


  public DigitalReversiWindow(ReadOnlyReversiModel model) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    board = new DigitalReversiBoard(model);

    this.add(board);
    this.setTitle("Reversi");
    this.setBackground(Color.DARK_GRAY);

    this.getContentPane().setBackground(Color.DARK_GRAY);
    this.setSize(500, 500);
    this.refresh();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }
}
