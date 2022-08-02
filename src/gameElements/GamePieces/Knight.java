package gameElements.GamePieces;

import gameElements.Board;


/** 
 * The Knight class consists of all the movements and functionality for Knight 
 * The Knight can only move 4 spaces in any direction and in an L shape consisting of 2 spaces vertically and one space horizontally OR 2 spaces horizontally and one space vertically 
 * 
 * @author Max Zhou (mjz94)
 * @author Dhruv Patel (dvp67)
 * @version 1.0 
 * @since 2022-03-23
 */

public class Knight {
    
	
	/** 
	 * This method checks if the Knight is able to move to the position entered
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return if the Knight can move return true otherwise return false
	 */
	
	public static boolean move (Board board, String turn, int[] originalCoords, int[] destinationCoords)
    {

        if (board.getPiece(destinationCoords[0], destinationCoords[1]).getColor() == turn.toLowerCase().charAt(0)) {
            return false;
        }

		if(Math.abs(destinationCoords[0] - originalCoords[0]) == 2 && Math.abs(destinationCoords[1] - originalCoords[1]) == 1){
            moveKnight(turn, board, originalCoords, destinationCoords);
			return true;
		}
		
		if(Math.abs(destinationCoords[0] - originalCoords[0]) == 1 && Math.abs(destinationCoords[1] - originalCoords[1]) == 2){
            moveKnight(turn, board, originalCoords, destinationCoords);

			return true;
		}
		
		return false;
	}
	
	/** 
	 * This method moves the Knight 
	 * @param board the board that houses all the pieces
	 * @param turn the current color turn (i.e. "White" or "Black")
     * @param originalCoords of the original row and column coordinates to be checked
     * @param destinationCoords of the destination row and column coordinates to be checked
     * @return boolean true if Knight moved otherwise false
	 */
	
	 private static void moveKnight (String turn, Board board, int[] originalCoords, int[] destinationCoords)
     {
         board.getPiece(originalCoords[0], originalCoords[1]).setType(board.getReferenceBoardString(originalCoords[0], originalCoords[1]));
         if (turn.equals("White"))
         {
         	 board.getPiece(destinationCoords[0], destinationCoords[1]).setType("wN");
         }
            
         else
         {
         	board.getPiece(destinationCoords[0], destinationCoords[1]).setType("bN");
         }
     }
        

}
	
