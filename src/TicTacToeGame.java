/**
 * <b>File Name: </b> <p>TicTacToeGame.java</p>
 * <b>Description: </b>
 * <p>
 * The abstract super class of all tic tac toe game classes, contains common
 * fields, and methods.
 * </p>
 *
 * @author nylecm
 */
public abstract class TicTacToeGame {

    // Messages:
    protected static final String CROSS_SYMBOL = GridStatus.X_CLAIMED.toString();
    protected static final String NAUGHT_SYMBOL = GridStatus.O_CLAIMED.toString();

    // Game Configuration:
    protected static final int NUM_OF_PAYERS = 2;
    protected final Player[] players = new Player[NUM_OF_PAYERS];

    // The game grid:
    protected final TicTacToeGrid grid = new TicTacToeGrid();

    /**
     * Method that initiates a tic tac toe game.
     */
    protected abstract void playTicTacToe();

    /**
     * Gets the grid the game is being played on.
     *
     * @return the grid the game is being played on.
     */
    public TicTacToeGrid getGrid() {
        return grid;
    }

    /**
     * Validates AI difficulty input.
     *
     * @param input the string Ai difficulty input from the player.
     * @return the AI difficulty that corresponds to the difficulty entered by
     * the player.
     * @throws IllegalArgumentException when an invalid difficulty is entered.
     */
    protected AIDifficulty validateAiDifficultyInput(String input) throws IllegalArgumentException {
        if (input.equalsIgnoreCase("e")) {
            return AIDifficulty.EASY;
        } else if (input.equalsIgnoreCase("m")) {
            return AIDifficulty.MEDIUM;
        } else if (input.equalsIgnoreCase("h")) {
            return AIDifficulty.HARD;
        } else {
            throw new IllegalArgumentException("Invalid AI difficulty entered (" + input + ")!");
        }
    }
}
