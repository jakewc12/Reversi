package digitalviews.squarereversi;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.*;

import digitalviews.DigitalBoardManager;
import digitalviews.DrawnHexagon;
import digitalviews.DrawnShape;
import digitalviews.hexreversi.HexManager;
import model.Coordinate;
import model.ReadOnlyReversiModel;
import model.hexreversi.LogicalHexCoordinate;
import model.squarereversi.SquareCoordinate;

public class SquareManager extends JPanel implements DigitalBoardManager {
  private boolean hintsEnabled;
  private boolean squareClicked;
  private Optional<SquareCoordinate> highlightedCord;
  private int centerCordX;
  private int centerCordY;
  private final ReadOnlyReversiModel model;
  private final SquareManager manager = this;
  private List<DrawnSquare> hexagons = new ArrayList<>();

  public SquareManager(int windowWidth, int windowHeight, ReadOnlyReversiModel model) {
    if (model == null || windowHeight < 1 || windowWidth < 1) {
      throw new IllegalArgumentException("Invalid inputs for HexManager");
    }
    this.setPreferredSize(new Dimension(windowWidth, windowHeight));
    this.setOpaque(false);
    this.setBackground(Color.DARK_GRAY);
    centerCordX = windowWidth / 2;
    centerCordY = windowHeight / 2;
    this.model = model;
    highlightedCord = Optional.empty();

    this.repaint();
  }

  /**
   * Signals the view to draw or redraw itself.
   */
  @Override
  public void refresh() {
    this.repaint();
  }

  /**
   * Returns the (Q,R,S) coordinate of the current cell that is highlighted.
   *
   * @return the current highlighted cell on the board.
   */
  @Override
  public Optional<Coordinate> getHighlightedCord() {
    return Optional.empty();
  }

  /**
   * Shows hints of how many flips would happen if the player places on highlighted cord.
   */
  @Override
  public void enableHints() {
    hintsEnabled = !hintsEnabled;
  }

  /**
   * True if a tile is currently highlighted, false else.
   *
   * @return if a tile is highlighted.
   */
  @Override
  public boolean tileCurrentlyClicked() {
    return false;
  }

  /**
   * Returns all of the shapes in the manager.
   *
   * @return all of the drawnshapes.
   */
  @Override
  public List<DrawnShape> getShapes() {
    return null;
  }
}
