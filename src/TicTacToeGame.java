public abstract class TicTacToeGame {
    // Game Configuration:
    protected static final int NUM_OF_PAYERS = 2;
    protected static final Player[] PLAYERS = new Player[NUM_OF_PAYERS];

    // The game grid:
    protected final TicTacToeGrid grid = new TicTacToeGrid();

    protected abstract void playTicTacToe();
}
