package digitalviews;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.*;

import model.ReadOnlyReversiModel;

public class DigitalReversiWindow extends JFrame implements DigitalWindow {
  private ReadOnlyReversiModel model;
  private DigitalReversiBoard board;
  private JTextField input;

  Consumer<String> commandCallback;

  public DigitalReversiWindow(ReadOnlyReversiModel model) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    board = new DigitalReversiBoard(model);

    init();

  }
  private void init() {
    board = new DigitalReversiBoard(model);

    this.setTitle("Reversi");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBackground(Color.DARK_GRAY);
    this.setLayout(new BorderLayout());
    this.add(board);
    this.getContentPane().setBackground(Color.DARK_GRAY);
    this.setSize(500, 800);


    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    input = new JTextField(15);
    JButton excuteButton = new JButton("Execute");
    excuteButton.addActionListener((ActionEvent e) ->
    {
      if (commandCallback != null) { //if there is a command callback
        commandCallback.accept(input.getText()); //send command to be processed
        input.setText(""); //clear the input text field
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
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }
}
