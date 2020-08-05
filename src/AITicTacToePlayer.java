/**
 * The type Ai player.
 */
public class AITicTacToePlayer extends Player {

    private final AIDifficulty difficulty;
    private TicTacToeGrid grid;

    /**
     * Instantiates a new Ai player.
     *
     * @param name       the name of the player.
     * @param difficulty the difficulty level of the AI player.
     * @param grid       the tic tac toe grid the player is playing on.
     */
    public AITicTacToePlayer(String name, AIDifficulty difficulty, TicTacToeGrid grid) {
        super(name);
        this.difficulty = difficulty;
    }

    /**
     * Gets difficulty.
     *
     * @return the difficulty of the AI opponent.
     */
    public AIDifficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Gets grid.
     *
     * @return the grid the player is using to play the game.
     */
    public TicTacToeGrid getGrid() {
        return grid;
    }

    /**
     * Sets grid.
     *
     * @param grid the grid the player is using to play the game.
     */
    public void setGrid(TicTacToeGrid grid) {
        this.grid = grid;
    }

    /**
     * Generates the next move for an AI player.
     *
     * @return the position that has been picked.
     */
    @Override
    public String pickPosition() {
        //TODO write position picker algorithm.
        if (grid.getNumberOfMarks() % 2 == 1) { // This AI player is playing first.
            if (grid.getNumberOfMarks() == 0) { // Centre - great first move
                return "5";
            } else if (grid.getNumberOfMarks() == 2) {
                // 8 possible moves from second player.
            } else {
                // block, or win.
                // forking strategy.
            }
        } else { // This AI player is playing second.

        }


        /*switch (difficulty) { How difficulty could be implemented:
            case EASY:
                // Random 60% of the time
                break;
            case MEDIUM:
                break; // " 30 "
            case HARD:
                break; // " 0 "
        }
        */

        // Placeholder:
        return ((Long) Math.round((Math.random() * 9))).toString();
    }
}
