package digitalviews;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import model.Coordinate;
import model.DiscColor;

/**
 * A single digital hexagon tile on the game board.
 */
public class DrawnHexagon implements DrawnHexagonInterface {

  public static final double THETA = (Math.PI * 2) / 6.0;
  //Length or radius?
  static int hexagonLength = 25;
  private Polygon poly;
  private Coordinate hexCoordinate;
  private final double centerCoordY;
  private final double centerCoordX;
  private final Color color;
  private Color discColor;

  /**
   * Creates a new hexagon that has the game coordinates q and r, a color of clr.
   *
   * @param hexCoordinate     the coordintae of the hexagon in relation to the entire grid.
   * @param discColor         the disc color of the hexagon cell.
   * @param boardCenterCoordx the dead center or origin of the board in pixel coordinates.
   * @param boardCenterCoordy the dead center or origin of the board in pixel coordinates.
   * @param hexColor          the color of the background of the hex.
   * @param hexagonLength     the radius of the hexagon.
   */
  public DrawnHexagon(Coordinate hexCoordinate, DiscColor discColor, int boardCenterCoordx,
                      int boardCenterCoordy, Color hexColor, int hexagonLength) {
    double setCoordX;
    this.hexCoordinate = hexCoordinate;
    this.centerCoordY = boardCenterCoordy + (hexagonLength * 1.6) * (hexCoordinate.getIntR());
    double offsetX = hexCoordinate.getIntR() * hexagonLength * .9;
    setCoordX = boardCenterCoordx + (hexagonLength * 1.8) * (-hexCoordinate.getIntS());
    setCoordX -= offsetX;
    this.centerCoordX = setCoordX;
    if (discColor == DiscColor.BLACK) {
      this.discColor = Color.BLACK;
    } else if (discColor == DiscColor.WHITE) {
      this.discColor = Color.WHITE;
    } else {
      this.discColor = Color.LIGHT_GRAY;
    }
    this.hexagonLength = hexagonLength;
    this.color = hexColor;
    resetPolygon();
  }

  private void resetPolygon() {
    poly = new Polygon();
    for (int i = 0; i < 6; i++) {
      int x1 = (int) (centerCoordX + hexagonLength * Math.sin(THETA * i));
      int y1 = (int) (centerCoordY + hexagonLength * Math.cos(THETA * i));
      poly.addPoint(x1, y1);
    }
  }

  /**
   * Draws the hexagon on the graphic.
   *
   * @param g the graphic to be drawn on.
   */
  public void draw(Graphics g) {
    resetPolygon();
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
    g.drawOval(poly.getBounds().x + hexagonLength / 2, poly.getBounds().y + hexagonLength / 2,
            hexagonLength, hexagonLength);
    g.fillOval(poly.getBounds().x + hexagonLength / 2, poly.getBounds().y + hexagonLength / 2,
            hexagonLength, hexagonLength);
  }

  /**
   * the Y position of the center of the hexagon in relation to the panel it is drawn on.
   *
   * @return the Y position of the hexagon.
   */
  public double getY() {
    return centerCoordY;
  }

  /**
   * the X position of the center of the hexagon in relation to the panel it is drawn on.
   *
   * @return the X position of the hexagon.
   */
  public double getX() {
    return centerCoordX;
  }

  /**
   * The coordinate in (Q,R,S) form of the hexagon.
   *
   * @return the coordinates of the hex.
   */
  public Coordinate getHexCoordinate() {
    return this.hexCoordinate;
  }
}
