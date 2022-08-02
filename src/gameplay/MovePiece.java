package gameplay;

import gameElements.Board;
import gameElements.GamePieces.Bishop;
import gameElements.GamePieces.King;
import gameElements.GamePieces.Knight;
import gameElements.GamePieces.Pawn;
import gameElements.GamePieces.Queen;
import gameElements.GamePieces.Rook;


/**
 * The MovePiece class contains methods that will check if a move is legal and execute instructions based on that
 * 
 * @author Max Zhou (mj94)
 * @author Dhruv Patel (dvp67)
 * @version 1.0
 * @since 2022-03-23
 */
public class MovePiece {
    
    /**
     * Checks if a move is legal or not.
     * Moves the piece if it is legal
     * @param turn the current player's turn
     * @param original the original coordinates inputed by the user
     * @param destination the destination coordinates inputed by the user
     * @param board the board to be played on
     * @param whiteInCheckmate the status of white in checkmate
     * @param blackInCheckmate the status of black in checkmate
     * @param lastMove the information of the last move
     * @return true if the move was legal, false if the move was illegal
     */
    public static boolean movePiece (String turn, String original, String destination, Board board, Checkmated whiteInCheckmate, Checkmated blackInCheckmate, LastMove lastMove)
    {
        // converts the filerank coords into Board coords
        int[] originalCoords = fileRankToBoardIndex(original);
        int[] destinationCoords = fileRankToBoardIndex(destination);

        if (checkOutOfBounds(originalCoords, destinationCoords)) {  // Check whether the input coords are out of bounds
            System.out.println("Illegal Move, try again");
            return false;
        }
        
        if (board.isEmpty(originalCoords))    // checks whether the original location is on a empty square
        {
            System.out.println("Illegal Move, try again");
            return false;
        }

        if (board.getPiece(originalCoords[0], originalCoords[1]).getType().charAt(0) != turn.toLowerCase().charAt(0))    // Checks if the desired piece is the same color as the player
        {
            System.out.println("Illegal Move, try again");
            return false;
        }

        if (checkLegalAndMove(turn, board, originalCoords, destinationCoords, lastMove) == false)
        {
            System.out.println("Illegal Move, try again");
            return false;
        }

        String movedPiece = board.getPiece(destinationCoords[0], destinationCoords[1]).getType();
        lastMove.setInfo(movedPiece, originalCoords[0], destinationCoords[0], originalCoords[1], destinationCoords[1]);


        if (checkCheckmate(turn, board)) {
            if (turn.equals("White")) {
                blackInCheckmate.isCheckmated();
            }
            else {
                whiteInCheckmate.isCheckmated();
            }
        }

        return true;
    }

    /**
     * Checks whether the input coordinates are out of bounds of the board
     * @param originalCoords the starting coordinates
     * @param destinationCoords the ending coordinates
     * @return true if the input is out of bounds, false otherwise
     */
    private static boolean checkOutOfBounds (int[] originalCoords, int[] destinationCoords) {
        if (originalCoords[0] < 0 || originalCoords[0] > 7) {
            return true;
        }
        if (originalCoords[1] < 0 || originalCoords[1] > 7) {
            return true;
        }
        if (destinationCoords[0] < 0 || destinationCoords[0] > 7) {
            return true;
        }
        if (destinationCoords[1] < 0 || destinationCoords[1] > 7) {
            return true;
        }
        return false;
    }

    /**
     * Converts the coordinates in string format to array format (ar[0] = row, ar[1] = col)
     * @param fileRank the coordinates inputed by the user
     * @return an array with the coordinates
     */
    public static int[] fileRankToBoardIndex (String fileRank)
    {
        int[] coords = new int[2];
        int col = 0;
        int row = 0;
        
        char file = fileRank.charAt(0);
        char rank = fileRank.charAt(1);

        col = ((int)(file)) - 97;   // 'a' has ascii value 97 -> translates to 0 on board, etc.
        row = ((int)(rank)) - 49;
        row = 7 - row;

        coords[1] = col;
        coords[0] = row;

        return coords;
    }

    /**
     * Checks whether a move is legal or not.
     * If the move is legal the piece is moved.
     * @param turn the current player's turn
     * @param board the board to be played on
     * @param originalCoords the starting coordinates
     * @param destinationCoords the ending coordinates
     * @param lastMove the information of the last move made
     * @return true if the move was legal, false otherwise
     */
    private static boolean checkLegalAndMove (String turn, Board board, int[] originalCoords, int[] destinationCoords, LastMove lastMove)
    {
        char pieceCharacter = board.getPiece(originalCoords[0], originalCoords[1]).getType().charAt(1);

        if (pieceCharacter == 'R')
        {
            return Rook.move(board, turn, originalCoords, destinationCoords);
        }
        else if (pieceCharacter == 'N')
        {
            return Knight.move(board, turn, originalCoords, destinationCoords);
        }
        else if (pieceCharacter == 'B')
        {
            return Bishop.move(board, turn, originalCoords, destinationCoords);
        }
        else if (pieceCharacter == 'Q')
        {
            return Queen.move(board, turn, originalCoords, destinationCoords);
        }
        else if (pieceCharacter == 'K')
        {
            return King.move(board, turn, originalCoords, destinationCoords);
        }
        else
        {
            // also promotes pawn to a queen if it reaches the end of the board.
            return Pawn.move(board, turn, originalCoords, destinationCoords, lastMove);    
        }
    }

