package digitalviews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;
import model.Coordinate;
import model.ReadOnlyReversiModel;

public class HexManager extends JComponent implements DigitalBoardManager {

  private final int size;
  private List<HexagonInterface> hexagons = new ArrayList<>();
  private final int centerCord;
  private boolean hexClicked = false;
  /**
   * everytime something happens with model, we should reset hexagons. this way, we can redraw the
   * tiles if multiple change at once.
   */
  private final ReadOnlyReversiModel model;
  private final HexManager manager = this;

  public HexManager(int size, int windowSize, ReadOnlyReversiModel model) {
    this.setPreferredSize(new Dimension(windowSize, windowSize));
    this.setOpaque(false);
    this.addMouseListener(new MouseEventsListener());
    this.setBackground(Color.DARK_GRAY);
    centerCord = windowSize / 2;
    this.size = size;
    this.model = model;
    makeHexagons();
    this.repaint();
  }

  /**
   * looks at model and creates the hexagon accordingly. We should call this everytime a move is
   * made.
   */
  private void makeHexagons() {
    hexagons = new ArrayList<>();
    List<Coordinate> coordinates = model.getAllCoordinates();
    for (Coordinate coordinate : coordinates) {
      hexagons.add(new Hexagon(coordinate, model.getColorAt(coordinate), centerCord));
    }
  }

  /**
   * Set the color for a disc within a hexagon using the views x and y coordinates.
   *
   * @param x     the x position in the view.
   * @param y     the y position in the view.
   * @param color the color you want the disc to be set too.
   */
  public void setColor(int x, int y, Color color) {
    for (HexagonInterface hex : hexagons) {
      if ((Math.abs(y - hex.getY()) <= 18) && (Math.abs(x - hex.getX()) <= 18)) {
        hex.setColor(color);
      }
    }
    repaint();
  }

  private void setAllHexesToLightGrey() {
    for (HexagonInterface hex : hexagons) {
      hex.setColor(Color.LIGHT_GRAY);
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
    this.setBackground(Color.DARK_GRAY);
    g.setColor(Color.DARK_GRAY);
    g.fillRect(0, 0, getWidth(), getHeight());
    for (HexagonInterface hex : hexagons) {
      hex.draw(g);
    }
  }

  @Override
  public void refresh() {
    this.makeHexagons();
    this.repaint();
  }

  private class MouseEventsListener extends MouseInputAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
      if (!hexClicked) {
        hexClicked = checkClickedLocationOnAHex(e.getX(), e.getY());
      } else {
        hexClicked = false;
      }
      if (hexClicked) {
        manager.setColor(e.getX(), e.getY(), Color.CYAN);
      } else {
        setAllHexesToLightGrey();
      }
      manager.repaint();
    }
  }
}
