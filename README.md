<h3>Chess for two players played on the terminal.</h3>

When the program is launched, a board in text is drawn, followed by a prompt for white side to enter a move. Moves are inputted in the form ```"FileRank FileRank"```, where the first FileRank consists of the origin coordinates and the second FileRank consists of the destination coordinates. When a successful move is inputted, the updated board will be printed and the opposing player will then be prompted to make a move. Here is an example turn by white followed by black:
<pre>
bR bN bB bQ bK bB bN bR 8
bp bp bp bp bp bp bp bp 7
   ##    ##    ##    ## 6
##    ##    ##    ##    5
   ##    ##    ##    ## 4
##    ##    ##    ##    3 
wp wp wp wp wp wp wp wp 2
wR wN wB wQ wK wB wN wR 1
 a  b  c  d  e  f  g  h

White's move: e2 e4

bR bN bB bQ bK bB bN bR 8
bp bp bp bp bp bp bp bp 7
   ##    ##    ##    ## 6
##    ##    ##    ##    5
   ##    ## wp ##    ## 4
##    ##    ##    ##    3
wp wp wp wp    wp wp wp 2
wR wN wB wQ wK wB wN wR 1
 a  b  c  d  e  f  g  h

Black's move: e7 e5

bR bN bB bQ bK bB bN bR 8
bp bp bp bp ## bp bp bp 7
   ##    ##    ##    ## 6
##    ##    bp    ##    5
   ##    ## wp ##    ## 4
##    ##    ##    ##    3
wp wp wp wp    wp wp wp 2
wR wN wB wQ wK wB wN wR 1
 a  b  c  d  e  f  g  h

White's move: 
</pre>
To castle, the player must input the starting and ending coordinates of the king piece. For example, a white castling king's side would be```e1 g1```. A pawn promotion is indicated by putting the piece to be promoted to after the move. An example of promoting a pawn to a knight might be```d7 d8 N```. The promoted piece is promoted to a queen by default if no promotion piece is indicated.

The game ends when a king piece is under checkmate. Upon checkmate, the program will be terminated and the winning color will be printed as the winner. Players may also offer a draw by inputting```draw```.
