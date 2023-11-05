package digitalviews;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


import javax.swing.*;

import javax.swing.JPanel;

import controller.ReversiController;
import model.ReadOnlyReversiModel;

public class JReversiPanel extends JFrame implements ReversiView {
  private List<HexCellPanel> cells;
  private ReadOnlyReversiModel model;
  private boolean clicked;

  public JReversiPanel(ReadOnlyReversiModel model) {

    super();
    this.setTitle("Reversi");
    this.getContentPane().setBackground(Color.DARK_GRAY);
    this.setSize(500, 500);
    this.refresh();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container contentPane = this.getContentPane();
    cells = new ArrayList<>();
    //this.model = model;
    JPanel panel = new JPanel();
    this.setLayout(null);
    for (int r = -1 * model.getBoardRadius(); r <= model.getBoardRadius(); r++) {
      for (int q = -1 * model.getBoardRadius(); q <= model.getBoardRadius(); q++) {
        for (int s = -1 * model.getBoardRadius(); s <= model.getBoardRadius(); s++) {
          if ((r + q + s) == 0) {
            HexCellPanel thisCell = new HexCellPanel(model.getColorAt(q, r, s), q, r, s, model.getBoardRadius());
            thisCell.repaint();
            cells.add(thisCell);
            contentPane.add(thisCell);
            //this.add(thisCell);
          }
        }
      }
    }
    //makePolygon(q,r);

    System.out.println("hi");
    this.repaint();
    this.setLocationRelativeTo(null);
    this.refresh();
    this.setVisible(true);

  }

  private void makePolygon(int q, int r, Graphics g) {
    g.setColor(Color.GRAY);
    Polygon polygon = new Polygon();
    for (int i = 0; i < 6; i++) {
      polygon.addPoint((int) (250 + (r * 30) + 100 * Math.sin(i * 2 * Math.PI / 6)),
              (int) (250 + (q * 30) + 100 * Math.cos(i * 2 * Math.PI / 6)));
    }
    g.drawPolygon(polygon);
    g.fillPolygon(polygon);
  }

  @Override
  public void addClickListener(ReversiController listener) {

  }

  @Override
  public void refresh() {

  }

  @Override
  public void makeVisible() {

  }

  @Override
  public void showErrorMessage(String error) {

  }
}

