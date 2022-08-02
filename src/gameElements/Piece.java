package gameElements;

/**
 * The Piece class contains the elements that make up a piece to be on the board
 * 
 * @author Max Zhou (mj94)
 * @author Dhruv Patel (dvp67)
 * @version 1.0
 * @since 2022-03-23
 */

public class Piece {
    
    /**
     * The status of whether a piece has been moved yet
     */
    public boolean hasMoved;
	
    /**
     * The name of the piece
     */
	private String type;    // type can be only: [color] followed by 'p', 'R', 'N', 'B', 'Q', 'K' or "  " or "##".

    /**
     * The constructor to create a Piece object without a specified starting name
     */
    public Piece ()
    {
        type = null;
    }

    /**
     * The constructor to create a Piece object with a specified starting name
     * @param s piece
     */
    public Piece (String s)
    {
        type = s;
    }

    /**
     * Sets the current piece to a new type
     * @param s the new name of the piece
     */
    public void setType (String s)
    {
        type = s;
    }

    /**
     * Gets the name of the piece
     * @return the name of the piece
     */
    public String getType ()
    {
        return type;
    }

    /**
     * Gets the color of the piece
     * @return the color of the piece
     */
    public char getColor ()
    {
        return type.charAt(0);
    }
}