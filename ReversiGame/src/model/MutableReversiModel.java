package model;

public interface MutableReversiModel extends ReadOnlyReversiModel{

  void placeDisc();
  void skipCurrentTurn();

}