    /**
     * Checks if the current player's King is under checkmate
     * @param turn the current player's turn
     * @param board the current board that is being played on
     * @return true if the king is under checkmate, false otherwise.
     */
    private static boolean checkCheckmate (String turn, Board board) {

        int[] kingCoords = new int[2];

        if (turn.equals("White")) {
            boolean kingFound = false;
            for (int i = 0; i < 8; i++) {
                if (kingFound) {
                    break;
                }
                for (int j = 0; j < 8; j++) {
                    if (board.getPiece(i, j).getType().equals("bK"))
                    {
                        kingFound = true;
                        kingCoords[0] = i;
                        kingCoords[1] = j;
                        break;
                    }
                }
            }
        }
        else {
            boolean kingFound = false;
            for (int i = 0; i < 8; i++) {
                if (kingFound) {
                    break;
                }
                for (int j = 0; j < 8; j++) {
                    if (board.getPiece(i, j).getType().equals("wK"))
                    {
                        kingFound = true;
                        kingCoords[0] = i;
                        kingCoords[1] = j;
                        break;
                    }
                }
            }
        }

        String oppoTurn = "White";
        if (turn.equals("White")) {
            oppoTurn = "Black";
        }

        if (hasValidMove(oppoTurn, board, kingCoords) == false) {
            return true;
        }

        return false;
    }


