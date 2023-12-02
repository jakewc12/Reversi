package digitalviews;

import static digitalviews.DrawnHexagon.hexagonRadius;

import controller.PlayerActions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.DiscColor;
import model.ReadOnlyReversiModel;
import player.Player;

/**
 * DigitalReversiWindow represents the GUI rendering of the Reversi.
 */
public class DigitalReversiWindow extends JFrame implements DigitalWindow {

  private final ReadOnlyReversiModel model;
  private final DigitalWindow window = this;
  private HexManager manager;
  private KeyListener listener;

  /**
   * Creates a new DigitalReversiWindow.
   *
   * @param model the Reversi to be used.
   */
  public DigitalReversiWindow(ReadOnlyReversiModel model) {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    int radius = model.getBoardRadius();

    int windowSize = (radius * 2 + 1) * hexagonRadius * 2;
    this.setSize(new Dimension(windowSize, windowSize));
    manager = new HexManager(windowSize, windowSize, model);
    init();

  }

  /**
   * Initializes the reversi window by creating a default window with the size 500x500. It then
   * creates a manager to manage the hexes placement and size.
   */
  private void init() {
    this.setTitle("Reversi");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBackground(Color.DARK_GRAY);
    this.setLayout(new BorderLayout());

    this.getContentPane().setBackground(Color.DARK_GRAY);
    this.setSize(500, 500);

    int windowSize = (model.getBoardSize()) * hexagonRadius * 2;
    manager = new HexManager(windowSize, windowSize, model);
    this.add(manager);

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
      String winner;
      if (model.checkScoreOfPlayer(DiscColor.WHITE) > model.checkScoreOfPlayer(DiscColor.BLACK)) {
        winner = " Winner is WHITE";
      } else if (model.checkScoreOfPlayer(DiscColor.BLACK) > model.checkScoreOfPlayer(
              DiscColor.WHITE)) {
        winner = " Winner is BLACK";
      } else {
        winner = " TIE";
      }
      this.setTitle("Game Over!" + winner);
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
  public void addFeaturesListener(PlayerActions features) {
    listener = new KeyListener() {
      /**
       * Allows for key interactions. If 'p' is pressed, it will use features to make a move
       * on the coordinate of the highlighted hex. If 's' is pressed, it will use features to
       * skip a turn.
       *
       * @param e the event to be processed
       */
      @Override
      public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'p') {
          if (manager.getHighlightedCord().isPresent()) {
            System.out.println("Placed disc at " + manager.getHighlightedCord().get());
            features.placeDisc(manager.getHighlightedCord().get());
          } else {
            System.out.println("No disc selected to place.");
          }
        } else if (e.getKeyChar() == 's') {
          System.out.println("Skipped turn.");
          features.skipTurn();
        }
        window.refresh();
      }

      @Override
      public void keyPressed(KeyEvent e) {
        //needs to here to implement the interface.
      }

      @Override
      public void keyReleased(KeyEvent e) {
        //needs to here to implement the interface.
      }
    };
    this.addKeyListener(listener);
  }

  @Override
  public KeyListener getListener() {
    return this.listener;
  }

  /**
   * Creates a window which informs the player a illegal move has been made.
   */
  @Override
  public void showErrorMessage(Player player) {
    JOptionPane.showMessageDialog(null, "Illegal move for player " + player.getPlayerColor());

  }
}
