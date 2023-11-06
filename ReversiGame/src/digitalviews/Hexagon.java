package digitalviews;

import java.awt.*;

import model.GameDisc;

/**
 * A Single hexagon tile in the game board
 */
public class Hexagon {

  private Polygon poly;
  private Color color;
  private Color discColor;
  private int x;
  private int y;

  public static int hexagonLength = 25;
  public final static double THETA = (Math.PI * 2) / 6.0;

  /**
   * create a hexagon centered at x,y having color clr
   *
   * @param x the column
   * @param y the row
   */
  public Hexagon(int x, int y, GameDisc.DiscColor clr) {
    this.x = x;
    this.y = y;
    if (clr == GameDisc.DiscColor.BLACK) {
      discColor = Color.BLACK;
    } else if (clr == GameDisc.DiscColor.WHITE) {
      discColor = Color.WHITE;
    } else {
      discColor = Color.LIGHT_GRAY;
    }
    color = Color.LIGHT_GRAY;
    poly = new Polygon();
    for (int i = 0; i < 6; i++) {
      int x1 = (int) (y + hexagonLength * Math.sin(THETA * i));
      int y1 = (int) (x + hexagonLength * Math.cos(THETA * i));
      poly.addPoint(x1, y1);
    }
  }

  /**
   * this would set the color of the tile to cyan or back to light grey.
   * This does NOT change the color of the disc.
   * @param clr
   */
  public void setColor(Color clr) {
    if (clr == Color.CYAN) {
      color = Color.CYAN;
    } else {
      color = Color.LIGHT_GRAY;
    }
  }

  public Polygon getPolygon() {
    return poly;
  }

  public void draw(Graphics g) {
    g.setColor(Color.BLACK);
    g.drawPolygon(poly);
    g.setColor(color);
    g.fillPolygon(poly);

    g.setColor(discColor);
    g.drawOval(x - 5, y - 5, hexagonLength - 10, hexagonLength - 10);
    g.fillOval(x - 5, y - 5, hexagonLength - 10, hexagonLength - 10);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Color getColor() {
    return this.color;
  }
}
