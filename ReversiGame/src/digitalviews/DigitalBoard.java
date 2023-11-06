package digitalviews;

import javax.swing.*;

import model.GameDisc;

public interface DigitalBoard {
  void setColor(int row, int col, GameDisc.DiscColor color);
}
