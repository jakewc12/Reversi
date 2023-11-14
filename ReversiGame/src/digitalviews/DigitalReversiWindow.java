package digitalviews;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;

import javax.swing.*;

import controller.Features;
import model.ReadOnlyReversiModel;

/**
 * DigitalReversiWindow is a GUI rendering of the Reversi game.
 */
public class DigitalReversiWindow extends JFrame implements DigitalWindow {
  private ReadOnlyReversiModel model;
  private HexManager manager;
  private DigitalWindow window = this;

  /**
   * Creates a DigitalReversiWindow based on model.
   *
   * @param model the Reversi game to be rendered.
   */
  public DigitalReversiWindow(ReadOnlyReversiModel model) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    int radius = model.getBoardRadius();

    int windowSize = (radius * 2 + 1) * DrawnHexagon.hexagonLength * 2;
    this.setSize(new Dimension(windowSize, windowSize));
    manager = new HexManager(windowSize, windowSize, model);
    init();

  }

  private void init() {
    this.setTitle("Reversi");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBackground(Color.DARK_GRAY);
    this.setLayout(new BorderLayout());


    int radius = model.getBoardRadius();
    int windowSize = (radius * 2 + 1) * DrawnHexagon.hexagonLength * 2;
    manager = new HexManager(windowSize, windowSize, model);
    this.add(manager);


    this.getContentPane().setBackground(Color.DARK_GRAY);
    this.setSize(500, 800);


    this.refresh();
    this.pack();
  }

  /**
   * Signals the view to draw or redraw itself.
   */
  @Override
  public void refresh() {
    manager.refresh();
    this.setTitle("Reversi - " + model.getCurrentTurn().toString() + "'s Turn");
    if (model.gameOver()) {
      this.setTitle("Game Over!");
    }
    this.repaint();
  }

  /**
   * Make the view visible. This is usually called after the view is constructed.
   */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Adds features to the window to allow for key interaction.
   *
   * @param features the features to be added to the view.
   */
  @Override
  public void addFeaturesListener(Features features) {
    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'p') {
          if (manager.getHighlightedCord().isPresent()) {
            features.placeDisc(manager.getHighlightedCord().get());
          }
        } else if (e.getKeyChar() == 's') {
          features.skipTurn();
        }
        window.refresh();
      }

      @Override
      public void keyPressed(KeyEvent e) {
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }
    });
  }
}
