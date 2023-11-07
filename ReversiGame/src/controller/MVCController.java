package controller;

import java.util.Scanner;

import digitalviews.DigitalWindow;
import model.MutableReversiModel;

public class MVCController implements ReversiController {
  private MutableReversiModel model;
  private DigitalWindow view;

  public MVCController(MutableReversiModel model, DigitalWindow view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public String processCommand(String command) {
    StringBuilder output = new StringBuilder();
    Scanner scan = new Scanner(command);
    while (scan.hasNext()) {
      String in = scan.next();
      if (!model.gameOver()) {
        if (in.equalsIgnoreCase("place")) {
          int q = scan.nextInt();
          int r = scan.nextInt();
          int s = scan.nextInt();
          try {
            model.placeDisc(q, r, s);
            output.append("Placed at (").append(q)
                    .append(", ").append(r).append(", ").append(s).append(")");
            System.out.println("Placed at (" + q + ", " + r + ", " + s + ")");
          } catch (IllegalArgumentException e) {
            output.append("Invalid move. Illegal numbers given for q,r,s");
          } catch (IllegalStateException e) {
            output.append("Invalid move. Illegal move played");
            System.out.println("Invalid move. Illegal move played");
          }
        }else if(in.equals("skip")) {
          try{
            model.skipCurrentTurn();
          }catch(Exception e){
            output.append("Connection with model failed");
          }
        }else{
          output.append("Invalid command given");
        }
      }
    }
    return output.toString();
  }

  @Override
  public void go() {
    this.view.setCommandCallback(this::accept);
    this.view.refresh();
    this.view.makeVisible();
  }

  public void accept(String command) {
    try {
      processCommand(command);
    } catch (Exception ex) {
      view.showErrorMessage(ex.getMessage());
    }
  }
}
