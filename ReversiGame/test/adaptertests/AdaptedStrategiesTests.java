package adaptertests;

import DustinRaymondReversi.strategy.AvoidCornerTrapStrategy;
import DustinRaymondReversi.strategy.ChooseCornerStrategy;
import DustinRaymondReversi.strategy.ChosenByBothStrategyCombinator;
import DustinRaymondReversi.strategy.FallbackStrategyCombinator;
import DustinRaymondReversi.strategy.GreedilyCaptureStrategy;
import adaptation_assignment.RaDusModelAdapter;
import adaptation_assignment.RaDusStrategyAdapter;
import controller.ReversiController;
import helpers.TestHelper;
import model.Coordinate;
import model.Coordinate;
import model.DiscColor;
import model.MutableReversi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import player.HumanPlayer;
import player.Player;
import player.ReversiStrategy;

public class AdaptedStrategiesTests {

  MutableReversi model;

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
    Assert.assertEquals(new Coordinate(-2, 1, 1),
        strategyCornerTrap.chooseMove(model, playerBlack).get());
    TestHelper.printModelBoard(model);

  }


}
