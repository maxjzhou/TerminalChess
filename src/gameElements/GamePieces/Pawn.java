package gameElements.GamePieces;

import gameElements.Board;
import gameplay.Checkmate;
import gameplay.LastMove;

/** 
 * The Pawn class consists of all the movements and functionality for Pawn 
 * The Pawn can move vertically as long as the path is clear
 * From starting position Pawn can move two spaces and one space after that
 * When capturing Pawn can only move diagonally to capture
 * 
 * @author Max Zhou (mjz94)
 * @author Dhruv Patel (dvp67)
 * @version 1.0 
 * @since 2022-03-23
 */

public class Pawn {
    
	/** 
	 * This method checks if the Pawn is able to move to the position entered
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @param lastMove calling for the last move that was made
     * @return if the Pawn can move return true otherwise return false
	 */
	
    public static boolean move (Board board, String turn, int[] originalCoords, int[] destinationCoords, LastMove lastMove) // int[0] -> y coord (row), int[1] -> x coord (column)
    {
        // Check if the direction for the pawn is legal (the pawn can only move in a forward direction)
        if ((turn.equals("White") && destinationCoords[0] >= originalCoords[0]) || (turn.equals("Black") && destinationCoords[0] <= originalCoords[0]))
        {
            return false;
        }

        // Check if the pawn is moving forward
        if (originalCoords[1] == destinationCoords[1])  // the pawn's original and destination columns are the same
        {
            // Check if the pawn is moving more than 2 spaces forward
            if (Math.abs(originalCoords[0] - destinationCoords[0]) > 2)
            {
                return false;
            }

            // Check if the user wants to move two spaces forward
            if (Math.abs(originalCoords[0] - destinationCoords[0]) == 2)
            {
                if (checkIfAtStartingPosition(turn, originalCoords) == false)
                {
                    return false;
                }
            }

            // Check if there are any blocking pieces in the way of the pawn
            if (checkIfBlockingInFront(turn, board, originalCoords, destinationCoords) == false)
            {
                return false;
            }
            
            // Check if moving the piece will put your own king in checkmate
            if (checkSelfCheckmate(turn, board, originalCoords, destinationCoords)) {
                return false;
            }

            // The move is legal and the pawn will be moved
            movePawn(turn, board, originalCoords, destinationCoords);
        }

        // Check if the pawn is moving diagnolly to capture an enemy piece
        else if ((destinationCoords[1] == originalCoords[1] - 1) || (destinationCoords[1] == originalCoords[1] + 1))
        {
            // Check if the pawn is moving more than one space forward diagonal
            if (Math.abs(destinationCoords[0] - originalCoords[0]) != 1) 
            {
                return false;
            }

            // Check for enpassant
            if (checkEnPassant(turn, lastMove, board, originalCoords, destinationCoords)) {
                movePawn(turn, board, originalCoords, destinationCoords);
                return true;
            }

            // Check if the diagonal space is empty or if the piece diagonal to it is of the same team
            if ((board.isEmpty(destinationCoords)) || (board.getPiece(destinationCoords[0], destinationCoords[1]).getColor() == turn.toLowerCase().charAt(0)))
            {
                return false;
            }

            movePawn(turn, board, originalCoords, destinationCoords);
        }
        else
        {
            return false;   // The pawn is making an illegal move
        }

        return true;
    }
    
	/** 
	 * This method checks for EnPassant to allow Pawns to capture other Pawns when the Pawn moves two spaces but is captured as if it only moves one space
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return boolean true if Pawn moved otherwise false
	 */

    private static boolean checkEnPassant (String turn, LastMove lastMove, Board board, int[] originalCoords, int[] destinationCoords) {

        String s = lastMove.getS();
        int originalRow = lastMove.getOriginalRow();
        int originalCol = lastMove.getOriginalCol();
        int destinationRow = lastMove.getDestinationRow();
        int destinationCol = lastMove.getDestinationCol();

        if (turn.equals("White")) {
            if (originalCoords[0] != 3) {
                return false;
            }
            if (s.equals("bp") == false) {
                return false;
            }
            if (originalRow == 1 && destinationRow == 3 && originalCol == destinationCoords[1] && destinationCol == destinationCoords[1]) {
                board.getPiece(destinationRow, destinationCol).setType(board.getReferenceBoardString(destinationRow, destinationCol));
                return true;
            }
        }
        else {
            if (originalCoords[0] != 4) {
                return false;
            }
            if (s.equals("wp") ==  false) {
                return false;
            }
            if (originalRow == 6 && destinationRow == 4 && originalCol == destinationCoords[1] && destinationCol == destinationCoords[1]) {
                board.getPiece(destinationRow, destinationCol).setType(board.getReferenceBoardString(destinationRow, destinationCol));
                return true;
            }
        }

        return false;
    }
    
