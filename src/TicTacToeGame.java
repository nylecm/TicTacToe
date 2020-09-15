public abstract class TicTacToeGame {
    // Messages:
    protected static final String CROSS_SYMBOL = GridStatus.X_CLAIMED.toString();
    protected static final String NAUGHT_SYMBOL = GridStatus.O_CLAIMED.toString();

    // Game Configuration:
    protected static final int NUM_OF_PAYERS = 2;
    protected final Player[] players = new Player[NUM_OF_PAYERS];

    // The game grid:
    protected final TicTacToeGrid grid = new TicTacToeGrid();

    protected abstract void playTicTacToe();

    public TicTacToeGrid getGrid() {
        return grid;
    }

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
