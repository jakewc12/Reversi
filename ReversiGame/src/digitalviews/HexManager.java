package digitalviews;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import model.Coordinate;
import model.ReadOnlyReversiModel;

public class HexManager extends JPanel implements DigitalBoardManager {

  private final int size;
  private List<HexagonInterface> hexagons = new ArrayList<>();
  private int centerCordX;
  private int centerCordY;
  private Coordinate highlightedCord;
  int hexLength;
  private boolean hexClicked = false;
  /**
   * everytime something happens with model, we should reset hexagons. this way, we can redraw the
   * tiles if multiple change at once.
   */
  private final ReadOnlyReversiModel model;
  private final HexManager manager = this;

  public HexManager(int size, int windowWidth, int windowHeight, ReadOnlyReversiModel model) {
    this.setPreferredSize(new Dimension(windowWidth, windowHeight));
    this.setOpaque(false);
    this.addMouseListener(new MouseEventsListener());
    this.setBackground(Color.DARK_GRAY);
    centerCordX = windowWidth / 2;
    centerCordY = windowHeight / 2;
    this.size = size;
    this.model = model;
    makeHexagons();
    highlightedCord = new Coordinate(
            model.getBoardRadius() + 1
            , model.getBoardRadius() + 1, model.getBoardRadius() + 1);
    this.repaint();
  }

  /**
   * looks at model and creates the hexagon accordingly. We should call this everytime a move is
   * made.
   */
  private void makeHexagons() {
    hexagons = new ArrayList<>();
    for (int r = -size; r <= size; r++) {
      for (int q = -size; q <= size; q++) {
        for (int s = -size; s <= size; s++) {
          if (r + q + s == 0) {
            Coordinate coordinate = new Coordinate(q, r, s);
            int hexLength = (Math.min(centerCordX, centerCordY)) / (2 * model.getBoardRadius() + 1);
            if (coordinate.equals(highlightedCord)) {
              hexagons.add(new Hexagon(coordinate
                      , model.getColorAt(coordinate)
                      , centerCordX, centerCordY, Color.CYAN, hexLength));
            } else {
              hexagons.add(new Hexagon(coordinate
                      , model.getColorAt(coordinate)
                      , centerCordX, centerCordY, Color.LIGHT_GRAY, hexLength));
            }
          }
        }
      }
    }
  }

  private boolean checkClickedLocationOnAHex(int x, int y) {
    for (HexagonInterface hex : hexagons) {
      if ((Math.abs(y - hex.getY()) <= 22.5) && (Math.abs(x - hex.getX()) <= 22.5)) {
        return true;
      }
    }
    return false;
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    this.centerCordY = getHeight() / 2;
    this.centerCordX = getWidth() / 2;
    makeHexagons();
    g2d.scale(1, 1);
    this.setBackground(Color.DARK_GRAY);
    g2d.setColor(Color.DARK_GRAY);
    g2d.fillRect(0, 0, getWidth(), getHeight());
    for (HexagonInterface hex : hexagons) {
      hex.draw(g2d);
    }
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  private class MouseEventsListener extends MouseInputAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
      highlightedCord = new Coordinate(
              model.getBoardRadius() + 1
              , model.getBoardRadius() + 1, model.getBoardRadius() + 1);
      if (!hexClicked) {
        hexClicked = checkClickedLocationOnAHex(e.getX(), e.getY());
      } else {
        hexClicked = false;
        highlightedCord = new Coordinate(
                model.getBoardRadius() + 1
                , model.getBoardRadius() + 1, model.getBoardRadius() + 1);
      }
      if (hexClicked) {
        for (HexagonInterface hex : hexagons) {
          if ((Math.abs(e.getY() - hex.getY()) <= 18) && (Math.abs(e.getX() - hex.getX()) <= 18)) {
            highlightedCord = hex.getCoordinate();
          }
        }
        System.out.println("Highlighted cell at " + highlightedCord.toString());
      }
      manager.repaint();
    }
  }
}
