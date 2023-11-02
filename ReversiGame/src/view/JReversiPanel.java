package view;

import java.awt.*;

import javax.swing.*;

import model.ReadOnlyReversiModel;

public class JReversiPanel extends JPanel {
  private ReadOnlyReversiModel model;

  public JReversiPanel(ReadOnlyReversiModel model){
    this.model = model;
  }
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
  }
}
