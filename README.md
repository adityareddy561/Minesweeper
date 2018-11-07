# Minesweeper

Implementation of Minesweeper Game
Remember the old Minesweeper ?
We play on a square board and we have to click on the board on the cells which do not have a mine. And obviously we don’t know where mines are. If a cell where a mine is present is clicked then we lose, else we are still in the game.
There are three levels for this game-

easy – 9 * 9 Board and 10 Mines
medium – 16 * 16 Board and 40 Mines
hard – 24 * 24 Board and 99 Mines
Probability of finding a mine –

easy  level –  10/81 (0.12)
medium level – 40/256 (0.15)
hard level – 99 / 576 (0.17)
The increasing number of tiles raises the difficulty bar. So the complexity level increases as we proceed to next levels.

It might seem like a complete luck-based game (you are lucky if you don’t step over any mine over the whole game and unlucky if you have stepped over one). But this is not a complete luck based game. Instead you can win almost every time if you follow the hints given by the game itself.
