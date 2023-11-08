package digitalviews;


import javax.swing.JPanel;

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
    init();
  }

  private void init() {
    radius = model.getBoardRadius();
    int windowSize = (radius * 2 + 1) * Hexagon.hexagonLength * 2;
    manager = new HexManager(radius, windowSize, model);
    this.setBorder(null);
    this.add(manager);
  }
}
