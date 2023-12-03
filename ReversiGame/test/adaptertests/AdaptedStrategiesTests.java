package adaptertests;

import DustinRaymondReversi.strategy.AvoidCornerTrapStrategy;
import DustinRaymondReversi.strategy.ChooseCornerStrategy;
import DustinRaymondReversi.strategy.ChosenByBothStrategyCombinator;
import adaptation_assignment.RaDusModelAdapter;
import adaptation_assignment.RaDusStrategyAdapter;
import controller.ReversiController;
import model.MutableReversi;
import org.junit.Before;
import org.junit.Test;
import player.MachinePlayer;
import player.ReversiStrategy;

public class AdaptedStrategiesTests {

  MutableReversi model;

  MachinePlayer machinePlayer;

  ReversiStrategy strategyCornerTrap;
  ReversiStrategy strategyChooseCorner;
  ReversiStrategy strategyCombinator;
  ReversiStrategy fallBackStratCombinator;
  ReversiStrategy captureMostCombinator;
  ReversiStrategy topLeftTieBreaker;

  ReversiController controller;


  @Before
  public void init() {
    //we will be using our model and our controllers and our AI players but their strategies
    model = new RaDusModelAdapter(3);
    model.setUpGame(model.getBoard());

    strategyCornerTrap = new RaDusStrategyAdapter(new AvoidCornerTrapStrategy());
    strategyChooseCorner = new RaDusStrategyAdapter(new ChooseCornerStrategy());
    strategyCombinator = new RaDusStrategyAdapter(new ChosenByBothStrategyCombinator());
    fallBackStratCombinator = new RaDusStrategyAdapter(new ChosenByBothStrategyCombinator());;
    captureMostCombinator =new RaDusStrategyAdapter(new ChosenByBothStrategyCombinator());;
    topLeftTieBreaker =new RaDusStrategyAdapter(new ChosenByBothStrategyCombinator());;


        model.startGame();
  }

  @Test
  public void adapterWorksAsExpected() {

  }


}
