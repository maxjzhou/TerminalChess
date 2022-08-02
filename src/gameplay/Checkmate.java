package gameplay;

import gameElements.Board;

/**
 * The Checkmate class contains methods to check if a Piece is under checkmate.
 * 
 * @author Max Zhou (mj94)
 * @author Dhruv Patel (dvp67)
 * @version 1.0
 * @since 2022-03-23
 */
public class Checkmate {

    /**
     * Returns whether the parameter coordinates are currently under checkmate by enemy pieces
     * 
     * @param board the board that houses all the pieces
     * @param coords the row and column coordinates to be checked
     * @param turn the current color turn (i.e. "White" or "Black")
     * @return if the coordinate on the board is under checkmate
     */

    public static boolean isCheckmate (Board board, int[] coords, String turn)
    {
        // Check in straight lines for threats (Rook or Queen)
        if (checkVerticalAndHorizontal(board, coords, turn))
        {
            return true;
        }

        // Check in diagonal lines for threats (Bishop or Queen)
        if (checkDiagonal(board, coords, turn))
        {
            return true;
        }

        // Check for enemy pawns in the two spaces diagonal in front
        if (checkPawns(board, coords, turn))
        {
            return true;
        }

        // Check for enemy king in the 8 squares surrounding
        if (checkKing(board, coords, turn))
        {
            return true;
        }

        // Check for knight threat (maximum 8 possible positions for knight threat)
        if (checkKnight(board, coords, turn)) {
            return true;
        }

        return false;
    }
    
    /**
     * Checks whether there is a threat to the specified square on the horizontal and vertical directions.
     * @param board the board that is currently played on
     * @param coords the specified (row,column) coordinates
     * @param turn the current player's turn
     * @return true if there is currently a threat, false if the square is safe
     */
    private static boolean checkVerticalAndHorizontal (Board board, int[] coords, String turn)
    {
        // Check vertical first
        for (int i = coords[0] - 1; i >= 0; i--)    // Checks up
        {
            String p = board.getPiece(i, coords[1]).getType();
            if ((turn.equals("White") && (p.equals("bR") || p.equals("bQ"))) || (turn.equals("Black") && (p.equals("wQ") || p.equals("wR"))))
            {
                return true;
            }

            int[] temp = new int[2];
            temp[0] = i;
            temp[1] = coords[1];
            if (board.isEmpty(temp) == false)
            {
                break;
            }
        }
        for (int i = coords[0] + 1; i <= 7; i++)    // Check down
        {
            String p = board.getPiece(i, coords[1]).getType();
            if ((turn.equals("White") && (p.equals("bR") || p.equals("bQ"))) || (turn.equals("Black") && (p.equals("wQ") || p.equals("wR"))))
            {
                return true;
            }

            int[] temp = new int[2];
            temp[0] = i;
            temp[1] = coords[1];
            if (board.isEmpty(temp) == false)
            {
                break;
            }
        }

        // Check horizontal next
        for (int i = coords[1] - 1; i >= 0; i--)    // Check left
        {
            String p = board.getPiece(coords[0], i).getType();
            if ((turn.equals("White") && (p.equals("bR") || p.equals("bQ"))) || (turn.equals("Black") && (p.equals("wQ") || p.equals("wR")))) 
            {
                return true;
            }

            int[] temp = new int[2];
            temp[0] = coords[0];
            temp[1] = i;
            if (board.isEmpty(temp) == false)
            {
                break;
            }
        }

        for (int i = coords[1] + 1; i <= 7; i++)    // Check right
        {
            String p = board.getPiece(coords[0], i).getType();
            if ((turn.equals("White") && (p.equals("bR") || p.equals("bQ"))) || (turn.equals("Black") && (p.equals("wQ") || p.equals("wR")))) 
            {
                return true;
            }

            int[] temp = new int[2];
            temp[0] = coords[0];
            temp[1] = i;
            if (board.isEmpty(temp) == false)
            {
                break;
            }
        }

        return false;
    }

