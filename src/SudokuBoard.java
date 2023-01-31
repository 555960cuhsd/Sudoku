/**
 * Represents a Sudoku board. All rows and columns are numbered
 * from 1 to 9. Boxes are numbered from the top left across
 * and then down starting at 1. A 0 in the board represents a
 * blank space.
 * @author Justin Lee
 */
public class SudokuBoard
{
    // Instance variable for Sudoku board
    int[][] board = new int[9][9];

    /**
     * SudokuBoard constructor, constructs the Sudoku board given a 2d array.
     * @param arr 2d array representing a Sudoku board.
     */
    public SudokuBoard(int[][] arr) {
        for (int i = 0; i < arr[0].length; i++) {
            for (int j = 0; j < arr.length; j++) {
                board[i][j] = arr[i][j];
            }
        }
    }

    /**
     * Takes user input to replace a value in the Sudoku board with the user's guess.
     * @param guess The number to replace with
     * @param row The guess' row in the Sudoku board
     * @param column The guess' column in the Sudoku board
     */
    public void makeGuess(int guess, int row, int column) {
        board[row-1][column-1] = guess;
    }

    /**
     * Checks if the given row is valid by Sudoku's rules.
     * @param row 1d array representing the given row.
     * @return boolean value for whether the row is valid or not.
     */
    public boolean isValidRow(int row){

        boolean valid = true;
        for (int i = 0; i < board[0].length-1; i++) {
            for (int j = 1; j < board[0].length; j++) {
                if (j+i > board[0].length-1) {
                    break;
                }
                else if (board[row][i] == board[row][j+i]) {
                    valid = false;
                }
            }
        }
        return valid;
    }

    /**
     * Checks if the given column is valid by Sudoku's rules.
     * @param column 1d array representing the given column.
     * @return boolean value for whether the column is valid or not.
     */
    public boolean isValidColumn(int column){
        boolean valid = true;
        for (int i = 0; i < board[0].length-1; i++) {
            for (int j = 1; j < board[0].length; j++) {
                if (j+i > board[0].length-1) {
                    break;
                }
                else if (board[i][column] == board[j+i][column]) {
                    valid = false;
                }
            }
        }
        return valid;
    }

    /**
     * Checks if the given box is valid by Sudoku's rules.
     * @param boxNumber integer representing which number box is given.
     * @return boolean value for whether the box is valid or not.
     */
    public boolean isValidBox(int boxNumber) {
        // Setting the row and column to be the top left number in the box.
        int column = 0;
        int row = 0;
        switch (boxNumber) {
            case 0: row = 0;
                column = 0;
                break;
            case 1: row = 3;
                column = 0;
                break;
            case 2: row = 6;
                column = 0;
                break;
            case 3: row = 0;
                column = 3;
                break;
            case 4: row = 3;
                column = 3;
                break;
            case 5: row = 6;
                column = 3;
                break;
            case 6: row = 0;
                column = 6;
                break;
            case 7: row = 3;
                column = 6;
                break;
            case 8: row = 6;
                column = 6;
                break;
        }
        // Getting the box as a 2d array
        int[][] box = {
                {board[column][row], board[column][row+1], board[column][row+2]},
                {board[column+1][row], board[column+1][row+1], board[column+1][row+2]},
                {board[column+2][row], board[column+2][row+1], board[column+2][row+2]}
        };

        // Checking for repeated numbers in the box.

        int k = 0; // indexer for the current box column
        int j = 0; // indexer for the current box row
        int firstMark = 0;

        // Checks by going across the box row, then down a column, and checking with the new row, and repeat until the index goes out of the box.
        // Once one number checks all the numbers to the right and below, the loop moves on to check the next number compared to the rest and so on.

        // Loop to go across the box's row.
        for (int columnStarter = 0; columnStarter < box.length; columnStarter++) {
            // Validating the 3-long row in the box.
            for (int boxStarter = 0; boxStarter < box[0].length; boxStarter++) {
                int rowStarter = boxStarter;
                while (columnStarter + k <= 2) {
                    // Checking if the index for the current box row is over.
                    if (rowStarter + j > 2) { // If the row + the index goes over the box, go over to the next box column.
                        k++;
                        j = 0;
                        rowStarter = 0;
                    }
                    // Checking if the index for the current box column is over.
                    if (columnStarter + k > 2) { // If the column + the index goes over the box, quit.
                        break;
                    }
                    // Looking for a duplicate number
                    if (box[columnStarter][boxStarter] == box[columnStarter + k][rowStarter + j]) {
                        if (firstMark != 0) { // Since it will mark itself as equal, valid only becomes false on the second time.
                            return false;
                        } else {
                            firstMark++;
                        }
                    }
                    // Incrementing the index for the current box row.
                    j++;
                }
                // Moving on to the next number in the box to compare to the rest
                firstMark = 0;
                k = 0;
                j = 0;
            }
        }
        return true;
    }

    /**
     * Checks if the game is over by the Sudoku rules.
     * @return boolean value for whether the game is complete or not.
     */
    public boolean isGameOver(){
        // Checking for 0
        for (int[] row : board) {
            for (int j = 0; j < board[0].length; j++) {
                if (row[j] == 0) {
                    return false;
                }
            }
        }
        // Checking for valid rows and columns
        for (int j = 0 ; j < 9; j++) {
            if (!isValidRow(j) || !isValidColumn(j)) {
                return false;
            }
        }
        // Checking for valid boxes
        for (int k = 0 ; k < 9; k++) {
            if (!isValidBox(k)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Prints the current board state.
     */
    public void printBoard() {
        System.out.println("-------------------------");
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                if(c == 0){
                    System.out.print("|");
                }
                if (c % 3 == 0) {
                    if(board[r][c] == 0){
                        System.out.print(" _ ");
                    }
                    else{
                        System.out.print(" " + board[r][c] + " ");
                    }
                }
                else{
                    if(board[r][c] == 0){
                        System.out.print("_ ");
                    }
                    else{
                        System.out.print(board[r][c] + " ");
                    }
                }
                if(c == 8){
                    System.out.println("|");
                }
                else if (c % 3 == 2) {
                    System.out.print("|");
                }
            }
            if(r % 3 == 2){
                System.out.println("-------------------------");
            }
        }
        System.out.println();
    }
}