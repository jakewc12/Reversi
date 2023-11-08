package digitalviews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;
import model.ReadOnlyReversiModel;

public class HexManager extends JComponent implements DigitalBoard {

  private final int size;
  private final List<Hexagon> hexagons = new ArrayList<>();
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
    for (int r = -size; r <= size; r++) {
      for (int q = -size; q <= size; q++) {
        for (int s = -size; s <= size; s++) {
          if (r + q + s == 0) {
            //int x = centerCord + 50 * q;
            //int y = centerCord + 45 * r;
            //System.out.println(q + ", " +r);
            hexagons.add(new Hexagon(q, r, model.getColorAt(q, r, s), centerCord));
          }
        }
      }
    }
  }

  /**
   * Set the color for a disc within a hexagon.
   *
   * @param x     the x position
   * @param y     the column position
   * @param color the color to set
   */
  public void setColor(int x, int y, Color color) {
    for (Hexagon hex : hexagons) {
      if ((Math.abs(y - hex.getY()) <= 18) && (Math.abs(x - hex.getX()) <= 18)) {
        hex.setColor(color);
      }
    }
    repaint();
  }

  private void setAllHexesToLightGrey() {
    for (Hexagon hex : hexagons) {
      hex.setColor(Color.LIGHT_GRAY);
    }
  }

  private boolean checkClickedLocationOnAHex(int x, int y) {
    for (Hexagon hex : hexagons) {
      if ((Math.abs(y - hex.getY()) <= 21) && (Math.abs(x - hex.getX()) <= 21)) {
        return true;
      }
    }
    return false;
  }


  @Override
  protected void paintComponent(Graphics g) {
    //Graphics2D g2d = (Graphics2D)g;
    //g2d.scale(1, 1);
    //Rectangle bounds = this.getBounds();
    //g2d.translate(0, bounds.height);
    this.setBackground(Color.DARK_GRAY);
    g.setColor(Color.DARK_GRAY);
    g.fillRect(0, 0, getWidth(), getHeight());
    for (Hexagon hex : hexagons) {
      hex.draw(g);
    }
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
