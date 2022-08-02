package gameplay;

/**
 * The LastMove class holds the information of the last move conducted by either player
 * 
 * @author Max Zhou (mj94)
 * @author Dhruv Patel (dvp67)
 * @version 1.0
 * @since 2022-03-23
 */
public class LastMove {
    
    /**
     * The name of the piece
     */
    private String s;
    /**
     * The starting row
     */
    private int originalRow;
    /**
     * The ending row
     */
    private int destinationRow;
    /**
     * The starting column
     */
    private int originalCol;
    /**
     * The ending column
     */
    private int destinationCol;

    /**
     * Constructor to create a new LastMove object
     */
    public LastMove () {
        s = "z";
        originalRow = -1;
        destinationRow = -1;
        originalCol = -1;
        destinationCol = -1;
    }

    /**
     * Gets the name of the piece
     * @return the name of the piece
     */
    public String getS () {
        return s;
    }

    /**
     * Gets the starting row
     * @return the starting row
     */
    public int getOriginalRow () {
        return originalRow;
    }

    /**
     * Gets the ending row
     * @return the ending row
     */
    public int getDestinationRow () {
        return destinationRow;
    }

    /**
     * Gets the starting column
     * @return the starting column
     */
    public int getOriginalCol () {
        return originalCol;
    }

    /**
     * Gets the ending column
     * @return the ending column
     */
    public int getDestinationCol () {
        return destinationCol;
    }

    /**
     * Sets the current information of the LastMove object to the specified balues
     * @param newS the new name
     * @param newOriginalRow the new starting row
     * @param newDestinationRow the new ending row
     * @param newOriginalCol the new starting column
     * @param newDestinationCol the new ending column
     */
    public void setInfo (String newS, int newOriginalRow, int newDestinationRow, int newOriginalCol, int newDestinationCol) {
        s = newS;
        originalRow = newOriginalRow;
        destinationRow = newDestinationRow;
        originalCol = newOriginalCol;
        destinationCol = newDestinationCol;
    }

}
