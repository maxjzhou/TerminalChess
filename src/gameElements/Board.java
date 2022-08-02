package gameElements;

/**
 * The Board class creates a new board to be played on.
 * 
 * @author Max Zhou (mj94)
 * @author Dhruv Patel (dvp67)
 * @version 1.0
 * @since 2022-03-23
 */

public class Board {
    
    /**
     * Blank board to refer to which squares and black and white
     */
    private String[][] referenceBoard =
    {
        {"  ","##","  ","##","  ","##","  ","##"},
        {"##","  ","##","  ","##","  ","##","  "},
        {"  ","##","  ","##","  ","##","  ","##"},
        {"##","  ","##","  ","##","  ","##","  "},
        {"  ","##","  ","##","  ","##","  ","##"},
        {"##","  ","##","  ","##","  ","##","  "},
        {"  ","##","  ","##","  ","##","  ","##"},
        {"##","  ","##","  ","##","  ","##","  "},
    };
    
    /**
     * 2d array of pieces to create the Board
     */
    private Piece[][] pieces;

    /**
     * Constructor for creating a new Board
     */
    public Board()
    {
        pieces = new Piece[9][9];
    }

    /**
     * Sets up a new Board 
     */
    public void setupBoard ()
    {
        pieces[0][0] = new Piece("bR");
        pieces[0][1] = new Piece("bN");
        pieces[0][2] = new Piece("bB");
        pieces[0][3] = new Piece("bQ");
        pieces[0][4] = new Piece("bK");
        pieces[0][5] = new Piece("bB");
        pieces[0][6] = new Piece("bN");
        pieces[0][7] = new Piece("bR");

        pieces[7][0] = new Piece("wR");
        pieces[7][1] = new Piece("wN");
        pieces[7][2] = new Piece("wB");
        pieces[7][3] = new Piece("wQ");
        pieces[7][4] = new Piece("wK");
        pieces[7][5] = new Piece("wB");
        pieces[7][6] = new Piece("wN");
        pieces[7][7] = new Piece("wR");

        pieces[8][0] = new Piece(" a");
        pieces[8][1] = new Piece(" b");
        pieces[8][2] = new Piece(" c");
        pieces[8][3] = new Piece(" d");
        pieces[8][4] = new Piece(" e");
        pieces[8][5] = new Piece(" f");
        pieces[8][6] = new Piece(" g");
        pieces[8][7] = new Piece(" h");

        pieces[0][8] = new Piece("8");
        pieces[1][8] = new Piece("7");
        pieces[2][8] = new Piece("6");
        pieces[3][8] = new Piece("5");
        pieces[4][8] = new Piece("4");
        pieces[5][8] = new Piece("3");
        pieces[6][8] = new Piece("2");
        pieces[7][8] = new Piece("1");

        pieces[8][8] = new Piece(" ");

        for (int i = 0; i < 8; i++)
        {
            pieces[1][i] = new Piece("bp");
            pieces[6][i] = new Piece("wp");
        }

        for (int i = 2; i < 6; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                pieces[i][j] = new Piece(referenceBoard[i][j]);
            }
        }
        
    }

    /**
     * Loops through the entire board to print out each piece on each square
     */
    public void printBoard ()
    {
        System.out.print("\n");
        for (int i = 0; i < pieces.length; i++)
        {
            for (int j = 0; j < pieces[0].length; j++)
            {
                System.out.print(pieces[i][j].getType() + " ");
            }
            System.out.print("\n");
        }
        System.out.println();
    }

    /**
     * Checks whether a square is empty on the given coordinates
     * @param coords the (row,column) coordinates to check
     * @return true if the square is empty, false if there is a active piece occuyping the square
     */
    public boolean isEmpty (int[] coords)
    {
        String s = pieces[coords[0]][coords[1]].getType();
        if (s.equals("  ") || s.equals("##"))
        {
            return true;
        }
        return false;
    }


    /**
     * Getter method for the piece at the specified coordinates
     * @param row the row coordinate
     * @param col the column coordinate
     * @return the piece at the specified coordinate
     */
    public Piece getPiece (int row, int col)
    {
        return pieces[row][col];
    }

    /**
     * Get the correct empty square according to the reference board at the specified coordinate
     * @param row the row coordinate
     * @param col the column coordinate
     * @return the correct empty square at the specified coordinate
     */
    public String getReferenceBoardString (int row, int col)
    {
        return referenceBoard[row][col];
    }

    /**
     * Clones the parameter board to the board
     * @param b2 the board to be copied from 
     */
    public void cloneBoard (Board b2) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pieces[i][j].setType(b2.getPiece(i, j).getType());
            }
        }
    }

}