	/** 
	 * This method checks to see if Pawn is at is starting position
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return boolean true if Pawn moved otherwise false
	 */

    private static boolean checkIfAtStartingPosition (String turn, int[] originalCoords)
    {
        // White's turn
        if (turn.equals("White"))
        {
            if (originalCoords[0] == 6)
            {
                return true;
            }
            return false;
        }
        // Black's turn
        else
        {
            if (originalCoords[0] == 1)
            {
                return true;
            }
            return false;
        }
    }
    
    /** 
	 * This method checks if the path is blocked by any piece whether it be the same team or opposite teams
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return if path isn't clear return false otherwise return true 
	*/

    // Checks if there are any blocking pieces within the distance between startingRow and endingRow
    private static boolean checkIfBlockingInFront (String turn, Board board, int[] originalCoords, int[] destinationCoords)
    {
        int startingRow = originalCoords[0];
        int endingRow = destinationCoords[0];
        int column = originalCoords[1];

        if (turn.equals("White"))
        {
            for (int i = startingRow - 1; i >= endingRow; i--)
            {
                int[] temp = new int[2];
                temp[0] = i;
                temp[1] = column;
                if (board.isEmpty(temp) == false)
                {
                    return false;
                }
            }
            return true;
        }
        else
        {
            for (int i = startingRow + 1; i <= endingRow; i++)
            {
                int[] temp = new int[2];
                temp[0] = i;
                temp[1] = column;
                if (board.isEmpty(temp) == false)
                {
                    return false;
                }
            }
            return true;
        }
    }
    
	/** 
	 * This method checks if check mate is happening 
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return boolean true if Pawn moved otherwise false
	 */
    
    // Checks if moving the piece will put itself in checkmate
    private static boolean checkSelfCheckmate (String turn, Board board, int[] originalCoords, int[] destinationCoords) {

        Board tempBoard = new Board();
        tempBoard.setupBoard();
        tempBoard.cloneBoard(board);

        movePawn(turn, tempBoard, originalCoords, destinationCoords);

        int[] tempCoords = new int[2];

        if (turn.equals("White")) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    String p = tempBoard.getPiece(i, j).getType();
                    if (p.equals("wK")) {
                        tempCoords[0] = i;
                        tempCoords[1] = j;
                        break;
                    }
                }
            }
        }
        else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    String p = tempBoard.getPiece(i, j).getType();
                    if (p.equals("bK")) {
                        tempCoords[0] = i;
                        tempCoords[1] = j;
                        break;
                    }
                }
            }
        }

        if (Checkmate.isCheckmate(tempBoard, tempCoords, turn)) {
            return true;
        }

        return false;
    }

	/** 
	 * This method moves the Pawn and checks to see it is makes to the other end then it promotes the Pawn 
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return boolean true if Pawn moved otherwise false
	 */
    
    // Moves the pawn to the destination position
    private static void movePawn (String turn, Board board, int[] originalCoords, int[] destinationCoords)
    {
        board.getPiece(originalCoords[0], originalCoords[1]).setType(board.getReferenceBoardString(originalCoords[0], originalCoords[1]));
        
        if (turn.equals("White"))
        {
            if (destinationCoords[0] == 0)  // if the pawn reaches the end of the board, promote it to a Queen
            {
                board.getPiece(destinationCoords[0], destinationCoords[1]).setType("wQ");
            }
            else
            {
                board.getPiece(destinationCoords[0], destinationCoords[1]).setType("wp");
            }
        }
        else
        {
            if (destinationCoords[0] == 7)  // if the pawn reaches the end of the board, promote it to a Queen
            {
                board.getPiece(destinationCoords[0], destinationCoords[1]).setType("bQ");
            }
            else
            {
                board.getPiece(destinationCoords[0], destinationCoords[1]).setType("bp");
            }
        }
    }

}
