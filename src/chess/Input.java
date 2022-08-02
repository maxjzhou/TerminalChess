package chess;

import java.util.Scanner;

import gameElements.Board;
import gameplay.Checkmated;
import gameplay.LastMove;
import gameplay.MovePiece;
import gameplay.pawnPromotion;

enum Turn
{
    WHITE, BLACK
}

/**
 * The input class controls all user input (through the command line).
 * 
 * @author Max Zhou (mj94)
 * @author Dhruv Patel (dvp67)
 * @version 1.0
 * @since 2022-03-23
 */
public class Input {
    
    /**
     * The turn that will be switched
     */
    private static Turn turn = Turn.WHITE; // White always starts first

    /**
     * Checkmated object to hold the status of white in checkmate
     */
    static Checkmated whiteInCheckmate = new Checkmated();
    /**
     * Checkmated object to hold the status of black in checkmate
     */
    static Checkmated blackInCheckmate = new Checkmated();

    /**
     * LastMove object that holds information about the last move conducted
     */
    static LastMove lastMove = new LastMove ();
    
    /**
     * This method creates a Scanner object to listen to command line input.
     * The Scanner listens to the command line and executes directions based on input.
     * @param board the chess board to be played on
     * @return the winner of the game
     */
    public static String consoleInput (Board board)
    {
        Scanner sc = new Scanner(System.in);

        boolean checkmate = false;

        String winner = "";

        /**
         * Valid inputs for console:
         * 
         * - "[desired piece] [destination]" (e.g. "e2 e4" will move the piece at e2 to e4).
         * - "[desired pawn] [destination] [piece]" (e.g. "g7 g8 N" will move the pawn & promote it to a Knight).
         * - "resign" (the opposing player wins).
         * - "[desired piece] [destination] draw?" (e.g. "g1 f3 draw?").
         * - "draw" (in response to a draw proposal by the other player).
         * 
         * We do not need to worry about bad inputs (e.g. "x10 p14", "e3b5", etc.).
         */
        
        while (checkmate != true) 
        {
            String s = sc.nextLine();

            if (s.length() == 11)  // draw offered
            {
                sc.nextLine();  // other player enters "draw"
                winner = "draw";
                break;  // Game ends
            }
            else if (s.equals("resign"))    // one player resigns
            {
                winner = resign();  // other player wins
                break;
            }
            else if (s.length() == 7)   // promotion of pawn to another piece
            {
                if (pawnPromotion.promote(turntoString(), s.substring(0,2), s.substring(3, 5), s.charAt(6), board, whiteInCheckmate, blackInCheckmate, lastMove) == false)
                {
                    System.out.println("Invalid move, try again");
                    continue;
                }
            }
            else if (s.length() == 5)   // moves the desired piece
            {
                if (MovePiece.movePiece(turntoString(), s.substring(0,2), s.substring(3), board, whiteInCheckmate, blackInCheckmate, lastMove) == false)
                {
                    continue;
                }
            }
            else
            {
                System.out.println("Invalid input - try again.");
                continue;
            }

            if (turn == Turn.WHITE) {
                if (blackInCheckmate.getCheckmateStatus()) {
                    checkmate = true;
                    System.out.println("Checkmate");
                    winner = "White";
                    break;
                }
            }
            else {
                if (whiteInCheckmate.getCheckmateStatus()) {
                    checkmate = true;
                    System.out.println("Checkmate");
                    winner = "Black";
                    break;
                }
            }
            board.printBoard();
            switchTurns();
        }

        sc.close();

        return winner;
    }



    /**
     * Changes turns (from black to white)
     */
    private static void switchTurns ()
    {
        if (turn == Turn.WHITE)
        {
            turn = Turn.BLACK;
        }
        else
        {
            turn = Turn.WHITE;
        }
    }

    /**
     * Converts the turn enumeration to a String
     * @return the String form of a turn enumeration
     */
    private static String turntoString ()
    {
        if (turn == Turn.WHITE)
        {
            return "White";
        }
        
        return "Black";
    }

    /**
     * Resigns the current player's turn, causing the other player to win
     * @return the opposite color of the current player's turn
     */
    private static String resign ()
    {
        if (turn == Turn.WHITE)
        {
            return "Black";
        }
        return "White";
    }


}
