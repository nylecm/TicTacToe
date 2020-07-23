import java.util.Scanner;

/**
 * The type Tic tac toe, which handles the gameplay sequence of the game.
 * <p>
 * Currently the game supports two human players.
 *
 * @author nylecm
 */
public class TicTacToe {
    // Game Configuration:
    private static final int NUM_OF_PAYERS = 2;
    private static final String[] PLAYER_NAMES = new String[NUM_OF_PAYERS];

    // Messages:
    private static final String WELCOME_PROMPT = "Welcome to TicTacToe!\n";
    private static final String CROSS_WORD = "cross";
    private static final String CROSS_SYMBOL = "X";
    private static final String NAUGHT_WORD = "naught";
    private static final String NAUGHT_SYMBOL = "O";
    private static final String PLAYER_1_NAME_PROMPT =
            "Please enter player 1's (" + CROSS_SYMBOL + ") name: ";
    private static final String PLAYER_2_NAME_PROMPT =
            "Please enter player 2's (" + NAUGHT_SYMBOL + ") name: ";
    private static final String INPUT_PROMPT_FIRST_ATTEMPT =
            "'s go enter position number to place your ";
    private static final String INSTRUCTIONS_WORD_FIRST_LETTER = "I";
    private static final String INPUT_PROMPT = "Input position number, or press" +
            " I for instructions.";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        playTicTacToe();
    }

    /**
     * Contains the main gameplay sequence for the game.
     */
    private static void playTicTacToe() {
        System.out.println(WELCOME_PROMPT);

        printInstructions();

        // Players name input:
        Scanner in = new Scanner(System.in);
        System.out.print(PLAYER_1_NAME_PROMPT);
        PLAYER_NAMES[0] = in.nextLine();
        System.out.print(PLAYER_2_NAME_PROMPT);
        PLAYER_NAMES[1] = in.nextLine();

        TicTacToeGrid grid = new TicTacToeGrid();

        // True when it's determined that the game should end.
        boolean isGameFinished = false;

        // Main gameplay loop:
        while (!isGameFinished) {
            if (grid.getNextPlayer() == 1) {
                System.out.print(PLAYER_NAMES[0] + INPUT_PROMPT_FIRST_ATTEMPT +
                        CROSS_WORD + ": ");
            } else {
                System.out.print(PLAYER_NAMES[1] + INPUT_PROMPT_FIRST_ATTEMPT +
                        NAUGHT_WORD + ": ");
            }

            String playerInput;

            boolean isSymbolPlaced = false; // True when the player marks the board.

            // Player input loop:
            while (!isSymbolPlaced) {
                playerInput = in.nextLine();

                // Interprets player input:
                if (playerInput.equals(INSTRUCTIONS_WORD_FIRST_LETTER)) {
                    printInstructions();
                    System.out.println(INPUT_PROMPT);
                } else { // All inputs except above indicate the user wants to mark the grid.
                    try {
                        grid.markGrid(Integer.parseInt(playerInput));
                        // Successfully entered a grid position:
                        isSymbolPlaced = true;
                    } catch (NumberFormatException ex) { // Number Parsed Incorrectly.
                        System.out.println("Please enter an integer or a valid " +
                                "character such as " + INSTRUCTIONS_WORD_FIRST_LETTER + ".");
                        System.out.println(INPUT_PROMPT);
                    } catch (IndexOutOfBoundsException | IllegalArgumentException ex) {
                        // Either grid position number out-of-range, or position
                        // already occupied.
                        System.out.println(ex.getMessage());
                        System.out.println(INPUT_PROMPT);
                    }

                    if (isSymbolPlaced && grid.isGameFinished()) {
                        isGameFinished = true;
                    } else { // Game continues.
                        System.out.println(grid.toString());
                        grid.incrementPlayer();
                    }
                }
            }
        }
        //TODO Improve game finished message.
        System.out.println("Player " + grid.getNextPlayer() + " wins.");
        // Game Finished.
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

        //Prints game board with position numbers:
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
