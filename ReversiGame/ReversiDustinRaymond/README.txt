OVERVIEW

Reversi is a 2-player game where each person takes turns placing discs on a board to occupy the most
cells. Normally, the board is a square board consisting of square cells, but for this code, it is a
hexagonal board consisting of hexagonal cells. When a player places a disc on a cell, it will
occupy the space and capture the opponent's discs in the vicinity. Whichever player occupies the
most cells by the game's end wins.

More info: https://en.wikipedia.org/wiki/Reversi

For our code, we limited it to a 2-player game, where users can create a hexagonal board with a
side length (measured in # cells) of greater than or equal to 2.

As of 11/14, we have implemented a GUI based view, as well as various gameplay Strategies that can
be used to determine moves to play.

As of 11/27, we have implemented a controller that can transmit "game actions" such as attempted
moves or passes from the players/views to the model. Each player has their own controller, which is
responsible for notifying them whenever it is their turn and relaying their moves to the model.

A controller can handle any type of player, whether they be a human player or an AI that
automatically chooses moves based on a strategy.

-----------------------------------------------------------------------------------------------

QUICK START

RUN MAIN CLASS:
In the src/Reversi.java file, run the main method contained in the class. This will generate two
GUI views of the Reversi board, one for each player.

The default board has a side length of 6 cells. You can change the number of cells in the board by
changing the argument passed into the construction of the BasicReversi model:

    ReversiModel model = new BasicReversi(x);

where x is the custom side length of the board. Note: the object will not construct if the side
length given is less than 2.

COMMAND LINE ARGUMENTS:
By default, if no command-line arguments are given, the main method will initialize a game between
two human players. The main method can optionally accept two arguments. The first argument specifies
the type of player playing with the white pieces, and the second specifies the type of player
playing the black pieces.
- "human": a human player who interacts with the game through the corresponding GUI view
- "strategy1": an AI player that always chooses the move that captures the most pieces
- "strategy2": an AI player that chooses maximal-capture moves and attempts to avoid placing pieces
  in spaces adjacent to corners
- "strategy3": an AI player that prioritizes placing pieces in corners and chooses maximal-capture
  moves if no corner cells are available

MOVE/PASS:
For a human player, make a move by selecting a cell on the corresponding GUI and pressing [M]. If
the move is legal, the necessary captures will be performed, and both views will be updated
immediately. If the move is invalid for whatever reason, the move will fail, and the player will
be prompted to play again.

An AI player will automatically make moves when it is their turn to play. Selecting a cell on a
GUI corresponding to an AI player has no effect.

Players cannot move when it is not their turn. If a player attempts to move out of turn, the move
fails.

After a valid move, the player's turn automatically ends and the next player will be able to move.

STRATEGY:
To use a strategy to calculate a move, instantiate a ReversiStrategy object and a StrategyTiebreaker
object that takes in that ReversiStrategy. In the StrategyTiebreaker, call chooseOneMove, passing in
the model to work with, and the StrategyTiebreaker will return the HexPosn of that move, or empty,
if there aren't any moves.

We are using Axial coordinates to represent the board. For more info, visit this site:
https://www.redblobgames.com/grids/hexagons/
For any board, the middle cell is (0, 0).

-----------------------------------------------------------------------------------------------

KEY COMPONENTS

- The Reversi model (represented by ReversiModel interface) stores the state of the game internally.
The controller calls on the model to update the game state and retrieve relevant game data.

- The Reversi view (represented by ReversiGUIView interface) contains functionality to display a
GUI representation of the game. The view is a frame that contains a panel that takes in model data
and produces a visual representation of it.

- The panel contains all functionality to turn the model data and display it as a GUI view. The
panel retrieves relevant game data, translates it to a format suitable for GUIs to use,
and displays it.

- The controller (represented by ReversiController interface) is responsible for relaying
information between the model, view, and players and ensuring that the sequence of gameplay flows
smoothly.

- The player(s) (represented by ReversiPlayer interface) can be either human or AI driven. AI
players have the ability to tell the controller what to do. For human players, moves are decided
through user interactions in the view.

- The strategy (represented by ReversiStrategy and StrategyTiebreaker interfaces) together take
in a model and calculate a single move to play based on the model data.

KEY SUBCOMPONENTS
- The view relies on a model object and a panel to retrieve relevant game data for displaying.
    - BasicReversiGUIPanel - A class used to convert model data into a GUI display.
    - ReversiModel - The model that contains all game data.

- The model relies on two subcomponents: HexPosn and PlayerPiece to carry out its functionality.
    - HexPosn - An axial logicalCoordinate representation of a hexagonal logicalCoordinate, used to represent
        hexagonal coordinates of board.
    - PlayerPiece - An enumerated type used to represent both players as separate entities.

- The controller implements two interfaces, ReversiPlayerActions and ReversiModelFeatures, and
relies on a player object as a subcomponent.
    - ReversiPlayerActions, which allows it to receive and transmit actions done by players, and
    - ReversiModelFeatures, which allows it to subscribe to updates from the model.
    - ReversiPlayer - The player that the controller corresponds to.

- Players are represented as classes implementing the ReversiPlayer interface.
    - Human players are represented by the placeholder HumanPlayer class.
    - AI players are subclasses of the AIPlayer class and have strategies that are used to choose
        their moves.

- The strategy relies on a model object to calculate a move.

SOURCE ORGANIZATION
- Model component and all related subcomponents can be found in src/model package.
- View component and all related subcomponents can be found in src/view package
- Controller component and all related subcomponents can be found in src/controller package.
- Player components and all related subcomponents can be found in src/player package.
- Strategy components and all related subcomponents can be found in src/strategy package.
- Tests located in test folder

---------------------------------------------------------------
CHANGES FOR PART 2
In the ReversiModel

- Added a copy constructor in BasicReversi. This is in case we wanted to duplicate the BasicReversi
model. We have a constructor that takes in another BasicReversi and copies all the fields, making
sure no fields are aliases of one another.

- Added isLegalMoveAt method that takes in a HexPosn and returns a boolean indicating whether there
is a legal move at that position. This is so we can check if a given move is valid without actually
having to execute the move itself.

- Split the ReversiModel interface into a ReadOnlyReversiModel, with ReversiModel extending that
interface. This is so we can feed a ReadOnlyReveresiModel to the view and ensure that the view
cannot call methods that modify the internal data of the model.

In HexPosn

- Added a static method that calculates the distance between two HexPosns. This is used in tie
breaking when we need to compare distances to the top left corner.

---------------------------------------------------------------
CHANGES FOR PART 3
In the ReversiModel

- Added the ability for a model to add listeners that it can notify whenever important events
occur in the game, such as a player completing their turn.

- Added startGame method that ensures that there are exactly two player controllers listening to
the model, assigns each player their pieces, and notifies the controllers to begin playing the
game.

- Modified move and passTurn methods to notify the listeners upon completion so that players know
when turns have ended.

In the ReversiView

- Added a method for displaying an error message, such as if the player made an invalid move.

- Added a method for refreshing the view (upon a valid move being performed).

--------------------------------------