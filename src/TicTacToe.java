import java.util.Scanner;

/**
 * @author nylecm
 */
public class TicTacToe {
    private static final String[] playerNames = new String[2];

    public static void main(String[] args) {
        playTicTacToe();
    }

    private static void playTicTacToe() {

        System.out.println("Welcome to TicTacToe!\n");

        printInstructions();

        Scanner in = new Scanner(System.in);
        System.out.print("Please enter player 1's (X) name: ");
        playerNames[0] = in.nextLine();
        System.out.print("Please enter player 2's (O) name: ");
        playerNames[1] = in.nextLine();

        TicTacToeGrid grid = new TicTacToeGrid();

        while (!grid.isGameFinished()) {
            if (grid.getNextPlayer() == 1) {
                System.out.print(playerNames[0] + "'s go enter position number " +
                        "to place your cross:");
            } else {
                System.out.print(playerNames[1] + "'s go enter position number " +
                        "to place your nought:");
            }

            String playerInput;
            boolean isSymbolPlaced = false;

            //Main gameplay loop
            while (!isSymbolPlaced) {
                playerInput = in.nextLine();

                if (playerInput.equals("I")) {
                    printInstructions();
                } else {
                    try {
                        grid.markGrid(Integer.parseInt(playerInput));
                        isSymbolPlaced = true;
                    } catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }

        //game over.
    }

    /**
     * Prints the game instructions on the screen.
     */
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
                "any time.");
    }
}
