package controllertest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controller.ReversiController;
import controller.ReversiControllerImp;
import digitalviews.DigitalReversiWindow;
import digitalviews.DigitalWindow;
import model.DiscColor;
import model.MutableReversi;
import model.MutableReversiModel;
import player.HumanPlayer;
import player.Player;

/**
 * Tests potential issues with the controller for Reversi.
 */
public class ReversiControllerTests {
  private ReversiController controller;
  private MutableReversiModel model;
  private Player player;
  private DigitalWindow view;

  @Test
  public void testInvalidModel() {
    Assert.assertThrows(NullPointerException.class, () -> new ReversiControllerImp(null
            , new HumanPlayer(null, DiscColor.BLACK), new DigitalReversiWindow(null)));
    Assert.assertThrows(IllegalArgumentException.class
            , () -> new ReversiControllerImp(new MutableReversi(-1)
                    , new HumanPlayer(new MutableReversi(-1)
                    , DiscColor.BLACK), new DigitalReversiWindow(new MutableReversi(-1))));
  }
}
