package digitalviews;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.ReadOnlyReversiModel;

/**
 * The DigitalReversiWindow class extends JFrame and implements the DigitalWindow interface. It
 * represents the graphical user interface for a Reversi game, allowing users to interact with the
 * game through a window. The window includes a hexagonal game board, input field, and buttons for
 * playing moves.
 */
public class DigitalReversiWindow extends JFrame implements DigitalWindow {

  private ReadOnlyReversiModel model;
  private JTextField input;
  private HexManager manager;

  Consumer<String> commandCallback;

  /**
   * Constructs a DigitalReversiWindow with the specified ReadOnlyReversiModel.
   *
   * @param model The ReadOnlyReversiModel representing the game state.
   * @throws IllegalArgumentException If the model is null.
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

  /**
   * Initializes the components of the DigitalReversiWindow, including the title, layout, hexagonal
   * game board, input field, and buttons for playing moves.
   */
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

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    // this.add(buttonPanel, BorderLayout.SOUTH);

    input = new JTextField(15);
    JButton executeButton = new JButton("Play Move");
    executeButton.addActionListener((ActionEvent e) -> {
      if (commandCallback != null) {
        commandCallback.accept(input.getText());
        input.setText("");
        if (model.gameOver()) {
          input.setText("Game Over!");
        }
      }
    });
    buttonPanel.add(input);
    buttonPanel.add(executeButton);

    commandCallback = null;

    this.refresh();
    this.pack();
  }

  @Override
  public void setCommandCallback(Consumer<String> callback) {
    commandCallback = callback;
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error Happened", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void refresh() {
    manager.refresh();
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }
}
