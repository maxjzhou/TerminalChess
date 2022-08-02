package chess;

import gameElements.Board;

/**
 * The Chess class contains the entry point for the chess program.
 * 
 * @author Max Zhou (mj94)
 * @author Dhruv Patel (dvp67)
 * @version 1.0
 * @since 2022-03-23
 */

public class Chess
{

    /**
     * This is the main method which is called upon the program's execution.
     * It creates a board, calls the Input class, and prints the results at the end of the game.
     * @param args command line arguments/flags
     */
    public static void main (String[] args)
    {
        Board board = new Board();

        board.setupBoard();

        board.printBoard();

        String s = Input.consoleInput(board);

        if (s.equals("draw")) {
            System.out.println(s);
        }
        else {
            System.out.println(s + " wins");
        }
    }
}