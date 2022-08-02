package gameplay;

/**
 * The Checkmated class contains information on the status of whether a piece is under checkmate
 * 
 * @author Max Zhou (mj94)
 * @author Dhruv Patel (dvp67)
 * @version 1.0
 * @since 2022-03-23
 */
public class Checkmated {
    
    /**
     * Boolean that holds true if the object is under checkmate, false otherwise
     */
    private boolean c;

    /**
     * Constructor to create a new Checkmated object
     */
    public Checkmated () {
        c = false;
    }

    /**
     * Sets the status of c to true
     */
    public void isCheckmated () {
        c = true;
    }

    /**
     * Gets the status of the current checkmated object
     * @return the value of c
     */
    public boolean getCheckmateStatus () {
        return c;
    }

}
