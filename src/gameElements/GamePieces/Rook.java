package gameElements.GamePieces;

import gameElements.Board;

/** 
 * The Rook class consists of all the movements and functionality for Rook 
 * The Rook can move horizontally or vertically in any direction as long as the path is clear
 * 
 * @author Max Zhou (mjz94)
 * @author Dhruv Patel (dvp67)
 * @version 1.0 
 * @since 2022-03-23
 */

public class Rook {
	
	/** 
	 * This is used for castling to check if it has moved
     * @param hasMoved which is returned as false for castling
     * @return if the Rook can move return true otherwise return false
	 */
	
	static boolean hasMoved = false;
    
	
	/** 
	 * This method checks if the Rook is able to move to the position entered
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return if the Rook can move return true otherwise return false
	 */
	
	public static boolean move (Board board, String turn, int[] originalCoords, int[] destinationCoords)
    {

        // Check if the pawn is moving forward
		if(originalCoords[0] != destinationCoords[0] && originalCoords[1] != destinationCoords[1]){
			//Did not move along one rank/file
			return false;
		}
		if (checkIfBlockingInFront(turn, board, originalCoords, destinationCoords) == false)
        {
            return false;
        }
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
        
        moveRook(turn, board, originalCoords, destinationCoords);

		
		//First I will assumed the Rook is moving along the rows.
		int offset;
		
		if(originalCoords[0] != destinationCoords[0]){
			if(originalCoords[0] < destinationCoords[0]){
				offset = 1;
				moveRook(turn, board, originalCoords, destinationCoords);
			}else{
				offset = -1;
				moveRook(turn, board, originalCoords, destinationCoords);
			}
			
			for(int x = originalCoords[0] + offset; x != destinationCoords[0]; x += offset){
				//Go from originalCoords[0] to destinationCoords[0], and check every space
			}
		}
	
		//Now do the same for columns
		if(originalCoords[1] != destinationCoords[1]){
			if(originalCoords[1] < destinationCoords[1]){
				offset = 1;
				moveRook(turn, board, originalCoords, destinationCoords);
			}else{
				offset = -1;
				moveRook(turn, board, originalCoords, destinationCoords);
			}
			
			for(int x = originalCoords[1] + offset; x != destinationCoords[1]; x += offset){
				//Go from originalCoords[1] to destinationCoords[1], and check every space
				
			}
		}
		
		
		hasMoved = true;
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
	
	private static boolean checkIfBlockingInFront (String turn, Board board, int[] originalCoords, int[] destinationCoords)
    {
        // Check if rook is moving up
        if (originalCoords[0] > destinationCoords[0]) {
            for (int i = 1; i < Math.abs(originalCoords[0] - destinationCoords[0]); i++) {
                int[] temp = new int[2];
                temp[0] = originalCoords[0] - i;
                temp[1] = originalCoords[1];
                if (!board.isEmpty(temp)) {
                    return false;
                }
            }
        }

        // Check if rook is moving left
        if (originalCoords[1] > destinationCoords[1]) {
            for (int i = 1; i < Math.abs(originalCoords[1] - destinationCoords[1]); i++) {
                int[] temp = new int[2];
                temp[0] = originalCoords[0];
                temp[1] = originalCoords[1] - i;
                if (!board.isEmpty(temp)) {
                    return false;
                }
            }
        }

        // Check if rook is moving down
        if (originalCoords[0] < destinationCoords[0]) {
            for (int i = 1; i < Math.abs(originalCoords[0] - destinationCoords[0]); i++) {
                int[] temp = new int[2];
                temp[0] = originalCoords[0] + i;
                temp[1] = originalCoords[1];
                if (!board.isEmpty(temp)) {
                    return false;
                }
            }
        }

        // Check if rook is moving right
        if (originalCoords[1] < destinationCoords[1]) {
            for (int i = 1; i < Math.abs(originalCoords[1] - destinationCoords[1]); i++) {
                int[] temp = new int[2];
                temp[0] = originalCoords[0];
                temp[1] = originalCoords[1] + i;
                if (!board.isEmpty(temp)) {
                    return false;
                }
            }
        }

        return true;
    }
	
	/** 
	 * This method moves the Rook 
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return boolean true if Rook moved otherwise false
	 */
	
	 static void moveRook (String turn, Board board, int[] originalCoords, int[] destinationCoords)
     {
         board.getPiece(originalCoords[0], originalCoords[1]).setType(board.getReferenceBoardString(originalCoords[0], originalCoords[1]));
         if (turn.equals("White"))
         {
         	 board.getPiece(destinationCoords[0], destinationCoords[1]).setType("wR");
         }
            
         else
         {
         	board.getPiece(destinationCoords[0], destinationCoords[1]).setType("bR");
         }
     }
        

}
