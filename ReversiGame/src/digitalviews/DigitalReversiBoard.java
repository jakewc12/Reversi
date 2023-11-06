package digitalviews;

import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import model.GameDisc;
import model.ReadOnlyReversiModel;

public class DigitalReversiBoard extends JPanel {
  private int radius;
  private ReadOnlyReversiModel model;
  private HexManager manager;

  public DigitalReversiBoard(ReadOnlyReversiModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    radius = model.getBoardRadius();
    manager = new HexManager(radius, 500, model);
    this.add(manager);
  }


}
