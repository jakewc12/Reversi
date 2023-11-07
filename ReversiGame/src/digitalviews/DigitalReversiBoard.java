package digitalviews;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;

import controller.MVCController;
import model.GameDisc;
import model.ReadOnlyReversiModel;

public class DigitalReversiBoard extends JPanel {
  private int radius;
  private ReadOnlyReversiModel model;

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
    manager = new HexManager(radius, 500, model);
    this.setBorder(null);
    this.add(manager);
  }





}
