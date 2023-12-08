package adaptertests;

import dustinraymondreversi.strategy.AvoidCornerTrapStrategy;
import dustinraymondreversi.strategy.ChooseCornerStrategy;
import dustinraymondreversi.strategy.ChosenByBothStrategyCombinator;
import dustinraymondreversi.strategy.FallbackStrategyCombinator;
import dustinraymondreversi.strategy.GreedilyCaptureStrategy;
import adaptionassignment.RaDusModelAdapter;
import adaptionassignment.RaDusStrategyAdapter;
import controller.ReversiController;
import helpers.TestHelper;
import model.hexreversi.HexCoordinate;
import model.DiscColor;
import model.hexreversi.MutableHexReversi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import player.HumanPlayer;
import player.Player;
import player.ReversiStrategy;

/**
 * Tests potential issues with adapting classes to our clients code.
 */
public class AdaptedStrategiesTests {

  MutableHexReversi model;

  Player playerBlack;
  Player playerWhite;

  ReversiStrategy strategyCornerTrap;
  ReversiStrategy strategyChooseCorner;
  ReversiStrategy strategyCombinator;
  ReversiStrategy fallBackStratCombinator;
  ReversiStrategy captureMostStrategy;

  ReversiController controller;


  @Before
  public void init() {
    //we will be using our model and our controllers and our AI players but their strategies
    model = new RaDusModelAdapter(3);

    playerBlack = new HumanPlayer(model, DiscColor.BLACK);
    playerWhite = new HumanPlayer(model, DiscColor.WHITE);

    strategyCornerTrap = new RaDusStrategyAdapter(new AvoidCornerTrapStrategy());
    strategyChooseCorner = new RaDusStrategyAdapter(new ChooseCornerStrategy());
    strategyCombinator = new RaDusStrategyAdapter(
        new ChosenByBothStrategyCombinator(new GreedilyCaptureStrategy(),
            new ChooseCornerStrategy()));
    fallBackStratCombinator = new RaDusStrategyAdapter(
        new FallbackStrategyCombinator(new ChooseCornerStrategy(), new GreedilyCaptureStrategy()));
    captureMostStrategy = new RaDusStrategyAdapter(new GreedilyCaptureStrategy());

  }

  @Test
  public void adapterWorksAsExpected() {
    model.setUpGame(model.getBoard());
    model.startGame();
    Assert.assertEquals(new HexCoordinate(-2, 1, 1),
        strategyCornerTrap.chooseMove(model, playerBlack).get());
    TestHelper.printModelBoard(model);

  }


}
