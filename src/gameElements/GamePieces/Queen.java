package gameElements.GamePieces;

import gameElements.Board;

/** 
 * The Queen class consists of all the movements and functionality for Queen 
 * The Queen can move diagonally in any direction as long as the path is clear
 * The Queen can move horizontally and vertically in any direction as long as the path is clear
 * 
 * @author Max Zhou (mjz94)
 * @author Dhruv Patel (dvp67)
 * @version 1.0 
 * @since 2022-03-23
 */

public class Queen
{
    
	/** 
	 * This method checks if the Queen is able to move to the position entered
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return if the Queen can move return true otherwise return false
	 */
	public static boolean move (Board board, String turn, int[] originalCoords, int[] destinationCoords)
    {
		
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
        
       // moveQueen(turn, board, originalCoords, destinationCoords);
        


		int offset, rowOffset, colOffset;
		
		
		//DIAGONAL
        if(Math.abs(destinationCoords[0] - originalCoords[0]) == Math.abs(destinationCoords[1] - originalCoords[1])){

        	if(!checkIfBlockingDiagonal(turn, board, originalCoords, destinationCoords)) {
            	

        		moveQueen(turn, board, originalCoords, destinationCoords);
      		
        		
        		if(originalCoords[0] < destinationCoords[0]){
        			rowOffset = 1;
        			moveQueen(turn, board, originalCoords, destinationCoords);

        		}else{
        			rowOffset = -1;
        			moveQueen(turn, board, originalCoords, destinationCoords);
        		}
        		
        		if(originalCoords[1] < destinationCoords[1]){
        			colOffset = 1;
        			moveQueen(turn, board, originalCoords, destinationCoords);

        		}else{
        			colOffset = -1;
        			moveQueen(turn, board, originalCoords, destinationCoords);

        		}
        		return true;
        		
        	}
			
		}
		

        //Horizontal
        if (checkIfBlockingInFront(turn, board, originalCoords, destinationCoords) != true)
        {
        	moveQueen(turn, board, originalCoords, destinationCoords);
        	
    		
    		
    		if(originalCoords[0] != destinationCoords[0]){
    			if(originalCoords[0] < destinationCoords[0]){
    				offset = 1;
    				moveQueen(turn, board, originalCoords, destinationCoords);
    			}else{
    				offset = -1;
    				moveQueen(turn, board, originalCoords, destinationCoords);
    			}
    			
    			for(int x = originalCoords[0] + offset; x != destinationCoords[0]; x += offset){
    				//Go from originalCoords[0] to destinationCoords[0], and check every space
    			}
    		}
    		
    		if(originalCoords[1] != destinationCoords[1]){
    			if(originalCoords[1] < destinationCoords[1]){
    				offset = 1;
    				moveQueen(turn, board, originalCoords, destinationCoords);
    			}else{
    				offset = -1;
    				moveQueen(turn, board, originalCoords, destinationCoords);
    			}
    			
    			for(int x = originalCoords[1] + offset; x != destinationCoords[1]; x += offset){
    				//Go from originalCoords[1] to destinationCoords[1], and check every space
    				
    			}
    		}
    		return true;
        
        }
        //Vertical

	

		return false;
    }
	
	/** 
	 * This method checks if the path is blocked by any piece whether it be the same team or opposite teams
	 * It checks for blocking diagonally in any direction
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return if path isn't clear return false otherwise return true 
	*/
	
	
	private static boolean checkIfBlockingDiagonal (String turn, Board board, int[] originalCoords, int[] destinationCoords)
    {
        // Check if Queen is moving top left
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

        // Check if Queen is moving top right
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

        // Check if Queen is moving bottom left
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

        // Check if Queen is moving bottom right
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
	 * This method checks if the path is blocked by any piece whether it be the same team or opposite teams
	 * It checks for blocking vertically and horizontally
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return if path isn't clear return false otherwise return true 
	*/
	
	private static boolean checkIfBlockingInFront (String turn, Board board, int[] originalCoords, int[] destinationCoords)
    {
        // Check if Queen is moving up
        if (originalCoords[0] > destinationCoords[0]) {
            for (int i = 1; i < Math.abs(originalCoords[0] - destinationCoords[0]); i++) {
                int[] temp = new int[2];
                temp[0] = originalCoords[0] - i;
                temp[1] = originalCoords[1];
                if (!board.isEmpty(temp)) {
                    return true;
                }
            }
        }

        // Check if Queen is moving left
        if (originalCoords[1] > destinationCoords[1]) {
            for (int i = 1; i < Math.abs(originalCoords[1] - destinationCoords[1]); i++) {
                int[] temp = new int[2];
                temp[0] = originalCoords[0];
                temp[1] = originalCoords[1] - i;
                if (!board.isEmpty(temp)) {
                    return true;
                }
            }
        }

        // Check if Queen is moving down
        if (originalCoords[0] < destinationCoords[0]) {
            for (int i = 1; i < Math.abs(originalCoords[0] - destinationCoords[0]); i++) {
                int[] temp = new int[2];
                temp[0] = originalCoords[0] + i;
                temp[1] = originalCoords[1];
                if (!board.isEmpty(temp)) {
                    return true;
                }
            }
        }

        // Check if Queen is moving right
        if (originalCoords[1] < destinationCoords[1]) {
            for (int i = 1; i < Math.abs(originalCoords[1] - destinationCoords[1]); i++) {
                int[] temp = new int[2];
                temp[0] = originalCoords[0];
                temp[1] = originalCoords[1] + i;
                if (!board.isEmpty(temp)) {
                    return true;
                }
            }
        }

        return false;
    }
	
	
	/** 
	 * This method moves the Queen 
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return boolean true if Queen moved otherwise false
	 */
	
	 private static void moveQueen (String turn, Board board, int[] originalCoords, int[] destinationCoords)
     {
         board.getPiece(originalCoords[0], originalCoords[1]).setType(board.getReferenceBoardString(originalCoords[0], originalCoords[1]));
         if (turn.equals("White"))
         {
         	 board.getPiece(destinationCoords[0], destinationCoords[1]).setType("wQ");
         }
            
         else
         {
         	board.getPiece(destinationCoords[0], destinationCoords[1]).setType("bQ");
         }
     }
        

}