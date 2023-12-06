package DustinRaymondReversi.view;

import DustinRaymondReversi.controller.ReversiPlayerActions;
import DustinRaymondReversi.model.HexPosn;
import DustinRaymondReversi.model.PlayerPiece;
import DustinRaymondReversi.model.ReadOnlyReversiModel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

/**
 * Represents a GUI panel containing a graphical display of a Reversi game. The panel displays a
 * representation of the game board and can also allow a user to interact with the game board
 * through various means, including key and mouse inputs. Whenever the game is updated for any
 * reason, the panel is updated to display the most recent state of the board.
 */
public class BasicReversiGUIPanel extends JPanel {

  private final ReadOnlyReversiModel model;

  // contains any feature listeners that need to be notified whenever the user
  // triggers an event
  private final List<ReversiPlayerActions> listeners;
  // fields for the panel
  private final Map<Character, Runnable> keyTyped;
  private final Map<Integer, Runnable> keyPressed;
  private final Map<Integer, Runnable> keyReleased;
  // stores the current cell clicked. Set to null if no cell is clicked.
  private HexPosn currentCellClicked;

  /**
   * Constructs a new GUI panel with read-only access to the given Reversi model.
   *
   * @param model the game that will be displayed on this panel
   * @throws IllegalArgumentException if the provided model is null
   */
  BasicReversiGUIPanel(ReadOnlyReversiModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model provided to panel cannot be null.");
    }

    this.model = model;
    this.listeners = new ArrayList<>();
    this.currentCellClicked = null;

    // initializes mouse listener
    MouseEventsListener listener = new MouseEventsListener();
    this.addMouseListener(listener);
    this.addMouseMotionListener(listener);

    // initializes key binds
    this.keyTyped = new HashMap<>();
    this.keyPressed = new HashMap<>();
    this.keyReleased = new HashMap<>();
    this.configureKeyBinds();

