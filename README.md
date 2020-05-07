# ChessApp
A terminal based chess application made with Java. 

Assignment Description:

For this assignment you will explore how to apply the object-oriented design ideas you learned in class to design and implement the chess game.

------

- https://www.cs.rutgers.edu/courses/213/classes/spring_2020_venugopal/chess/chess.html#faq)

------

### Output

The board should be drawn on the screen with ascii art EXACTLY as shown in this [example](https://www.cs.rutgers.edu/courses/213/classes/spring_2020_venugopal/chess/display.txt). 

You MUST have white make the first move. Having black make the first move is not appropriate, and will incur a penalty.

**Note:**

- Every piece must know what moves are allowed on it. If a player attempts an illegal move on a piece, your program should not execute the move. Instead, it should print "Illegal move, try again", followed by the usual prompt (for white's move or black's move).

  

- When a move is made, and it puts the opponent's King under check, your program should print "Check" before prompting for the opponent's move.

  

- If a checkmate is detected, your program should print "Checkmate"

  

- The last thing before termination should be a display of "Black wins", "White wins" or "draw".



### Input

Your program needs to accept input of the form "FileRank FileRank", where the first file (column) and rank (row) are the coordinates of the piece to be moved, and the second file and rank are the coordinates of where it should end up. (See the board example shown above.)

The figure immediately below should make it clear which rank and file combinations belong to which squares. The white pieces always intially occupy ranks 1 and 2. The black pieces always initially occupy ranks 7 and 8. The queen always starts on the d file.

![img](https://www.cs.rutgers.edu/courses/213/classes/spring_2020_venugopal/chess/SCD_algebraic_notation.svg)

A castling move is indicated by specifying where the king begins and ends. So, white castling king's side would be "e1 g1".

A pawn promotion is indicated by putting the piece to be promoted to after the move. So, promoting a pawn to a knight might be "g7 g8 N". If no promotion piece is indicated, it is assumed to be a queen.

[Example of black winning](https://www.cs.rutgers.edu/courses/213/classes/spring_2020_venugopal/chess/ex1.txt)

### Ending the game

- If checkmate occurs, the game shall end immediately with the result reported.

  

- A player may resign by entering "resign".

  

  - [Example of white resigning](https://www.cs.rutgers.edu/courses/213/classes/spring_2020_venugopal/chess/ex_res_w.txt)
  - [Example of black resigning](https://www.cs.rutgers.edu/courses/213/classes/spring_2020_venugopal/chess/ex_res_b.txt)

- A player may offer a draw by appending "draw?" to the end of an otherwise regular move. The draw may be accepted by the other player submitting "draw" as the entirety of his or her next move. There will be no automatic draws (due to unchangeing positions over long periods of time, etc).

  - [Example of a draw](https://www.cs.rutgers.edu/courses/213/classes/spring_2020_venugopal/chess/ex_draw.txt)

You are NOT required to implement termination by threefold repetition, or the fifty-move rule. (You are welcome to include them in your code to make it complete; however, there is no extra credit for either.)
