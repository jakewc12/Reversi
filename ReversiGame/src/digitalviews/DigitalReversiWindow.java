package digitalviews;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.*;

import controller.ReversiController;
import model.ReadOnlyReversiModel;

public class DigitalReversiWindow extends JFrame implements DigitalWindow {
  private ReadOnlyReversiModel model;
  private JTextField input;
  private HexManager manager;
  private JPanel board;
  private JButton excuteButton;
  private JPanel buttonPanel;

  Consumer<String> commandCallback;

  public DigitalReversiWindow(ReadOnlyReversiModel model) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    int radius = model.getBoardRadius();
    int windowSize = (radius * 2 + 1) * Hexagon.hexagonLength * 2;
    manager = new HexManager(radius, windowSize, model);
    board = new JPanel();
    init();

  }

  private void init() {
    this.setTitle("Reversi");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBackground(Color.DARK_GRAY);
    this.setLayout(new BorderLayout());


    board.setBorder(null);
    board.add(manager);


    this.add(board);
    this.getContentPane().setBackground(Color.DARK_GRAY);
    this.setSize(500, 800);


    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    input = new JTextField(15);
    excuteButton = new JButton("Play Move");
    excuteButton.addActionListener((ActionEvent e) ->
    {
      if (commandCallback != null) {
        commandCallback.accept(input.getText());
        input.setText("");
        if(model.gameOver()) {
          input.setText("Game Over!");
        }
      }
    });
    buttonPanel.add(input);
    buttonPanel.add(excuteButton);

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
    manager.refresh();
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }
}
