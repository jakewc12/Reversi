package digitalviews;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import model.GameDisc;

/**
 * A Single hexagon tile in the game board
 */
public class Hexagon {

  private Polygon poly;
  private Color color;
  private Color discColor;
  private int q;
  private int r;
  private int y;
  private int x;

  //Length or radius?
  static final int hexagonLength = 25;
  private int centerCord;
  public final static double THETA = (Math.PI * 2) / 6.0;

  /**
   * create a hexagon centered at x,y having color clr
   *
   * @param q the column
   * @param r the row
   */
  public Hexagon(int q, int r, GameDisc.DiscColor clr, int centerCord) {
    //use Q and R instead of x and y
    this.centerCord = centerCord;
    this.q = q;
    this.r = r;
    this.y = centerCord + 50 * (r);
    this.x = centerCord + 44 * (-q - r);
    //need to move the y so that it off centers correctly.
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
      int x1 = (int) (x + hexagonLength * Math.sin(THETA * i));
      int y1 = (int) (y + hexagonLength * Math.cos(THETA * i));
      poly.addPoint(x1, y1);
    }
  }

  /**
   * this would set the color of the tile to cyan or back to light grey. This does NOT change the
   * color of the disc.
   *
   * @param clr
   */
  public void setColor(Color clr) {
    color = clr;
    if (color == Color.CYAN) {
      System.out.println("Hex clicked on was at (" + q + ", " + r + ", " + (-q - r) + ")");
    }
  }

  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    drawHex(g2d);
    drawDisc(g2d);
  }

  private void drawHex(Graphics2D g) {
    g.setColor(Color.BLACK);
    g.drawPolygon(poly);
    g.setColor(color);
    g.fillPolygon(poly);
  }

  private void drawDisc(Graphics2D g) {
    if (discColor != Color.WHITE && discColor != Color.BLACK) {
      discColor = color;
    }
    g.setColor(discColor);
    //g.drawOval(poly.getBounds.x+10, poly.getBounds.y+10, hexagonLength - 10, hexagonLength - 10);
    //    g.fillOval(poly.getBounds.x+10, poly.getBounds.y+10, hexagonLength - 10, hexagonLength - 10);
    g.drawOval(x - 10, y - 10, hexagonLength - 10, hexagonLength - 10);
    g.fillOval(x - 10, y - 10, hexagonLength - 10, hexagonLength - 10);
  }

  public int getY() {
    return y;
  }

  public int getX() {
    return x;
  }

  public int getQ() {
    return this.q;
  }

  public int getR() {
    return this.r;
  }
}