    /**
     * Checks whether there is a threat to the specified square in the diagonal directions
     * @param board the board to be played on
     * @param coords the specified (row,column) coordinates
     * @param turn the current player's turn
     * @return true if there is a current threat to the square, false if there isn't.
     */
    private static boolean checkDiagonal (Board board, int[] coords, String turn)
    {
        // Check top left
        int counter = 1;

        while (coords[0] - counter >= 0 && coords[1] - counter >= 0) {
            String p = board.getPiece(coords[0] - counter, coords[1] - counter).getType();
            if ((turn.equals("White") && (p.equals("bB") || p.equals("bQ"))) || (turn.equals("Black") && (p.equals("wQ") || p.equals("wB")))) 
            {
                return true;
            }

            int[] temp = new int[2];
            temp[0] = coords[0] - counter;
            temp[1] = coords[1] - counter;
            if (board.isEmpty(temp) == false)
            {
                break;
            }
            counter++;
        }

        // Check top right
        counter = 1;

        while (coords[0] - counter >= 0 && coords[1] + counter <= 7) {
            String p = board.getPiece(coords[0] - counter, coords[1] + counter).getType();
            if ((turn.equals("White") && (p.equals("bB") || p.equals("bQ"))) || (turn.equals("Black") && (p.equals("wQ") || p.equals("wB")))) 
            {
                return true;
            }

            int[] temp = new int[2];
            temp[0] = coords[0] - counter;
            temp[1] = coords[1] + counter;
            if (board.isEmpty(temp) == false)
            {
                break;
            }
            counter++;
        }

        //Check bottom left
        counter = 1;

        while (coords[0] + counter <= 7 && coords[1] - counter >= 0) {
            String p = board.getPiece(coords[0] + counter, coords[1] - counter).getType();
            if ((turn.equals("White") && (p.equals("bB") || p.equals("bQ"))) || (turn.equals("Black") && (p.equals("wQ") || p.equals("wB")))) 
            {
                return true;
            }

            int[] temp = new int[2];
            temp[0] = coords[0] + counter;
            temp[1] = coords[1] - counter;
            if (board.isEmpty(temp) == false)
            {
                break;
            }
            counter++;
        }

        //Check bottom right
        counter = 1;

        while (coords[0] + counter <= 7 && coords[1] + counter <= 7) {
            String p = board.getPiece(coords[0] + counter, coords[1] + counter).getType();
            if ((turn.equals("White") && (p.equals("bB") || p.equals("bQ"))) || (turn.equals("Black") && (p.equals("wQ") || p.equals("wB")))) 
            {
                return true;
            }

            int[] temp = new int[2];
            temp[0] = coords[0] + counter;
            temp[1] = coords[1] + counter;
            if (board.isEmpty(temp) == false)
            {
                break;
            }
            counter++;
        }
        

        return false;
    }

