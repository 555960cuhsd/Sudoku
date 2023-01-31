/**
 * Runs a game of sudoku.
 * @author Justin Lee
 */

import java.util.Scanner;

public class SudokuGame {
    public static void main(String[] args)
    {
        // Given Sudoku puzzle board
        int[][] puzzle = new int[][]{
                {0, 7, 1, 0, 0, 0, 0, 0, 6},
                {0, 0, 9, 0, 0, 0, 8, 0, 0},
                {5, 0, 4, 9, 0, 0, 0, 7, 1},
                {0, 0, 0, 0, 0, 0, 9, 0, 0},
                {0, 0, 0, 4, 3, 0, 0, 0, 0},
                {7, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 0, 0, 0, 6, 0, 4, 3, 5},
                {0, 0, 3, 1, 4, 0, 0, 0, 8},
                {6, 0, 0, 0, 0, 7, 0, 0, 0}
        };

        SudokuBoard sudoku = new SudokuBoard(puzzle);
        sudoku.printBoard();

        Scanner in = new Scanner(System.in);

        while (!sudoku.isGameOver()) {
            int guess = collectInput(in, "guess");
            int row = collectInput(in, "row");
            int column = collectInput(in, "column");

            // Inputting the user's guess into the board
            sudoku.makeGuess(guess, row, column);
            sudoku.printBoard();
        }

        System.out.println("Good game!");
        in.close();

    }

    /**
     * Collects the input of the given input type and validates the input
     * @param input the raw user's input
     * @param inputType the type of input that is given
     * @return The inputted integer
     */
    public static int collectInput(Scanner input, String inputType){
        while (true) {
            System.out.print("Enter " + inputType + ": ");

            if (!input.hasNextInt()) {
                System.out.println("Input must be a number!");
                System.out.println();
                input.nextLine();
                continue;
            }

            int num = input.nextInt();
            if (!(num >= 1 && num <= 9)) {
                System.out.println("Invalid "+inputType+"! Must be between 1 and 9.");
                System.out.println();
                input.nextLine();
                continue;

            }

            System.out.println();
            return num;
        }
    }

    /**
     * Based on the guess' row and column, finds the box number to validate the box. (Primarily used for debugging)
     * @param row The guess' row
     * @param column the guess' column
     * @return the box's number
     */
    public static int findBox(int row, int column) {
        if (row <= 2) {
            if (column <= 2) {
                return 0;
            }
            else if (column <= 5) {
                return 1;
            }
            else {
                return 2;
            }
        }
        else if (row <= 5) {
            if (column <= 2) {
                return 3;
            }
            else if (column <= 5) {
                return 4;
            }
            else {
                return 5;
            }
        }
        else {
            if (column <= 2) {
                return 6;
            }
            else if (column <= 5) {
                return 7;
            }
            else {
                return 8;
            }
        }
    }
}