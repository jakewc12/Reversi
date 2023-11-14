package digitalviews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import model.Coordinate;
import model.ReadOnlyReversiModel;

/**
 * The HexManager class manages the display and interaction of hexagonal tiles on a JPanel for a
 * Reversi game. It extends JPanel and implements the DigitalBoardManager interface. HexManager
 * handles the creation and rendering of hexagons based on the game model, and allows highlighting
 * and interaction with specific hexagons through mouse events.
 */
public class HexManager extends JPanel implements DigitalBoardManager {

  private List<DrawnHexagonInterface> hexagons = new ArrayList<>();
  private int centerCordX;
  private int centerCordY;
  /**
   * if no cell is highlighted, then highLightedCord will be a coordinate not on the board.
   */
  private Optional<Coordinate> highlightedCord;
  private boolean hexClicked = false;
  /**
   * everytime something happens with model, we should reset hexagons. this way, we can redraw the
   * tiles if multiple change at once.
   */
  private final ReadOnlyReversiModel model;
  private final HexManager manager = this;

  /**
   * Constructs a HexManager with the specified window dimensions and Reversi game model.
   *
   * @param windowWidth  The width of the window.
   * @param windowHeight The height of the window.
   * @param model        The ReadOnlyReversiModel representing the game state.
   */
  public HexManager(int windowWidth, int windowHeight, ReadOnlyReversiModel model) {
    if (model == null || windowHeight < 1 || windowWidth < 1) {
      throw new IllegalArgumentException("Invalid inputs for HexManager");

    }
    this.setPreferredSize(new Dimension(windowWidth, windowHeight));
    this.setOpaque(false);
    this.addMouseListener(new MouseEventsListener());
    this.setBackground(Color.DARK_GRAY);
    centerCordX = windowWidth / 2;
    centerCordY = windowHeight / 2;
    this.model = model;
    highlightedCord = Optional.empty();
    makeHexagons();

    this.repaint();
  }

  /**
   * Looks at the model and creates the hexagons accordingly. This method should be called every
   * time a move is made.
   */
  private void makeHexagons() {
    hexagons = new ArrayList<>();
    List<Coordinate> allCoordinates = model.getAllCoordinates();
    for (Coordinate logicalCoord : allCoordinates) {
      int hexLength = (Math.min(centerCordX, centerCordY)) / (2 * model.getBoardRadius() + 1);
      if (highlightedCord.isPresent()) {
        if (logicalCoord.equals(highlightedCord.get())) {
          hexagons.add(
                  new DrawnHexagon(logicalCoord, model.getColorAt(logicalCoord), centerCordX
                          , centerCordY, Color.CYAN, hexLength));
        } else {
          hexagons.add(
                  new DrawnHexagon(logicalCoord, model.getColorAt(logicalCoord), centerCordX
                          , centerCordY, Color.LIGHT_GRAY, hexLength));
        }
      } else {
        hexagons.add(
                new DrawnHexagon(logicalCoord, model.getColorAt(logicalCoord), centerCordX
                        , centerCordY, Color.LIGHT_GRAY, hexLength));
      }
    }
  }

  private boolean clickLandsOnHex(int x, int y) {
    for (DrawnHexagonInterface hex : hexagons) {
      if ((Math.abs(y - hex.getY()) <= 22.5) && (Math.abs(x - hex.getX()) <= 22.5)) {
        return true;
      }
    }
    return false;
  }

  /**
   * paints this JPanel onto the graphics.
   *
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  protected void paintComponent(Graphics g) {
    final Graphics2D g2d = (Graphics2D) g;

    this.centerCordY = getHeight() / 2;
    this.centerCordX = getWidth() / 2;
    makeHexagons();
    g2d.scale(1, 1);
    this.setBackground(Color.DARK_GRAY);
    g2d.setColor(Color.DARK_GRAY);
    g2d.fillRect(0, 0, getWidth(), getHeight());
    for (DrawnHexagonInterface hex : hexagons) {
      hex.draw(g2d);
    }
  }

  /**
   * Returns the (Q,R,S) coordinate of the current cell that is highlighted.
   *
   * @return the current highlighted cell on the board.
   */
  public Optional<Coordinate> getHighlightedCord() {
    return highlightedCord;
  }

  /**
   * Signals the view to draw or redraw itself.
   */
  @Override
  public void refresh() {
    this.repaint();
  }

  private class MouseEventsListener extends MouseInputAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
      highlightedCord = Optional.empty();
      if (!hexClicked) {
        hexClicked = clickLandsOnHex(e.getX(), e.getY());
      } else {
        hexClicked = false;
      }
      if (hexClicked) {
        for (DrawnHexagonInterface hex : hexagons) {
          if ((Math.abs(e.getY() - hex.getY()) <= 18) && (Math.abs(e.getX() - hex.getX()) <= 18)) {
            highlightedCord = Optional.ofNullable(hex.getHexCoordinate());
          }
        }
        highlightedCord.ifPresent(coordinate ->
                System.out.println("Highlighted cell at " + coordinate.toString()));
      }
      manager.repaint();
    }
  }
}
