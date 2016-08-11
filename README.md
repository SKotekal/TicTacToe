#Tic-Tac-Toe

Simple Tic-Tac-Toe AI for `NxN` grid that is quite strong. Implemented without DFS but rather simple assignment of scoring rows, columns, and diaganols, where `+1` is in favor of the AI, and `-1` is in favor of the opponent. Once a row/column/diaganol (R/C/D) has more than one type of piece, the score for that R/C/D is set to a sentinel value, meaning that R/C/D has no propsect of victory. The decision tree is a simple priority list.

1. Win. If the score of a certain R/C/D is `N-1`, play in that respective R/C/D. 
2. Block opponent win. If the score of a certain R/C/D/ is `-(N-1)`, play in that respective R/C/D.
3. Setup a fork, which forces a win on the next turn. There should exist two R/C/D that should be `N-2`. Play in the intersection of those.
4. Block an opponent fork.
5. Maximize aggregate score of R/C/D.

The AI plays on any `NxN` board. The game is played via console.