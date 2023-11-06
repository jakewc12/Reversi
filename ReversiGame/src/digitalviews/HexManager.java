package digitalviews;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import model.GameDisc;
import model.ReadOnlyReversiModel;

public class HexManager extends JComponent {
  private int size;
  private List<Hexagon> hexagons = new ArrayList<>();
  private int centerCord;
  private boolean hexClicked = false;
  /**
   * everytime something happens with model, we should reset hexagons.
   * this way, we can redraw the tiles if multiple change at once.
   */
  private ReadOnlyReversiModel model;
  private HexManager manager = this;

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
   * looks at model and creates the hexagon accordingly. We should call this everytime
   * a move is made.
   */
  private void makeHexagons() {
    for (int r = -size; r <= size; r++) {
      for (int q = -size; q <= size; q++) {
        for (int s = -size; s <= size; s++) {
          if (r + q + s == 0) {
            int x = centerCord + 50 * r;
            int y = centerCord + 50 * q;
            hexagons.add(new Hexagon(x, y, model.getColorAt(q, r, s)));
          }
        }
      }
    }
  }

  /**
   * Set the color for a disc within a hexagon.
   *
   * @param row   the row position
   * @param col   the column position
   * @param color the color to set
   */
  public void setColor(int row, int col, Color color) {
    System.out.println(color.toString());
    for (Hexagon hex : hexagons) {
      if ((Math.abs(hex.getX() - row) <= 10) && (Math.abs(hex.getY() - col) <= 10)) {
        hex.setColor(color);
      }
    }
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    this.setBackground(Color.DARK_GRAY);
    g.setColor(Color.DARK_GRAY);
    g.fillRect(0, 0, getWidth(), getHeight());
    for (Hexagon hs : hexagons) {
      hs.draw(g);
    }
  }

  private class MouseEventsListener extends MouseInputAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
      System.out.println(e.getX() + ", " + e.getY());
      hexClicked = !hexClicked;
      if (hexClicked) {
        manager.setColor(e.getX(), e.getY(), Color.CYAN);
      } else {
        //manager.setColor(e.getX(), e.getY(), Color.LIGHT_GRAY);
        for (Hexagon hex : hexagons) {
          manager.setColor(hex.getX(), hex.getY(), Color.LIGHT_GRAY);
        }
      }
      manager.repaint();
    }
  }

}
