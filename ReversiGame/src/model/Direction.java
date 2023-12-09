package model;

/**
 * A direction that relates to a game hex. This direction can be used to find neighboring game
 * cells or coordinates.
 */
public interface Direction {

  int[] vector();
}
