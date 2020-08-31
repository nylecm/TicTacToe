import java.util.Scanner;

/**
 * <b>File Name: </b> <p>TicTacToeCommandLine.java</p>
 * <b>Description: </b>
 * <p>
 * Handles the gameplay sequence of the tic tac toe game. Currently supports
 * a classic 3x3 grid, with 2 human players.
 * </p>
 *
 * @author nylecm
 */
public class TicTacToeCommandLine extends TicTacToeGame {
    // Messages:
    private static final String WELCOME_PROMPT = "Welcome to TicTacToe!\n";
    private static final String CROSS_WORD = "cross";
    private static final String NAUGHT_WORD = "naught";
    private static final String CROSS_SYMBOL = "X";
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

    public TicTacToeCommandLine() {
        playTicTacToe();
    }

    /**
     * Contains the main gameplay sequence for the game.
     */
    protected void playTicTacToe() {
        System.out.println(WELCOME_PROMPT);

        printInstructions();

        boolean isGameToRepeat = true;

        while (isGameToRepeat) {
            Scanner in = new Scanner(System.in);

            setUpGame(in); // Player input of game mode, and their name(s).

            // True when it's determined that the game should end.
            boolean isGameFinished = false;

            // Main gameplay loop, terminates when the game ends:
            while (!isGameFinished) {
                Player curPlayer;

                if (grid.getNextPlayer() == 1) {
                    curPlayer = PLAYERS[0];
                    if (curPlayer.getClass() == HumanPlayer.class) {
                        System.out.println(PLAYERS[0].getName() + INPUT_PROMPT_FIRST_ATTEMPT +
                                CROSS_WORD + ": ");
                    } else {
                        System.out.println("Player: " + curPlayer.getName() + " has made their move.");
                    }
                } else {
                    curPlayer = PLAYERS[1];
                    if (curPlayer.getClass() == HumanPlayer.class) {
                        System.out.println(PLAYERS[1].getName() + INPUT_PROMPT_FIRST_ATTEMPT +
                                NAUGHT_WORD + ": ");
                    } else {
                        System.out.println("Player: " + curPlayer.getName() + " has made their move.");
                    }
                }
                isGameFinished = playerMoveInput(curPlayer);
            }
            // Game Finished.

            isGameToRepeat = isGameToRepeat(in); // Checks if user wants to play again.
        }
    }

