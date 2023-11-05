package digitalviews;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;

import model.GameDisc;

public class HexCellPanel extends JPanel {
  private GameDisc.DiscColor color;
  private boolean clicked;
  private int q;
  private int r;
  private int s;
  private int radius;
  private Polygon polygon;
  private final int[] xPoints = {50, 100, 150, 100};
  private final int[] yPoints = {50, 100, 50, 0};

  public HexCellPanel(GameDisc.DiscColor color, int q, int r, int s, int radius) {
    this.color = color;
    this.q = q;
    this.r = r;
    this.s = s;
    this.radius = radius;
    //this.setPreferredSize(new Dimension(50, 50));
    //this.setBackground(new Color(0,0,0,0));
    //this.setBorder(new EmptyBorder(0,0,0,0));
  }

  private Path2D.Double path;
  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    System.out.println("made");

    path = new Path2D.Double();
    path.moveTo(50, 50); // Move to the starting point (x, y)
    path.lineTo(100, 50); // Draw a line to (x, y)
    path.lineTo(75, 100); // Draw another line to (x, y)
    path.closePath(); // Close the path to form a polygon
    this.addMouseListener(new MouseEventsListener());
    g2d.setColor(Color.BLUE);
    g2d.draw(path);
    g2d.fill(path);



  /*
    g2d.translate(0, this.getHeight());
    g2d.scale(1, -1);

    int[] xPoints = new int[6];
    int[] yPoints = new int[6];

    for (int i = 0; i < 6; i++) {
      double angle = 2 * Math.PI / 6 * i;
      xPoints[i] = (int) (30 + (10*radius) * Math.sin(angle));
      yPoints[i] = (int) (30 + (10*radius) * Math.cos(angle));

    }
    polygon = new Polygon(xPoints, yPoints, 6);
    this.addMouseListener(new MouseEventsListener());
    g2d.setColor(Color.GRAY);
    g2d.fillPolygon(polygon);
    g2d.setColor(Color.BLACK);
    g2d.drawPolygon(polygon);

   */

  }

  private void setColor(Graphics g) {
    if (!clicked) {
      //set color to cyan if clicked
      g.setColor(Color.CYAN);
      g.drawPolygon(polygon);
      g.fillPolygon(polygon);
      System.out.println("set polygon");

    } else {
      g.setColor(Color.GRAY);
    }
    clicked = !clicked;
  }

  public Dimension getPreferredSize() {
    return new Dimension(50,50);
  }

  private class MouseEventsListener extends MouseInputAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
      System.out.println("Cell that was clicked was at Q " + q + " R " + r + " S " + s);
      System.out.println("pressed");
    }
  }
}