    // initializes key listener
    this.setFocusable(true);
    this.requestFocus();
    KeyListener keyListener = new KeyboardEventsListener();
    this.addKeyListener(keyListener);
  }

  /**
   * Gets the associated color with the given PlayerPiece piece.
   *
   * @param piece the given player piece
   * @return the associated color with the given PlayerPiece piece
   */
  private static Color getPlayerPieceColor(PlayerPiece piece) {
    switch (piece) {
      case PLAYER_ONE:
        return Color.WHITE;
      case PLAYER_TWO:
        return Color.BLACK;
      default:
        // this shouldn't be able to happen
        throw new IllegalArgumentException("Unknown player piece type.");
    }
  }

  /**
   * Configures which keys map to specific key events, such as making a move or passing.
   */
  private void configureKeyBinds() {
    this.keyPressed.put(KeyEvent.VK_M, () -> {
      if (this.currentCellClicked != null) {
        System.out.println("Moved at " + this.currentCellClicked);

        for (ReversiPlayerActions listener : this.listeners) {
          listener.attemptMove(this.currentCellClicked);
        }
      } else {
        System.out.println("No cell selected");
      }
    });

    this.keyPressed.put(KeyEvent.VK_P, () -> {
      System.out.println("Passed turn");

      for (ReversiPlayerActions listener : this.listeners) {
        listener.attemptPass();
      }
    });
  }

  /**
   * Adds the given features set to this panel, allowing that features set to be notified whenever a
   * particular event occurs in the panel (i.e. a player performs a specific mouse or key action
   * that corresponds to a particular feature of the Reversi game).
   *
   * @param features the listener to add
   * @throws IllegalArgumentException if the listener is null
   */
  public void addFeaturesListener(ReversiPlayerActions features) {
    if (features == null) {
      throw new IllegalArgumentException("Requested features listener cannot be null.");
    }

    this.listeners.add(features);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(600, 600);
  }

  /**
   * Converts a given HexPosn (which uses axial q/r coordinates) to logical x and y coordinates. In
   * this coordinate system, one unit in either the x or y direction is equal to the "side length"
   * of a hexagon. The origin is in the middle of the screen, and (0, 0) in logical x/y coordinates
   * is equivalent to the HexPosn (0, 0). The x-axis increases horizontally rightward, and the
   * y-axis increases vertically upward.
   *
   * @param posn the HexPosn to convert
   * @return the x/y coordinates that correspond to that HexPosn
   */
  private Point2D.Double convertHexPosnToLogical(HexPosn posn) {
    double x = (posn.getQ() + 0.5 * posn.getR()) * Math.sqrt(3);
    double y = -1.5 * posn.getR();
    return new Point2D.Double(x, y);
  }

  /**
   * Calculates the amount by which to scale the logical coordinates to match the physical
   * coordinates, allowing for the board to resize itself to fit the window while remaining a
   * regular hexagon.
   *
   * @return the scale factor
   */
  private double calculateScaleFactor() {
    double logicalHeight = (this.model.getSideLength() - 1) * 3 + 2;
    double logicalWidth = (this.model.getSideLength() * 2 - 1) * Math.sqrt(3);

    double expectedScaleRatioFromHeight = this.getHeight() / logicalHeight;
    double expectedScaleRatioFromWidth = this.getWidth() / logicalWidth;
    return Math.min(expectedScaleRatioFromHeight, expectedScaleRatioFromWidth);
  }

  /**
   * Converts between logical x/y coordinates and physical pixel coordinates on the screen. In this
   * coordinate system, one unit in either the x or y direction is equal to the "side length" of a
   * hexagon. The origin is in the middle of the screen, and (0, 0) in logical x/y coordinates is
   * equivalent to the HexPosn (0, 0). The x-axis increases horizontally rightward, and the y-axis
   * increases vertically upward.
   *
   * @return the transformation
   */
  private AffineTransform transformLogicalToPhysical() {

    double scaleFactor = this.calculateScaleFactor();

    AffineTransform ret = new AffineTransform();
    ret.translate(this.getWidth() / 2., this.getHeight() / 2.);
    ret.scale(scaleFactor, scaleFactor);
    ret.scale(1, -1);

    return ret;
  }

  /**
   * Converts between physical pixel coordinates on the screen and logical x/y coordinates. The
   * logical x/y coordinate system is detailed in the Javadoc for the transformLogicalToPhysical
   * method.
   *
   * @return the corresponding logical coordinates of the given physical coordinates
   */
  private AffineTransform transformPhysicalToLogical() {
    double scaleFactor = this.calculateScaleFactor();

    AffineTransform ret = new AffineTransform();
    ret.scale(1, -1);
    ret.scale(1. / scaleFactor, 1. / scaleFactor);
    ret.translate(-this.getWidth() / 2., -this.getHeight() / 2.);

    return ret;
  }

  /**
   * Draws a regular hexagon with the given parameters. The hexagon is oriented so that one of its
   * corners is pointing upward; it is convex and both vertically and horizontally symmetric.
   *
   * @param graphics   the graphics object to copy and draw the hexagon on (the copy)
   * @param center     the center coordinates of the hexagon
   * @param sideLength the side length of the hexagon
   * @param color      the color of the hexagon
   * @param fill       whether the hexagon is filled or not
   */
  private void drawHexagon(Graphics graphics,
      Point2D center, double sideLength,
      Color color, boolean fill) {
    Graphics2D g2d = (Graphics2D) graphics.create();

    g2d.setColor(color);
    g2d.translate(center.getX(), center.getY());

    Shape hexagon = new HexagonShape(sideLength);

    if (fill) {
      g2d.fill(hexagon);
    } else {
      g2d.setStroke(new BasicStroke(0.1f));
      g2d.draw(hexagon);
    }
  }

  /**
   * Draws an individual hexagonal Reversi cell at the logical x/y coordinates that correspond to
   * the given HexPosn. This method does not convert logical x/y coordinates to physical coordinates
   * (handled in paintComponent method). This method also does not draw player pieces. Will
   * highlight the reversi cell cyan if it is the selected cell. Otherwise, it will fill in a gray
   * color.
   *
   * @param graphics the graphics object to draw on
   * @param pos      the HexPosn to draw at
   */
  private void drawReversiCell(Graphics graphics, HexPosn pos) {
    if (pos.equals(this.currentCellClicked)) {
      this.drawHexagon(graphics, this.convertHexPosnToLogical(pos), 1,
          Color.CYAN, true);
    } else {
      this.drawHexagon(graphics, this.convertHexPosnToLogical(pos), 1,
          Color.LIGHT_GRAY, true);
    }
    this.drawHexagon(graphics, this.convertHexPosnToLogical(pos), 1,
        Color.BLACK, false);
  }

  /**
   * Draws the given PlayerPiece piece in the cellat the logical x/y coordinates that correspond
   * with the given HexPosn. Similar to drawReversiCell, this method does not convert to physical
   * coordinates.
   *
   * @param graphics the graphics object to draw on
   * @param pos      the HexPosn to draw at
   * @param piece    the player piece to draw
   */
  private void drawPlayerPiece(Graphics graphics, HexPosn pos, PlayerPiece piece) {
    Graphics2D g2d = (Graphics2D) graphics.create();
    Color color = getPlayerPieceColor(piece);
    Point2D center = this.convertHexPosnToLogical(pos);

    g2d.setColor(color);
    g2d.translate(center.getX(), center.getY());

    Shape pieceCircle = new Ellipse2D.Double(-0.5, -0.5, 1, 1);
    g2d.fill(pieceCircle);
  }

  /**
   * Checks if the given position (in logical coordinates) occurs within the edges of the given
   * hexagon at a specific HexPosn. Calculates the center of the given Hexagon in logical
   * coordinates and then checks if the given position is located within all 6 sides.
   *
   * @param point      the position to check if in hexagon, as a Point2D.Double
   * @param hexToCheck the hexagon to check
   * @return true if the given position is located within the edges of the given hexagon, false if
   * not.
   */
  private boolean pointIsInHexagon(Point2D point, HexPosn hexToCheck) {
    Point2D hexCenter = convertHexPosnToLogical(hexToCheck);
    Point2D deltaFromCenter = new Point2D.Double(
        point.getX() - hexCenter.getX(),
        point.getY() - hexCenter.getY());

    double sideSlope = 1 / Math.sqrt(3);
    return (Math.abs(deltaFromCenter.getX()) < Math.sqrt(3) / 2) //left and right edges
        && deltaFromCenter.getY() < sideSlope * deltaFromCenter.getX() + 1 //top left edge
        && deltaFromCenter.getY() > sideSlope * deltaFromCenter.getX() - 1 //bottom right edge
        && deltaFromCenter.getY() < -sideSlope * deltaFromCenter.getX() + 1 //top right edge
        && deltaFromCenter.getY() > -sideSlope * deltaFromCenter.getX() - 1; //bottom left edge
  }

  /**
   * Gets the HexPosn of the nearest hexagon within the board to the given point (in logical
   * coordinates). Returns empty if the point is not contained within any of the cells on the
   * board.
   *
   * @param point the point in logical coordinates
   * @return the HexPosn of the hexagon in the board that the point lies in. If the point lies
   * outside all cells on the board, return Optional.empty()
   */
  private Optional<HexPosn> getNearestHexagonInBounds(Point2D point) {
    for (HexPosn pos : this.model.getAllValidCoordinates()) {
      if (pointIsInHexagon(point, pos)) {
        return Optional.of(pos);
      }
    }
    return Optional.empty();
  }

  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D g2d = (Graphics2D) graphics;

    g2d.transform(this.transformLogicalToPhysical());

    for (HexPosn pos : this.model.getAllValidCoordinates()) {
      this.drawReversiCell(g2d, pos);
      if (this.model.getPlayerAt(pos).isPresent()) {
        this.drawPlayerPiece(g2d, pos, this.model.getPlayerAt(pos).get());
      }
    }
  }

  /**
   * Represents a mouse listener for this Reversi view, which observes mouse events.
   */
  private class MouseEventsListener extends MouseInputAdapter {

    @Override
    public void mousePressed(MouseEvent e) {

      Point mousePoint = e.getPoint();
      //transform mouseX and mouseY to logical coordinates

      Point2D mouseXYLogical = transformPhysicalToLogical().transform(mousePoint, null);

      Optional<HexPosn> nearestHexagon = getNearestHexagonInBounds(mouseXYLogical);

      if (nearestHexagon.isPresent()) {
        System.out.println(nearestHexagon.get());
      } else {
        System.out.println("no hexagon clicked");
      }

      //if the selected cell is clicked on again, the cell will deselect
      if (nearestHexagon.isPresent() && nearestHexagon.get().equals(currentCellClicked)) {
        BasicReversiGUIPanel.this.currentCellClicked = null;
      } else {
        //if the user clicked inside the board, it will set the current cell clicked to the
        //nearest hexagon, otherwise it will deselect the current cell, if any.
        BasicReversiGUIPanel.this.currentCellClicked = nearestHexagon.orElse(null);
      }

      BasicReversiGUIPanel.this.repaint();
    }
  }

  /**
   * Represents a keyboard listener for this Reversi view, which observes key events.
   */
  private class KeyboardEventsListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
      if (keyTyped.containsKey(e.getKeyChar())) {
        keyTyped.get(e.getKeyChar()).run();
      }
    }

    @Override
    public void keyPressed(KeyEvent e) {
      if (keyPressed.containsKey(e.getKeyCode())) {
        keyPressed.get(e.getKeyCode()).run();
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {
      if (keyReleased.containsKey(e.getKeyCode())) {
        keyReleased.get(e.getKeyCode()).run();
      }
    }
  }
}
