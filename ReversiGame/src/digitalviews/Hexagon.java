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
  private int q;
  private int r;
  private int x;
  private int y;

  private final int hexagonLength = 25;
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
    this.x = centerCord + 50 * (r);
    this.y = centerCord + 44*(q+r);
    //x is really the y and y is rly x with some calculation
    //System.out.println(q + ", " + r);
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
   *
   * @param clr
   */
  public void setColor(Color clr) {
    color = clr;
  }

  public void draw(Graphics g) {
    drawHex(g);
    drawDisc(g);
    /*
    if(discColor == Color.BLACK) {
      System.out.println("black at "+ this.getQ() + ", "+ this.getR());
    }else if(discColor == Color.WHITE){
      System.out.println("white at "+ this.getQ() + ", "+ this.getR());
    }
     */

  }

  private void drawHex(Graphics g) {
    g.setColor(Color.BLACK);
    g.drawPolygon(poly);
    g.setColor(color);
    g.fillPolygon(poly);
  }

  private void drawDisc(Graphics g) {
    g.setColor(discColor);
    g.drawOval(x - 10, y - 10, hexagonLength - 10, hexagonLength - 10);
    g.fillOval(x - 10, y - 10, hexagonLength - 10, hexagonLength - 10);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getQ() {
    return this.q;
  }

  public int getR() {
    return this.r;
  }
}
