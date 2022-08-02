package gameElements.GamePieces;

import gameElements.Board;

/** 
 * The King class consists of all the movements and functionality for King 
 * The King can move horizontally or vertically or diagonally one space in any direction 
 * The King can call castling where the King moves left or right two spaces from its string position and King moves to the opposite side of King  
 * 
 * @author Max Zhou (mjz94)
 * @author Dhruv Patel (dvp67)
 * @version 1.0 
 * @since 2022-03-23
 */

public class King {
	
	/** 
	 * This is used for castling to check if it has moved
     * @param hasMoved which is returned as false for castling
     * @return if the King can move return true otherwise return false
	 */
	
	static boolean hasMoved = false;
	
	/** 
	 * This is used for castling to check if there is castling
     * @param castling which is returned as false for castling
     * @return if the King can move return true otherwise return false
	 */
	
	static boolean castling = false;
    
	/** 
	 * This method checks if the King is able to move to the position entered
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return if the King can move return true otherwise return false
	 */
	
	public static boolean move (Board board, String turn, int[] originalCoords, int[] destinationCoords)
    {
		
        
		int offset, rowOffset, colOffset;
		
		if (Math.abs(originalCoords[0] - destinationCoords[0]) > 1)
        {
                return false;
        }
		if (Math.abs(originalCoords[1] - destinationCoords[1]) > 2)
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
		
		
		if (checkCastling(turn, board, originalCoords, destinationCoords) == false)
        {
            return true;
            
        }
        
		
		
		if(originalCoords[0] != destinationCoords[0]){
			
			if(originalCoords[0] < destinationCoords[0]){
				offset = 1;
				moveKing(turn, board, originalCoords, destinationCoords);
			}else{
				offset = -1;
				moveKing(turn, board, originalCoords, destinationCoords);
			}
			
			if(originalCoords[0] < destinationCoords[0]){
				rowOffset = 1;
				moveKing(turn, board, originalCoords, destinationCoords);

			}else{
				rowOffset = -1;
				moveKing(turn, board, originalCoords, destinationCoords);
			}
			
			for(int x = originalCoords[0] + offset; x != destinationCoords[0]; x += offset){
				//Go from originalCoords[0] to destinationCoords[0], and check every space
			}
			
		}
	
		//Now do the same for columns
		if(originalCoords[1] != destinationCoords[1]) {
			if(originalCoords[1] < destinationCoords[1]){
				offset = 1;
				moveKing(turn, board, originalCoords, destinationCoords);
			}else{
				offset = -1;
				moveKing(turn, board, originalCoords, destinationCoords);
			}
			if(originalCoords[1] < destinationCoords[1]){
				colOffset = 1;
				moveKing(turn, board, originalCoords, destinationCoords);

			}else{
				colOffset = -1;
				moveKing(turn, board, originalCoords, destinationCoords);
			}
			
			for(int x = originalCoords[1] + offset; x != destinationCoords[1]; x += offset){
				//Go from originalCoords[1] to destinationCoords[1], and check every space
				
			}
		}
		return true;
		
    }
		
		// 0 y row
		// 1 x column
		
		//BLACK
	
	/** 
	 * This method calls for castling to happen
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return boolean true if King and Rook moved otherwise false
	 */
	
	private static boolean checkCastling (String turn, Board board, int[] originalCoords, int[] destinationCoords) {

		if(hasMoved == false && originalCoords[0] == 0) {
			int[] original1 = {0,0};
			int[] spot1 = {0,1}; 
			int[] spot2 = {0,2}; 
			int[] spot3 = {0,3}; 
			


			if (board.isEmpty(spot1) && board.isEmpty(spot2) && board.isEmpty(spot3) && board.getPiece(0, 0).hasMoved == false &&
					originalCoords[1] - destinationCoords[1] == 2) {

				if(!board.isEmpty(destinationCoords)) {
					moveKing(turn, board, originalCoords, destinationCoords);
				}
				

				int[] destination1 = {0,3};
				King.moveKing(turn, board, original1, destination1);
				return true;
			}
		}
		

			
			//another if to see if black can move two to the left
		
		if(hasMoved == false && originalCoords[0] == 0) {
			int[] original4 = {0,7};
			int[] spot1 = {0,5}; 
			int[] spot2 = {0,6}; 


			if (board.isEmpty(spot1) && board.isEmpty(spot2) && board.getPiece(0, 7).hasMoved == false &&
					(destinationCoords[1] - originalCoords[1] == 2) ){
				
				if(!board.isEmpty(destinationCoords)) {
					moveKing(turn, board, originalCoords, destinationCoords);
				}
				
				

				int[] destination4 = {0,5};
				King.moveKing(turn, board, original4, destination4);
				return true;
			}
		}
		
		
		
		
		
		
		if(hasMoved == false && originalCoords[0] == 7) {
			int[] original3 = {7,0};
			int[] spot1 = {7,1}; 
			int[] spot2 = {7,2}; 
			int[] spot3 = {7,3}; 
			

			if (board.isEmpty(spot1) && board.isEmpty(spot2) && board.isEmpty(spot3) && board.getPiece(7, 0).hasMoved == false && originalCoords[1] - destinationCoords[1] == 2) {

				if(!board.isEmpty(destinationCoords)) {
					moveKing(turn, board, originalCoords, destinationCoords);
				}
				

				int[] destination3 = {7,3};
				King.moveKing(turn, board, original3, destination3);
				return true;

			}
		}
		
		
		
		
		if(hasMoved == false && originalCoords[0] == 7) {
			int[] original4 = {7,7};
			int[] spot1 = {7,5}; 
			int[] spot2 = {7,6}; 


			if (board.isEmpty(spot1) && board.isEmpty(spot2) && board.getPiece(7, 7).hasMoved == false && destinationCoords[1] - originalCoords[1] == 2) {
				
				if(!board.isEmpty(destinationCoords)) {
					moveKing(turn, board, originalCoords, destinationCoords);
				}
				
				

				int[] destination4 = {7,5};
				King.moveKing(turn, board, original4, destination4);
				return true;
			}
		}
		//WHITE
		
		hasMoved = true;
		return true;
    }
	
	/** 
	 * This method moves the King 
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return boolean true if King moved otherwise false
	 */
	
	 private static void moveKing (String turn, Board board, int[] originalCoords, int[] destinationCoords)
     {
         board.getPiece(originalCoords[0], originalCoords[1]).setType(board.getReferenceBoardString(originalCoords[0], originalCoords[1]));
         if (turn.equals("White"))
         {
         	 board.getPiece(destinationCoords[0], destinationCoords[1]).setType("wK");
         }
            
         else
         {
         	board.getPiece(destinationCoords[0], destinationCoords[1]).setType("bK");
         }
     }
        

}

