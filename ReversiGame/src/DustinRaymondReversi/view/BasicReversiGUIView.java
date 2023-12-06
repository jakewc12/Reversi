package DustinRaymondReversi.view;

import DustinRaymondReversi.controller.ReversiPlayerActions;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Represents a GUI window that displays a basic hexagonal Reversi game using the Swing library. A
 * basic Reversi GUI view is a JFrame and also contains a basic Reversi GUI panel, within which the
 * entire game is displayed.
 */
public class BasicReversiGUIView extends JFrame implements ReversiGUIView {

  private final BasicReversiGUIPanel panel;

  /**
   * Constructs a new basic Reversi GUI view that displays the given Reversi game.
   *
   * @param model the game to display in this view
   * @throws IllegalArgumentException if the game is null
   */
  public BasicReversiGUIView(DustinRaymondReversi.model.ReadOnlyReversiModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model provided to panel cannot be null.");
    }

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panel = new BasicReversiGUIPanel(model);
    this.add(this.panel);
    this.pack();
  }

  @Override
  public void addFeaturesListener(ReversiPlayerActions features) {
    this.panel.addFeaturesListener(features);
  }

  @Override
  public void display(boolean show) {
    this.setVisible(show);
  }

  @Override
  public void showError(String message) {
    JOptionPane.showMessageDialog(this, message, "Stop! You broke the law!",
        JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void refresh() {
    this.panel.repaint();
  }
}