    /**
     * Checks whether the current king has any valid move
     * @param turn the current players turn
     * @param board the board that is currently being played on
     * @param kingCoords the coordinates of the King
     * @return true if the king has a valid move, false if the king has no valid move
     */
    private static boolean hasValidMove (String turn, Board board, int[] kingCoords) {

        // Check existing location
        if (!Checkmate.isCheckmate(board, kingCoords, turn)) {
            return true;
        }

        // Check top
        if (kingCoords[0] - 1 >= 0) {
            String p = board.getPiece(kingCoords[0] - 1, kingCoords[1]).getType();
            if (turn.equals("White")) {
                if (p.charAt(0) != 'w') {
                    int[] temp = new int[2];
                    temp[0] = kingCoords[0] - 1;
                    temp[1] = kingCoords[1];
                    if (!Checkmate.isCheckmate(board, temp, turn)) {
                        System.out.println("Check");
                        return true;
                    }
                }
            }
            else {
                if (p.charAt(0) != 'b') {
                    int[] temp = new int[2];
                    temp[0] = kingCoords[0] - 1;
                    temp[1] = kingCoords[1];
                    if (!Checkmate.isCheckmate(board, temp, turn)) {
                        System.out.println("Check");
                        return true;
                    }
                }
            }
        }

        // Check top right
        if (kingCoords[0] - 1 >= 0 && kingCoords[1] + 1 <= 7) {
            String p = board.getPiece(kingCoords[0] - 1, kingCoords[1] + 1).getType();
            if (turn.equals("White")) {
                if (p.charAt(0) != 'w') {
                    int[] temp = new int[2];
                    temp[0] = kingCoords[0] - 1;
                    temp[1] = kingCoords[1] + 1;
                    if (!Checkmate.isCheckmate(board, temp, turn)) {
                        System.out.println("Check");
                        return true;
                    }
                }
            }
            else {
                if (p.charAt(0) != 'b') {
                    int[] temp = new int[2];
                    temp[0] = kingCoords[0] - 1;
                    temp[1] = kingCoords[1] + 1;
                    if (!Checkmate.isCheckmate(board, temp, turn)) {
                        System.out.println("Check");
                        return true;
                    }
                }
            }
        }

        // Check right
        if (kingCoords[1] + 1 <= 7) {
            String p = board.getPiece(kingCoords[0], kingCoords[1] + 1).getType();
            if (turn.equals("White")) {
                if (p.charAt(0) != 'w') {
                    int[] temp = new int[2];
                    temp[0] = kingCoords[0];
                    temp[1] = kingCoords[1] + 1;
                    if (!Checkmate.isCheckmate(board, temp, turn)) {
                        System.out.println("Check");
                        return true;
                    }
                }
            }
            else {
                if (p.charAt(0) != 'b') {
                    int[] temp = new int[2];
                    temp[0] = kingCoords[0];
                    temp[1] = kingCoords[1] + 1;
                    if (!Checkmate.isCheckmate(board, temp, turn)) {
                        System.out.println("Check");
                        return true;
                    }
                }
            }
        }

        // Check bottom right
        if (kingCoords[0] + 1 <= 7 && kingCoords[1] + 1 <= 7) {
            String p = board.getPiece(kingCoords[0] + 1, kingCoords[1] + 1).getType();
            if (turn.equals("White")) {
                if (p.charAt(0) != 'w') {
                    int[] temp = new int[2];
                    temp[0] = kingCoords[0] + 1;
                    temp[1] = kingCoords[1] + 1;
                    if (!Checkmate.isCheckmate(board, temp, turn)) {
                        System.out.println("Check");
                        return true;
                    }
                }
            }
            else {
                if (p.charAt(0) != 'b') {
                    int[] temp = new int[2];
                    temp[0] = kingCoords[0] + 1;
                    temp[1] = kingCoords[1] + 1;
                    if (!Checkmate.isCheckmate(board, temp, turn)) {
                        System.out.println("Check");
                        return true;
                    }
                }
            }
        }

        // Check bottom
        if (kingCoords[0] + 1 <= 7) {
            String p = board.getPiece(kingCoords[0] + 1, kingCoords[1]).getType();
            if (turn.equals("White")) {
                if (p.charAt(0) != 'w') {
                    int[] temp = new int[2];
                    temp[0] = kingCoords[0] + 1;
                    temp[1] = kingCoords[1];
                    if (!Checkmate.isCheckmate(board, temp, turn)) {
                        System.out.println("Check");
                        return true;
                    }
                }
            }
            else {
                if (p.charAt(0) != 'b') {
                    int[] temp = new int[2];
                    temp[0] = kingCoords[0] + 1;
                    temp[1] = kingCoords[1];
                    if (!Checkmate.isCheckmate(board, temp, turn)) {
                        System.out.println("Check");
                        return true;
                    }
                }
            }
        }

        // Check bottom left
        if (kingCoords[0] + 1 <= 7 && kingCoords[1] - 1 >= 0) {
            String p = board.getPiece(kingCoords[0] + 1, kingCoords[1] - 1).getType();
            if (turn.equals("White")) {
                if (p.charAt(0) != 'w') {
                    int[] temp = new int[2];
                    temp[0] = kingCoords[0] + 1;
                    temp[1] = kingCoords[1] - 1;
                    if (!Checkmate.isCheckmate(board, temp, turn)) {
                        System.out.println("Check");
                        return true;
                    }
                }
            }
            else {
                if (p.charAt(0) != 'b') {
                    int[] temp = new int[2];
                    temp[0] = kingCoords[0] + 1;
                    temp[1] = kingCoords[1] - 1;
                    if (!Checkmate.isCheckmate(board, temp, turn)) {
                        System.out.println("Check");
                        return true;
                    }
                }
            }
        }

        // Check left
        if (kingCoords[1] - 1 >= 0) {
            String p = board.getPiece(kingCoords[0], kingCoords[1] - 1).getType();
            if (turn.equals("White")) {
                if (p.charAt(0) != 'w') {
                    int[] temp = new int[2];
                    temp[0] = kingCoords[0];
                    temp[1] = kingCoords[1] - 1;
                    if (!Checkmate.isCheckmate(board, temp, turn)) {
                        System.out.println("Check");
                        return true;
                    }
                }
            }
            else {
                if (p.charAt(0) != 'b') {
                    int[] temp = new int[2];
                    temp[0] = kingCoords[0];
                    temp[1] = kingCoords[1] - 1;
                    if (!Checkmate.isCheckmate(board, temp, turn)) {
                        System.out.println("Check");
                        return true;
                    }
                }
            }
        }

        // Check top left
        if (kingCoords[0] - 1 >= 0 && kingCoords[1] - 1 >= 0) {
            String p = board.getPiece(kingCoords[0] - 1, kingCoords[1] - 1).getType();
            if (turn.equals("White")) {
                if (p.charAt(0) != 'w') {
                    int[] temp = new int[2];
                    temp[0] = kingCoords[0] - 1;
                    temp[1] = kingCoords[1] - 1;
                    if (!Checkmate.isCheckmate(board, temp, turn)) {
                        System.out.println("Check");
                        return true;
                    }
                }
            }
            else {
                if (p.charAt(0) != 'b') {
                    int[] temp = new int[2];
                    temp[0] = kingCoords[0] - 1;
                    temp[1] = kingCoords[1] - 1;
                    if (!Checkmate.isCheckmate(board, temp, turn)) {
                        System.out.println("Check");
                        return true;
                    }
                }
            }
        }

        return false;
    } 

}
