# Project 2: Board Game

#### In this project you will help determine the probability of winning a board game. Your program will represent the board in a double linked list. Your program will also simulate playing the game with 1,2,3 and finally 4 players.  The program will output the average number of moves necessary for one of the players to “win” the game.

## Board Game Rules:

### A player begins the game on the Start circle. The current player rolls a dice, that gives a number from 1 to 6. The current player moves that many colored blocks from the current player’s position. 
### If the square is unoccupied, then the current player adds the number on the square to the player’s total. If the square already has a previous player on it, the previous player has to go back 7 spaces  , and the current player adds the number on the square to the current player’s total. 
### If a player moves back 7 spaces and shares a block with another player, that’s ok- no additional processing. If the player is moved back beyond the first square, put the player on Start circle.
