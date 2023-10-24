package model;

public interface ReadOnlyReversiModel {
  String getDiscAt(int row, int column);

  boolean gameOver();


  //figure out how to do coloring in the model instead of card class.
}
