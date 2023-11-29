package model;

/**
 * Features any reversi model should possess such that the controllers can update the view.
 */
public interface ModelStatus {

  /**
   * Allows the model to tell controllers that a move was played so the controller can update view.
   */
  void moveWasPlayed();
}