    /**
     * Checks whether there are enemy pawns in the two squares directly in front and diagonal.
     * @param board the board that is currently played on
     * @param coords the specified coordinates
     * @param turn the current player's turn
     * @return true if there are enemy pawns on either of the two square, false if there isn't
     */
    private static boolean checkPawns (Board board, int[] coords, String turn) {

        if (turn.equals("White")) { // Check when white's turn
            
            if (!(coords[0] - 1 < 0 || coords[1] - 1 < 0)) {
                if (board.getPiece(coords[0] - 1, coords[1] - 1).getType().equals("bp")) {
                    return true;
                }
            }
            if (!(coords[0] - 1 < 0 || coords[1] + 1 > 7)) {
                if (board.getPiece(coords[0] - 1, coords[1] + 1).getType().equals("bp")) {
                    return true;
                }
            }

        } else {    // Check when black's turn

            if (!(coords[0] + 1 > 7 || coords[1] - 1 < 0)) {
                if (board.getPiece(coords[0] + 1, coords[1] - 1).getType().equals("wp")) {
                    return true;
                }
            }
            if (!(coords[0] + 1 > 7 || coords[1] + 1 > 7)) {
                if (board.getPiece(coords[0] + 1, coords[1] + 1).getType().equals("wp")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Check for an enemy king in all square surrounding the specified coordinate
     * @param board the board that is currently being played on
     * @param coords the specified (row,column) coordinates
     * @param turn the current players turn
     * @return true if there is an enemy King in any of the surrounding squares, false otherwise.
     */
    public static boolean checkKing (Board board, int[] coords, String turn) {

        // Check top
        if (!(coords[0] - 1 < 0)) {
            String p = board.getPiece(coords[0] - 1, coords[1]).getType();
            if (turn.equals("White")) {
                if (p.equals("bK")) {
                    return true;
                }
            }
            else {
                if (p.equals("wK")) {
                    return true;
                }
            }
        }

        // Check top right
        if (!(coords[0] - 1 < 0 || coords[1] + 1 > 7)) {
            String p = board.getPiece(coords[0] - 1, coords[1] + 1).getType();
            if (turn.equals("White")) {
                if (p.equals("bK")) {
                    return true;
                }
            }
            else {
                if (p.equals("wK")) {
                    return true;
                }
            }
        }

        // Check right
        if (!(coords[1] + 1 > 7)) {
            String p = board.getPiece(coords[0], coords[1] + 1).getType();
            if (turn.equals("White")) {
                if (p.equals("bK")) {
                    return true;
                }
            }
            else {
                if (p.equals("wK")) {
                    return true;
                }
            }
        }

        // Check bottom right
        if (!(coords[0] + 1 > 7 || coords[1] + 1 > 7)) {
            String p = board.getPiece(coords[0] + 1, coords[1] + 1).getType();
            if (turn.equals("White")) {
                if (p.equals("bK")) {
                    return true;
                }
            }
            else {
                if (p.equals("wK")) {
                    return true;
                }
            }
        }

        // Check bottom
        if (!(coords[0] + 1 > 7)) {
            String p = board.getPiece(coords[0] + 1, coords[1]).getType();
            if (turn.equals("White")) {
                if (p.equals("bK")) {
                    return true;
                }
            }
            else {
                if (p.equals("wK")) {
                    return true;
                }
            }
        }

        // Check bottom left
        if (!(coords[0] + 1 > 7 || coords[1] - 1 < 0)) {
            String p = board.getPiece(coords[0] + 1, coords[1] - 1).getType();
            if (turn.equals("White")) {
                if (p.equals("bK")) {
                    return true;
                }
            }
            else {
                if (p.equals("wK")) {
                    return true;
                }
            }
        }

        // Check left
        if (!(coords[1] - 1 < 0)) {
            String p = board.getPiece(coords[0], coords[1] - 1).getType();
            if (turn.equals("White")) {
                if (p.equals("bK")) {
                    return true;
                }
            }
            else {
                if (p.equals("wK")) {
                    return true;
                }
            }
        }

        // Check top left
        if (!(coords[0] - 1 < 0 || coords[1] - 1 < 0)) {
            String p = board.getPiece(coords[0] - 1, coords[1] - 1).getType();
            if (turn.equals("White")) {
                if (p.equals("bK")) {
                    return true;
                }
            }
            else {
                if (p.equals("wK")) {
                    return true;
                }
            }
        }


        return false;
    }

    /**
     * Checks if there are knights that are a threat to a specified square.
     * @param board the board that is currently played on
     * @param coords the specified (row,column) coordinates
     * @param turn the current player's turn
     * @return true if there is a knight threat, false otherwise.
     */
    public static boolean checkKnight (Board board, int[] coords, String turn) {

        // Up Left L
        if (coords[0] - 2 >= 0 && coords[1] - 1 >= 0) {
            String p = board.getPiece(coords[0] - 2, coords[1] - 1).getType();
            if (turn.equals("White")) {
                if (p.equals("bN")) {
                    return true;
                }
            }
            else {
                if (p.equals("wN")) {
                    return true;
                }
            }
        }

        // Up Right L
        if (coords[0] - 2 >= 0 && coords[1] + 1 <= 7) {
            String p = board.getPiece(coords[0] - 2, coords[1] + 1).getType();
            if (turn.equals("White")) {
                if (p.equals("bN")) {
                    return true;
                }
            }
            else {
                if (p.equals("wN")) {
                    return true;
                }
            }
        }

        // Right up L
        if (coords[0] - 1 >= 0 && coords[1] + 2 <= 7) {
            String p = board.getPiece(coords[0] - 1, coords[1] + 2).getType();
            if (turn.equals("White")) {
                if (p.equals("bN")) {
                    return true;
                }
            }
            else {
                if (p.equals("wN")) {
                    return true;
                }
            }
        }

        // Right down L
        if (coords[0] + 1 <= 7 && coords[1] + 2 <= 7) {
            String p = board.getPiece(coords[0] + 1, coords[1] + 2).getType();
            if (turn.equals("White")) {
                if (p.equals("bN")) {
                    return true;
                }
            }
            else {
                if (p.equals("wN")) {
                    return true;
                }
            }
        }

        // Down right L
        if (coords[0] + 2 <= 7 && coords[1] + 1 <= 7) {
            String p = board.getPiece(coords[0] + 2, coords[1] + 1).getType();
            if (turn.equals("White")) {
                if (p.equals("bN")) {
                    return true;
                }
            }
            else {
                if (p.equals("wN")) {
                    return true;
                }
            }
        }

        // Down left L
        if (coords[0] + 2 <= 7 && coords[1] - 1 >= 0) {
            String p = board.getPiece(coords[0] + 2, coords[1] - 1).getType();
            if (turn.equals("White")) {
                if (p.equals("bN")) {
                    return true;
                }
            }
            else {
                if (p.equals("wN")) {
                    return true;
                }
            }
        }

        // Left down L
        if (coords[0] + 1 <= 7 && coords[1] - 2 >= 0) {
            String p = board.getPiece(coords[0] + 1, coords[1] - 2).getType();
            if (turn.equals("White")) {
                if (p.equals("bN")) {
                    return true;
                }
            }
            else {
                if (p.equals("wN")) {
                    return true;
                }
            }
        }

        // Left up L
        if (coords[0] - 1 >= 0 && coords[1] - 2 >= 0) {
            String p = board.getPiece(coords[0] - 1, coords[1] - 2).getType();
            if (turn.equals("White")) {
                if (p.equals("bN")) {
                    return true;
                }
            }
            else {
                if (p.equals("wN")) {
                    return true;
                }
            }
        }


        return false;
    }

}
