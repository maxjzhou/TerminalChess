package gameElements.GamePieces;

import gameElements.Board;


/** 
 * The Bishop class consists of all the movements and functionality for Bishop 
 * The Bishop can move diagonally in any direction as long as the path is clear
 * 
 * @author Max Zhou (mjz94)
 * @author Dhruv Patel (dvp67)
 * @version 1.0 
 * @since 2022-03-23
 */
public class Bishop {
	
	/** 
	 * This method checks if the Bishop is able to move to the position entered
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return if the bishop can move return true otherwise return false
	 */

	public static boolean move (Board board, String turn, int[] originalCoords, int[] destinationCoords)
    {
		if(originalCoords[0] == destinationCoords[0] || originalCoords[1] == destinationCoords[1]){
			//Did not move diagonally
			return false;
		}

        if(Math.abs(destinationCoords[0] - originalCoords[0]) != Math.abs(destinationCoords[1] - originalCoords[1])){
			return false;
		}
        
        // Checks if pieces are blocking the diagonal in the direction of the destination coordinates
		if (checkIfBlockingDiagonal(turn, board, originalCoords, destinationCoords))
        {
            return false;
        }

        // Checks if the piece at the destination coordinate is the same color
        if (turn.equals("White")) {
            if (board.getPiece(destinationCoords[0], destinationCoords[1]).getColor() == 'w') {
                return false;
            }
        }
        else {
            if (board.getPiece(destinationCoords[0], destinationCoords[1]).getColor() == 'b') {
                return false;
            }
        }
        
        moveBishop(turn, board, originalCoords, destinationCoords);
		
		int rowOffset, colOffset;
		
		if(originalCoords[0] < destinationCoords[0]){
			rowOffset = 1;
			moveBishop(turn, board, originalCoords, destinationCoords);

		}else{
			rowOffset = -1;
			moveBishop(turn, board, originalCoords, destinationCoords);
		}
		
		if(originalCoords[1] < destinationCoords[1]){
			colOffset = 1;
			moveBishop(turn, board, originalCoords, destinationCoords);

		}else{
			colOffset = -1;
			moveBishop(turn, board, originalCoords, destinationCoords);

		}
		
		return true;
		
	}
	
	
	/** 
	 * This method checks if the path is blocked by any piece whether it be the same team or opposite teams
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return if path isn't clear return false otherwise return true 
	 */
	
	private static boolean checkIfBlockingDiagonal (String turn, Board board, int[] originalCoords, int[] destinationCoords)
    {
        // Check if bishop is moving top left
        if (destinationCoords[0] < originalCoords[0] && destinationCoords[1] < originalCoords[1]) {
            for (int i = 1; i < Math.abs(originalCoords[0] - destinationCoords[0]); i++) {
                int[] temp = new int[2];
                temp[0] = originalCoords[0] - i;
                temp[1] = originalCoords[1] - i;
                if (!board.isEmpty(temp)) {
                    return true;
                }
            }
        }

        // Check if bishop is moving top right
        else if (destinationCoords[0] < originalCoords[0] && destinationCoords[1] > originalCoords[1]) {
            for (int i = 1; i < Math.abs(originalCoords[0] - destinationCoords[0]); i++) {
                int[] temp = new int[2];
                temp[0] = originalCoords[0] - i;
                temp[1] = originalCoords[1] + i;
                if (!board.isEmpty(temp)) {
                    return true;
                }
            }
        }

        // Check if bishop is moving bottom left
        else if (destinationCoords[0] > originalCoords[0] && destinationCoords[1] < originalCoords[1]) {
            for (int i = 1; i < Math.abs(originalCoords[0] - destinationCoords[0]); i++) {
                int[] temp = new int[2];
                temp[0] = originalCoords[0] + i;
                temp[1] = originalCoords[1] - i;
                if (!board.isEmpty(temp)) {
                    return true;
                }
            }
        }

        // Check if bishop is moving bottom right
        else {
            for (int i = 1; i < Math.abs(originalCoords[0] - destinationCoords[0]); i++) {
                int[] temp = new int[2];
                temp[0] = originalCoords[0] + i;
                temp[1] = originalCoords[1] + i;
                if (!board.isEmpty(temp)) {
                    return true;
                }
            }
        }

        return false;
    }

	
	/** 
	 * This method moves the Bishop 
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return boolean true if Bishop moved otherwise false
	 */
	 private static void moveBishop (String turn, Board board, int[] originalCoords, int[] destinationCoords)
     {
         board.getPiece(originalCoords[0], originalCoords[1]).setType(board.getReferenceBoardString(originalCoords[0], originalCoords[1]));
         if (turn.equals("White"))
         {
         	 board.getPiece(destinationCoords[0], destinationCoords[1]).setType("wB");
         }
            
         else
         {
         	board.getPiece(destinationCoords[0], destinationCoords[1]).setType("bB");
         }
     }
        

}