package gameplay;

import gameElements.Board;

/** 
 * This is called upon in Pawn class when pawn reaches other side of the board
 * Pawn can then be promoted to become any other piece which changes its allowed movement to what the Pawn is promoted to
 * 
 * @author Max Zhou (mjz94)
 * @author Dhruv Patel (dvp67)
 * @version 1.0 
 * @since 2022-03-23
 */

public class pawnPromotion {
    
	/**
	 * This allows the Pawn to be promoted 
	 * 
	 * @param turn the current color turn (i.e. "White" or "Black")
	 * @param original of the original row and column coordinates
	 * @param destination of the destination row and column coordinates 
	 * @param promotePiece which allows the Pawn to be promoted 
	 * @param board the board that houses all the pieces
	 * @param whiteInCheckmate white is in check mate
	 * @param blackInCheckmate black is in check mate
	 * @param lastMove calling for the last move that was made
	 * @return true if pawn can be promoted
	 */
    public static boolean promote (String turn, String original, String destination, char promotePiece, Board board, Checkmated whiteInCheckmate, Checkmated blackInCheckmate, LastMove lastMove)
    {
        int[] destinationCoords = MovePiece.fileRankToBoardIndex(destination);

        if (turn.equals("White")) {
            if (destinationCoords[0] != 0) {
                return false;
            }
        }
        else {
            if (destinationCoords[0] != 7) {
                return false;
            }
        }

        if (MovePiece.movePiece(turn, original, destination, board, whiteInCheckmate, blackInCheckmate, lastMove) == false) {
            return false;
        }

        if (turn.equals("White")) {
            board.getPiece(destinationCoords[0], destinationCoords[1]).setType("w" + promotePiece);
        }
        else {
           
            board.getPiece(destinationCoords[0], destinationCoords[1]).setType("b" + promotePiece);
        }


        return true;
    }

}
