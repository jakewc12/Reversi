package model;

/**
 * Features any reversi model should possess such that the controllers can update the view.
 */
public interface ModelStatus {

  void moveWasPlayed();
}
