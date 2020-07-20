import java.util.Scanner;

public class TicTacToe {
    private static final int NUMBER_OF_ROWS_AND_COLUMNS = 3;
    private static final int MAX_GRID_POSITION = NUMBER_OF_ROWS_AND_COLUMNS *
            NUMBER_OF_ROWS_AND_COLUMNS;

    private static GRID_STATUS[][] gameBoard = new
            GRID_STATUS[NUMBER_OF_ROWS_AND_COLUMNS][NUMBER_OF_ROWS_AND_COLUMNS];
    private static String player1Name;
    private static String player2Name;

    public static void main(String[] args) {
        playTicTacToe();
    }

    private static void playTicTacToe() {

        System.out.println("Welcome to TicTacToe!\n");

        printInstructions();

        Scanner in = new Scanner(System.in);
        System.out.print("Please enter player 1's (X) name: ");
        player1Name = in.nextLine();
        System.out.print("Please enter player 2's (O) name: ");
        player2Name = in.nextLine();

        if (!isGameFinished()) {

        }
    }

    private static boolean isGameFinished() {
        return isTie() || isWin();
    }

    private static boolean isTie() {
        // True When there is no winning move possible.
    }

    private static boolean isWin() {
        // True When 3 in a row.
        for (GRID_STATUS[] gridRow : ) {

        }
    }

    private static boolean isGridPositionOccupied(int gridPosition) {
        return gameBoard[convertPositionNumberToRowNumber(gridPosition)]
                [convertPositionNumberToColumnNumber(gridPosition)] == null;
    }

    private static boolean isGridPositionOccupied(int gridRow, int gridColumn) {
        return gameBoard[gridRow][gridColumn] == null;
    }

    private static int convertPositionNumberToRowNumber(int gridPosition) {
        if (gridPosition < 1 || gridPosition > MAX_GRID_POSITION) {
            throw new IllegalArgumentException("Grid Position cannot be less " +
                    "than 1 or more than " + MAX_GRID_POSITION + ".");
        } else if (gridPosition <= 3) {
            return 0;
        } else if (gridPosition <= 6) {
            return 1;
        } else {
            return 2;
        }
    }

    private static int convertPositionNumberToColumnNumber(int gridPosition) {
        if (gridPosition < 1 || gridPosition > MAX_GRID_POSITION) {
            throw new IllegalArgumentException("Grid Position cannot be less " +
                    "than 1 or more than " + MAX_GRID_POSITION + ".");
        } else if (gridPosition % 3 == 1) {
            return 0;
        } else if (gridPosition % 3 == 2) {
            return 1;
        } else {
            return 2;
        }
    }

    private static void printInstructions() {
        System.out.println("How to Play:\n");
        System.out.println("Every turn enter the position at which you want your " +
                "symbol (O or X) to be placed.");
        System.out.println("Use the diagram below to familiarise yourself with " +
                "the position numbers:\n");

        System.out.println("\t1\t|\t2\t|\t3\t");
        System.out.println("  ------|-------|------");
        System.out.println("\t4\t|\t5\t|\t6\t");
        System.out.println("  ------|-------|------");
        System.out.println("\t7\t|\t8\t|\t9\t");

        System.out.println("\nEg. to place a symbol in the centre type 5 on " +
                "your turn.");
        System.out.println("If you want to see the instructions again type I at " +
                "any time."); //TODO type I for instructions
    }
}
