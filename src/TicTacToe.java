import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <b>File Name: </b> <p>TicTacToe.java</p>
 * <b>Description: </b>
 * <p>
 * Handles the gameplay sequence of the tic tac toe game. Currently supports
 * a classic 3x3 grid, with 2 human players.
 * </p>
 *
 * @author nylecm
 */
public class TicTacToe {
    // Game Configuration:
    private static final int NUM_OF_PAYERS = 2;
    private static final Player[] PLAYERS = new Player[NUM_OF_PAYERS];

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

        boolean isGameToRepeat = true;

        while (isGameToRepeat) {
            Scanner in = new Scanner(System.in);

            setUpGame(in);

            // Players name input:
            String[] playerNames = new String[NUM_OF_PAYERS];

            for (int i = 0; i < NUM_OF_PAYERS; i++) {
                System.out.print(PLAYER_1_NAME_PROMPT);
                PLAYERS[i] = new Plain.nextLine();


            }

            System.out.print(PLAYER_2_NAME_PROMPT);
            PLAYER_NAMES[1] = in.nextLine();

            TicTacToeGrid grid = new TicTacToeGrid();

            // True when it's determined that the game should end.
            boolean isGameFinished = false;

            // Main gameplay loop, terminates when the game ends:
            while (!isGameFinished) {
                if (grid.getNextPlayer() == 1) {
                    System.out.print(PLAYER_NAMES[0] + INPUT_PROMPT_FIRST_ATTEMPT +
                            CROSS_WORD + ": ");
                } else {
                    System.out.print(PLAYER_NAMES[1] + INPUT_PROMPT_FIRST_ATTEMPT +
                            NAUGHT_WORD + ": ");
                }
                isGameFinished = playerMoveInput(in, grid);
            }
            // Game Finished.

            boolean isValidInput = false;
            System.out.println("Do you want to play again (Type Y/n)?");

            // Allows user to input whether they want to play again.
            // Terminates when the user enters y/n.
            while (!isValidInput) {
                String continueYesNo = in.nextLine();
                if (continueYesNo.equalsIgnoreCase("n")) {
                    isGameToRepeat = false;
                    isValidInput = true;
                } else if (continueYesNo.equalsIgnoreCase("Y")) {
                    isValidInput = true;
                } else {
                    System.out.println("Please enter Y or n, to either " +
                            "continue or stop playing.");
                }
            }
        }
    }

    private static void setUpGame(Scanner in) throws IllegalArgumentException {
        System.out.println("Select game mode: \n");
        System.out.println("Enter 1 for two-player.");
        System.out.println("Enter 2 to play as first player(X) against AI.");
        System.out.println("Enter 3 to play as second player(O) against AI.");

        boolean isValidGameModeEntered = false;

        while (!isValidGameModeEntered) {
            try {
                int gameMode = in.nextInt();

                if (gameMode == 1) {
                    //Player name input.
                    PLAYERS[0] = new HumanPlayer(in.nextLine());
                    PLAYERS[1] = new HumanPlayer(in.nextLine());
                    isValidGameModeEntered = true;
                } else if (gameMode == 2) {
                    //Player name input.

                    PLAYERS[0] = new HumanPlayer(in.nextLine());
                    PLAYERS[1] = new AIPlayer("AI Player 2", AIDifficulty.MEDIUM);
                    isValidGameModeEntered = true;
                } else if (gameMode == 3) {
                    PLAYERS[0] = new AIPlayer("AI Player 2", AIDifficulty.MEDIUM);
                    PLAYERS[1] = new HumanPlayer(in.nextLine())
                    isValidGameModeEntered = true;
                } else {
                    throw new IllegalArgumentException("Game mode needs to be 1, 2, or 3.");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Numeric input required. Please enter either 1, 2, or 3.");
            }
        }
    }

    /**
     * Takes the input from the player at each turn, it could be the player
     * asking for instructions by pressing 'I' or it could be the player wanting
     * to place their mark at a given position.
     *
     * @param in   the scanner used to read from console.
     * @param grid the tic tac toe grid object in use.
     * @return true if game is finished.
     */
    private static boolean playerMoveInput(Scanner in, TicTacToeGrid grid) {
        // True when the player marks the board.
        boolean isSymbolPlaced = false;
        // Must be false as the game is still asking a player to mark the board.
        boolean isGameFinished = false;
        String playerInput;

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

                if (isSymbolPlaced && grid.isWin()) { // Game won:
                    System.out.println(grid.toString());
                    isGameFinished = true;
                    winMessage(grid);
                } else if (isSymbolPlaced && grid.isMaxMovesMade()) { // Tie:
                    System.out.println(grid.toString());
                    isGameFinished = true;
                    tieMessage();
                } else { // Game Continues:
                    System.out.println(grid.toString());
                    grid.incrementPlayer();
                }
            }
        }
        return isGameFinished;
    }

    /**
     * Shows a message to congratulate the winning player.
     *
     * @param grid the grid from which the number of the winning player is fetched.
     */
    private static void winMessage(TicTacToeGrid grid) {
        System.out.println("Player " + grid.getNextPlayer() + " (" + PLAYER_NAMES[grid.getNextPlayer() - 1] + ") wins.");
    }

    /**
     * Notifies the user that the game has been tied.
     */
    private static void tieMessage() {
        System.out.println("There has been a tie.");
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
