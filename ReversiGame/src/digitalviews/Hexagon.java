package digitalviews;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import model.DiscColor;

/**
 * A single digital hexagon tile on the game board.
 */
public class Hexagon {

  public final static double THETA = (Math.PI * 2) / 6.0;
  //Length or radius?
  static final int hexagonLength = 25;
  private final Polygon poly;
  private final int q;
  private final int r;
  private final int yCenterCoord;
  private final int xCenterCoord;
  private Color color;
  private Color discColor;

  /**
   * Creates a new hexagon that has the game coordinates q and r, a color of clr.
   *
   * @param q                the q coordinate which is the same as the one described in
   *                         model.HexagonCell.
   * @param r                the r coordinate which is the same as the one described in
   *                         model.HexagonCell.
   * @param discColor        the disc color of the hexagon cell.
   * @param boardCenterCoord the dead center or origin of the board in pixel coordinates.
   */
  public Hexagon(int q, int r, DiscColor discColor, int boardCenterCoord) {
    int xCoordMath;
    //use Q and R instead of x and y
    this.q = q;
    this.r = r;
    this.yCenterCoord = boardCenterCoord + 40 * (r);
    xCoordMath = boardCenterCoord + 45 * (-q - r);
    xCoordMath += r * 23;
    //need to move the y so that it off centers correctly.
    this.xCenterCoord = xCoordMath;
    if (discColor == DiscColor.BLACK) {
      this.discColor = Color.BLACK;
    } else if (discColor == DiscColor.WHITE) {
      this.discColor = Color.WHITE;
    } else {
      this.discColor = Color.LIGHT_GRAY;
    }
    color = Color.LIGHT_GRAY;
    poly = new Polygon();
    for (int i = 0; i < 6; i++) {
      int x1 = (int) (xCenterCoord + hexagonLength * Math.sin(THETA * i));
      int y1 = (int) (yCenterCoord + hexagonLength * Math.cos(THETA * i));
      poly.addPoint(x1, y1);
    }
  }

  /**
   * This would set the background color of the tile to cyan or back to light grey. This does NOT
   * change the color of the disc.
   *
   * @param clr the color you want the disc set to.
   */
  public void setColor(Color clr) {
    if (clr == Color.CYAN) {
      System.out.println("Coordinate clicked is: " + q + ", " + r + ", " + (-q - r));
    }
    color = clr;
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
    g.drawOval(poly.getBounds().x + 9, poly.getBounds().y + 12, hexagonLength, hexagonLength);
    g.fillOval(poly.getBounds().x + 9, poly.getBounds().y + 12, hexagonLength, hexagonLength);
  }

  public int getY() {
    return yCenterCoord;
  }

  public int getX() {
    return xCenterCoord;
  }

  public int getQ() {
    return this.q;
  }

  public int getR() {
    return this.r;
  }
}