    /**
     * Takes input from the player before the game is started; allowing them
     * to select game mode (two player or AI mode), as well as enter their name(s).
     *
     * @param in the scanner being used to take user input.
     */
    private void setUpGame(Scanner in) {
        System.out.println("Select game mode: \n");
        System.out.println("Enter 1 for two-player.");
        System.out.println("Enter 2 to play as first player(X) against AI.");
        System.out.println("Enter 3 to play as second player(O) against AI.");
        System.out.println("Enter 4 to see two AI players play against each other!.");

        boolean isValidGameModeEntered = false;

        while (!isValidGameModeEntered) {
            try {
                String gameModeString = in.nextLine();
                int gameMode = Integer.parseInt(gameModeString);

                // Creates players based on the game mode selected:
                if (gameMode == 1) {
                    String player1Name = nameInput(in, PLAYER_1_NAME_PROMPT);
                    PLAYERS[0] = new HumanPlayer(player1Name);

                    String player2Name = nameInput(in, PLAYER_2_NAME_PROMPT);
                    PLAYERS[1] = new HumanPlayer(player2Name);

                    isValidGameModeEntered = true;
                } else if (gameMode == 2) {
                    String player1Name = nameInput(in, PLAYER_1_NAME_PROMPT);
                    PLAYERS[0] = new HumanPlayer(player1Name);

                    PLAYERS[1] = new AITicTacToePlayer("AI Player 2",
                            aiDifficultyInput(in), grid);

                    isValidGameModeEntered = true;
                } else if (gameMode == 3) {
                    PLAYERS[0] = new AITicTacToePlayer("AI Player 1",
                            aiDifficultyInput(in), grid);

                    String player2Name = nameInput(in, PLAYER_2_NAME_PROMPT);
                    PLAYERS[1] = new HumanPlayer(player2Name);

                    isValidGameModeEntered = true;
                } else if (gameMode == 4) {
                    PLAYERS[0] = new AITicTacToePlayer("AI Player 1",
                            aiDifficultyInput(in), grid);

                    PLAYERS[1] = new AITicTacToePlayer("AI Player 2",
                            aiDifficultyInput(in), grid);

                    isValidGameModeEntered = true;
                } else {
                    System.out.println("Game mode needs to be 1, 2, 3, or 4.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Numeric input required. Please enter either " +
                        "1, 2, 3, or 4.");
            }
        }
    }

    /**
     * Handles the input of a name, a name is entered.
     *
     * @param in the scanner being used to take user input.
     * @return the name the user entered.
     */
    private String nameInput(Scanner in, String prompt) {
        boolean isNameEntered = false;
        String name = "";

        while (!isNameEntered) {
            System.out.println(prompt);
            name = in.nextLine();
            isNameEntered = !name.isEmpty();
        }

        return name;
    }

    /**
     * Handles the input of AI difficulty.
     *
     * @param in in the scanner being used to take user input.
     * @return the AI difficulty selected by the user,
     */
    private AIDifficulty aiDifficultyInput(Scanner in) {
        while (true) {
            System.out.println("\nSelect AI difficulty: \n");
            System.out.println("Enter e for an easy AI.");
            System.out.println("Enter m for a medium AI.");
            System.out.println("Enter h for an impossible AI.");

            String difficultyInput = in.nextLine();

            if (difficultyInput.equalsIgnoreCase("e")) {
                return AIDifficulty.EASY;
            } else if (difficultyInput.equalsIgnoreCase("m")) {
                return AIDifficulty.MEDIUM;
            } else if (difficultyInput.equalsIgnoreCase("h")) {
                return AIDifficulty.HARD;
            }
        }
    }

    /**
     * Takes the input from the player at each turn, it could be the player
     * asking for instructions by pressing 'I' or it could be the player wanting
     * to place their mark at a given position.
     *
     * @param curPlayer the current player making a move.
     * @return true if game is finished.
     */
    private boolean playerMoveInput(Player curPlayer) {
        // True when the player marks the board.
        boolean isSymbolPlaced = false;
        // Must be false as the game is still asking a player to mark the board.
        boolean isGameFinished = false;
        String playerInput;

        // Player input loop:
        while (!isSymbolPlaced) {
            playerInput = curPlayer.pickPosition();

            // Interprets player input:
            if (playerInput.equals(INSTRUCTIONS_WORD_FIRST_LETTER)) { // Special input(s):
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
                    isGameFinished = true;
                    winMessage();
                } else if (isSymbolPlaced && grid.isMaxMovesMade()) { // Tie:
                    isGameFinished = true;
                    tieMessage();
                } else if (isSymbolPlaced) { // Game Continues:
                    System.out.println(grid.toString());
                    grid.incrementPlayer();
                }
            }
        }
        return isGameFinished;
    }

    /**
     * Shows a message to congratulate the winning player.
     */
    private void winMessage() {
        System.out.println(grid.toString());
        System.out.println("\nPlayer " + grid.getNextPlayer() + " (" + PLAYERS[grid.getNextPlayer() - 1].getName() + ") wins.");
    }

    /**
     * Notifies the user that the game has been tied.
     */
    private void tieMessage() {
        System.out.println(grid.toString());
        System.out.println("\nThere has been a tie.");
    }

    /**
     * Takes input from the user to determine whether they want to play the game
     * again.
     *
     * @param in the scanner being used to take user input.
     * @return true if the user wants to play again.
     */
    private boolean isGameToRepeat(Scanner in) {
        boolean isGameToRepeat = false;
        boolean isValidInput = false;
        System.out.println("Do you want to play again (Type Y/n)?");

        // Allows user to input whether they want to play again.
        // Terminates when the user enters y/n.
        while (!isValidInput) {
            String continueYesNo = in.nextLine();
            if (continueYesNo.equalsIgnoreCase("n")) {
                isValidInput = true;
            } else if (continueYesNo.equalsIgnoreCase("Y")) {
                isGameToRepeat = true;
                isValidInput = true;
            } else {
                System.out.println("Please enter Y or n, to either " +
                        "continue or stop playing.");
            }
        }
        return isGameToRepeat;
    }

    /**
     * Prints the game instructions on the screen.
     */
    private void printInstructions() {
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
