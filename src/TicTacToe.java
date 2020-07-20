import java.util.Scanner;

public class TicTacToe {
    private static final int NUMBER_OF_ROWS_AND_COLUMNS = 3;
    private static final int MAX_GRID_POSITION = NUMBER_OF_ROWS_AND_COLUMNS *
            NUMBER_OF_ROWS_AND_COLUMNS;

    private static GRID_STATUS[][] gameBoard = new
            GRID_STATUS[NUMBER_OF_ROWS_AND_COLUMNS][NUMBER_OF_ROWS_AND_COLUMNS];
    private static String player1XName;
    private static String player2OName;

    public static void main(String[] args) {
        playTicTacToe();
    }

    private static void playTicTacToe() {

        System.out.println("Welcome to TicTacToe!\n");

        printInstructions();

        Scanner in = new Scanner(System.in);
        System.out.print("Please enter player 1's (X) name: ");
        player1XName = in.nextLine();
        System.out.print("Please enter player 2's (O) name: ");
        player2OName = in.nextLine();

        TicTacToeGrid grid = new TicTacToeGrid();

        while (! grid.isGameFinished()) {
            //gameplay loop
        }

        //game over.
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
