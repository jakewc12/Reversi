# Reversi for CS3500 project
A simple reversi game on a hexagonal board. This utilizes the Model, View Controller pattern and has both a GUI rendering of the game and a text rendering of the game.
When playing the game, to place a disc, click on a Hexagon and then press the key 'p'. To Skip a turn, press the key 's'. 
The game is executable through the Jar file through the command "java - jar ReversiGame.jar". 
Custom configurations to the game involve choosing if you want to use Human players or Machine players. For the configuration of the game, if there are no command-line inputs, the default players go as follows: player1 or BLACK is a human player while player2 or WHITE is a machine player using
strategy1. 
The command-line allows to change the configurations of the game to make player1 machine
or player2 human, however, if invalid inputs are given to the command-line, then the configurations
will resort to the default for the invalid inputs. For example, if the args are "strategy111" and
"human", the configuration will be human and human since the second input was valid. 
Options for invidual player custom configurations: "human" "strategy1" "providerStrategy1" "providerStrategy2" "providerStrategy3"
