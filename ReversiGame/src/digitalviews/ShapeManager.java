package digitalviews;

import digitalviews.hexreversi.DrawnHexagon;
import digitalviews.squarereversi.DrawnSquare;
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
import model.hexreversi.LogicalHexCoordinate;
import model.squarereversi.MutableSquareReversi;
import model.squarereversi.SquareCoordinate;

/**
 * The HexManager class manages the display and interaction of hexagonal tiles on a JPanel for a
 * Reversi game. It extends JPanel and implements the DigitalBoardManager interface. HexManager
 * handles the creation and rendering of tiles based on the game model, and allows highlighting and
 * interaction with specific tiles through mouse events.
 */
public class ShapeManager extends JPanel implements DigitalBoardManager {

  /**
   * everytime something happens with model, we should reset tiles. this way, we can redraw the
   * tiles if multiple change at once.
   */
  private final ReadOnlyReversiModel model;
  private final ShapeManager manager = this;
  private List<DrawnShape> tiles = new ArrayList<>();
  private int centerCordX;
  private int centerCordY;
  private boolean hintsEnabled;
  /**
   * if no cell is highlighted, then highLightedCord will be a coordinate not on the board.
   */
  private Optional<Coordinate> highlightedCord;
  private boolean tileClicked;

  /**
   * Constructs a HexManager with the specified window dimensions and Reversi game model.
   *
   * @param windowWidth  The width of the window.
   * @param windowHeight The height of the window.
   * @param model        The ReadOnlyReversiModel representing the game state.
   */
  public ShapeManager(int windowWidth, int windowHeight, ReadOnlyReversiModel model) {
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

    makeShapes();
    this.repaint();
  }

  private void makeShapes() {
    if (this.model instanceof MutableSquareReversi) {
      makeSquare();
    } else {
      makeHexagons();
    }
  }

  /**
   * Looks at the model and creates the tiles accordingly. This method should be called every time a
   * move is made.
   */
  private void makeHexagons() {
    tiles = new ArrayList<>();
    List<Coordinate> allLogicalCoordinates = model.getAllCoordinates();
    for (Coordinate logicalCoord : allLogicalCoordinates) {
      int hexLength = (Math.min(centerCordX, centerCordY)) / (model.getBoardSize());
      if (highlightedCord.isPresent()) {
        if (logicalCoord.equals(highlightedCord.get())) {
          if (hintsEnabled) {
            tiles.add(new DrawnHexagon((LogicalHexCoordinate) logicalCoord,
                model.getColorAt(logicalCoord), centerCordX, centerCordY, Color.CYAN, hexLength,
                Optional.of(model.getNumFlipsOnMove(logicalCoord, model.getCurrentTurn()))));
          } else {
            tiles.add(new DrawnHexagon((LogicalHexCoordinate) logicalCoord,
                model.getColorAt(logicalCoord), centerCordX, centerCordY, Color.CYAN, hexLength,
                Optional.empty()));
          }
        } else {
          tiles.add(
              new DrawnHexagon((LogicalHexCoordinate) logicalCoord, model.getColorAt(logicalCoord),
                  centerCordX, centerCordY, Color.LIGHT_GRAY, hexLength, Optional.empty()));
        }
      } else {
        tiles.add(
            new DrawnHexagon((LogicalHexCoordinate) logicalCoord, model.getColorAt(logicalCoord),
                centerCordX, centerCordY, Color.LIGHT_GRAY, hexLength, Optional.empty()));
      }
    }
  }

  private void makeSquare() {
    tiles = new ArrayList<>();
    List<Coordinate> allLogicalCoordinates = model.getAllCoordinates();
    for (Coordinate logicalCoord : allLogicalCoordinates) {
      int hexLength = Math.min(centerCordY, centerCordX) * 2 / model.getBoardSize();
      if (highlightedCord.isPresent()) {
        if (logicalCoord.equals(highlightedCord.get())) {
          if (hintsEnabled) {
            tiles.add(
                new DrawnSquare((SquareCoordinate) logicalCoord, model.getColorAt(logicalCoord),
                    Color.CYAN, hexLength,
                    Optional.of(model.getNumFlipsOnMove(logicalCoord, model.getCurrentTurn()))));
          } else {
            tiles.add(
                new DrawnSquare((SquareCoordinate) logicalCoord, model.getColorAt(logicalCoord),
                    Color.CYAN, hexLength, Optional.empty()));
          }
        } else {
          tiles.add(new DrawnSquare((SquareCoordinate) logicalCoord, model.getColorAt(logicalCoord),
              Color.LIGHT_GRAY, hexLength, Optional.empty()));
        }
      } else {
        tiles.add(new DrawnSquare((SquareCoordinate) logicalCoord, model.getColorAt(logicalCoord),
            Color.LIGHT_GRAY, hexLength, Optional.empty()));
      }
    }
  }

  private boolean clickLandsOnHex(int x, int y) {
    for (DrawnShape shape : tiles) {
      if (shape.containsPoint(x, y)) {
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
    makeShapes();
    g2d.scale(1, 1);
    this.setBackground(Color.DARK_GRAY);
    g2d.setColor(Color.DARK_GRAY);
    g2d.fillRect(0, 0, getWidth(), getHeight());
    for (DrawnShape tile : tiles) {
      tile.draw(g2d);
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

  @Override
  public void enableHints() {
    hintsEnabled = !hintsEnabled;
    System.out.println(hintsEnabled);
  }

  @Override
  public boolean tileCurrentlyClicked() {
    return this.tileClicked;
  }

  @Override
  public List<DrawnShape> getShapes() {
    return new ArrayList<>(tiles);
  }

  private class MouseEventsListener extends MouseInputAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
      highlightedCord = Optional.empty();
      if (!tileClicked) {
        tileClicked = clickLandsOnHex(e.getX(), e.getY());
      } else {
        tileClicked = false;
      }
      if (tileClicked) {
        for (DrawnShape shape : tiles) {
          if (shape.containsPoint(e.getX(), e.getY())) {
            highlightedCord = Optional.ofNullable(shape.getLogicalCoord());
          }
        }
        highlightedCord.ifPresent(
            coordinate -> System.out.println("Highlighted cell at " + coordinate));
      }
      manager.repaint();
    }
  }
}
