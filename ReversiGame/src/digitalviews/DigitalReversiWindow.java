package digitalviews;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;

import javax.swing.*;

import controller.Features;
import model.ReadOnlyReversiModel;

public class DigitalReversiWindow extends JFrame implements DigitalWindow {
  private ReadOnlyReversiModel model;
  private JTextField input;
  private HexManager manager;

  Consumer<String> commandCallback;

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


    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
   // this.add(buttonPanel, BorderLayout.SOUTH);

    input = new JTextField(15);
    JButton excuteButton = new JButton("Play Move");
    excuteButton.addActionListener((ActionEvent e) ->
    {
      if (commandCallback != null) {
        commandCallback.accept(input.getText());
        input.setText("");
        if (model.gameOver()) {
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

  @Override
  public void setCommandCallback(Consumer<String> callback) {
    commandCallback = callback;
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error Happened"
            , JOptionPane.ERROR_MESSAGE);
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

  @Override
  public void addFeaturesListener(Features features){
    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {

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
