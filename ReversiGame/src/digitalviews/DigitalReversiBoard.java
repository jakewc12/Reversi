package digitalviews;

import controller.MVCController;
import javax.swing.JPanel;
import model.ReadOnlyReversiModel;

public class DigitalReversiBoard extends JPanel {

  private int radius;
  private final ReadOnlyReversiModel model;

  private HexManager manager;
  private MVCController controller;


  public DigitalReversiBoard(ReadOnlyReversiModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    init();
  }

  public DigitalReversiBoard(ReadOnlyReversiModel model, MVCController controller) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    if (controller == null) {
      throw new IllegalArgumentException("Controller cannot be null");
    }
    this.controller = controller;
    this.model = model;
    init();
  }

  private void init() {
    radius = model.getBoardRadius();
    System.out.println();
    int windowSize = (radius * 2 + 1) * Hexagon.hexagonLength * 2;
    manager = new HexManager(radius, windowSize, model);
    this.setBorder(null);
    this.add(manager);
  }


}
