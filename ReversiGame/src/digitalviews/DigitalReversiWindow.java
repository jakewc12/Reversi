package digitalviews;

import controller.ReversiController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.ReadOnlyReversiModel;

public class DigitalReversiWindow extends JFrame implements DigitalWindow {

  Consumer<String> commandCallback;
  private final ReadOnlyReversiModel model;
  private DigitalReversiBoard board;
  private JTextField input;
  private JButton executeButton;
  private JPanel buttonPanel;
  private ReversiController controller;

  public DigitalReversiWindow(ReadOnlyReversiModel model) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    board = new DigitalReversiBoard(model);
    int windowSize = (model.getBoardRadius() * 2 + 1) * Hexagon.hexagonLength * 2;
    init(windowSize);

  }

  public DigitalReversiWindow(ReadOnlyReversiModel model, ReversiController controller) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    if (controller == null) {
      throw new IllegalArgumentException("Controller cannot be null");
    }
    this.model = model;
    this.controller = controller;
    int windowSize = (model.getBoardRadius() * 2 + 1) * Hexagon.hexagonLength * 2;
    init(windowSize);
  }

  private void init(int windowSize) {
    board = new DigitalReversiBoard(model);

    this.setTitle("Reversi");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBackground(Color.DARK_GRAY);
    this.setLayout(new BorderLayout());
    this.add(board);
    this.getContentPane().setBackground(Color.DARK_GRAY);
    this.setSize(windowSize, windowSize);

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    input = new JTextField(15);
    executeButton = new JButton("Execute");
    executeButton.addActionListener((ActionEvent e) -> {
      if (commandCallback != null) { //if there is a command callback
        System.out.println("pressed");
        commandCallback.accept(input.getText()); //send command to be processed
        input.setText(""); //clear the input text field
      }
    });
    buttonPanel.add(input);
    buttonPanel.add(executeButton);

    commandCallback = null;
    this.refresh();
    this.pack();
  }

  public void setCommandCallback(Consumer<String> callback) {
    commandCallback = callback;
  }

  @Override
  public void showErrorMessage(String error) {

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
