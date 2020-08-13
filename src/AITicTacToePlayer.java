import java.util.concurrent.ThreadLocalRandom;

/**
 * <b>File Name: </b> <p>AITicTacToePlayer.java</p>
 * <b>Description: </b>
 * <p>
 * Represents an AI 3x3 grid tic tac toe player on a certain grid. Has parameter
 * that allows for the difficulty of the AI to be configured.
 * </p>
 *
 * @author nylecm
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
        this.grid = grid;
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
     * Generates the next move for an AI player; based on the difficulty selected,
     * and whether the AI player is playing second or first.
     *
     * @return the position that has been picked.
     */
    @Override
    public String pickPosition() {
        int random = ThreadLocalRandom.current().nextInt(1, 11);
        switch (difficulty) {
            case EASY: // Algorithmic move picked 5/10 times.
                if (random <= 5) {
                    if (isPlayingFirst()) { // This AI player is playing first.
                        return pickPositionAsFirst().toString();
                    } else { // This AI player is playing second.
                        return pickPositionAsSecond().toString();
                    }
                } else {
                    return makeRandomMove().toString();
                }
            case MEDIUM: // Algorithmic move picked 7/10 times.
                if (random <= 7) {
                    if (isPlayingFirst()) { // This AI player is playing first.
                        return pickPositionAsFirst().toString();
                    } else { // This AI player is playing second.
                        return pickPositionAsSecond().toString();
                    }
                } else {
                    return makeRandomMove().toString();
                }
            case HARD: // Algorithmic move always picked.
                if (isPlayingFirst()) { // This AI player is playing first.
                    return pickPositionAsFirst().toString();
                } else { // This AI player is playing second.
                    return pickPositionAsSecond().toString();
                }
        }
        return makeRandomMove().toString();
    }

    /**
     * Makes the move of a perfect first player in a game of tic tac toe, which
     * guarantees a win with every opponent but the perfect second player,
     * where it guarantees a tie.
     *
     * @return the move of the ideal first tic tac toe player.
     */
    private Integer pickPositionAsFirst() {
        if (grid.getNumberOfMarks() == 0) { // On player's first move:
            return makeRandomMove();
        } else { // On player's third or later move:
            // Track the grid number of blocking/ forking/ opp. corner move.
            int blockMoveFoundAt = 0;
            int forkMoveFoundAt = 0;

            // For every row:
            for (int i = 0; i < 3; i++) {
                //Counts the number of cells in a row with a certain status:
                GridStatus[] row = grid.getRow(i);

                // Array of 3 int values that holds the number of Xs, Os, and
                // unclaimed squares respectively in a line.
                int[] lineStatus = getLineStatus(row);

                if (lineStatus[0] == 2 & lineStatus[2] == 1) { // 2 Xs & 1 unclaimed in a line:
                    for (int j = 0; j < 3; j++) {
                        if (row[j] == GridStatus.UNCLAIMED) {
                            return (i * 3) + 1 + j; // Makes winning move.
                        }
                    }
                } else if (lineStatus[1] == 2 && lineStatus[2] == 1 && blockMoveFoundAt < 1) {
                    for (int j = 0; j < 3; j++) {
                        if (row[j] == GridStatus.UNCLAIMED) {
                            blockMoveFoundAt = (i * 3) + 1 + j; // Tracks blocking move.
                        }
                    }
                } else if (i == 0 || i == 2 && (blockMoveFoundAt < 1 || forkMoveFoundAt < 1) && (lineStatus[2] > 0)) {

                    if ((row[0] == (GridStatus.X_CLAIMED)) && (row[1] == GridStatus.UNCLAIMED) && (row[2] == GridStatus.UNCLAIMED)) {
                        forkMoveFoundAt = (i * 3) + 3; //End of the row.
                    } else if ((row[0] == (GridStatus.UNCLAIMED) && (row[1] == GridStatus.UNCLAIMED) && (row[2] == GridStatus.X_CLAIMED))) {
                        forkMoveFoundAt = (i * 3) + 1; //Beginning of the row.
                    }
                }
            }
            // For every column:
            for (int i = 0; i < 3; i++) {
                GridStatus[] column = grid.getColumn(i);

                // Array of 3 int values that holds the number of Xs, Os, and
                // unclaimed squares respectively in a line.
                int[] lineStatus = getLineStatus(column);

                if (lineStatus[0] == 2 & lineStatus[2] == 1) { // 2 Xs & 1 unclaimed in a line:
                    for (int j = 0; j < 3; j++) {
                        if (column[j] == GridStatus.UNCLAIMED) {
                            return (j * 3) + 1 + i; // Makes winning move.
                        }
                    }
                } else if (lineStatus[1] == 2 && lineStatus[2] == 1 && blockMoveFoundAt < 1) {
                    for (int j = 0; j < 3; j++) {
                        if (column[j] == GridStatus.UNCLAIMED) {
                            blockMoveFoundAt = (j * 3) + 1 + i; // Tracks blocking move.
                        }
                    }
                } else if (i == 0 || i == 2 && (blockMoveFoundAt < 1 || forkMoveFoundAt < 1) && (lineStatus[2] > 0)) {
                    if ((column[0] == (GridStatus.X_CLAIMED)) && (column[1] == GridStatus.UNCLAIMED) && (column[2] == GridStatus.UNCLAIMED)) {
                        forkMoveFoundAt = i + 7; // End of the column.
                    } else if ((column[0] == (GridStatus.UNCLAIMED) && (column[1] == GridStatus.UNCLAIMED) && column[2] == GridStatus.X_CLAIMED)) {
                        forkMoveFoundAt = i + 1; // Beginning of the column.
                    }
                }
            }
            // Diagonal Positive Gradiant (3, 5, 7):
            GridStatus[] diagonalPositive = {grid.getMarkAt(3), grid.getMarkAt(5), grid.getMarkAt(7)};
            int[] lineStatus = getLineStatus(diagonalPositive);

            if (lineStatus[0] == 2 & lineStatus[2] == 1) {
                for (int i = 0; i < 3; i++) {
                    if (diagonalPositive[i] == GridStatus.UNCLAIMED) {
                        return (i * 2) + 3; // Makes winning move.
                    }
                }
            } else if (lineStatus[1] == 2 && lineStatus[2] == 1 && blockMoveFoundAt < 1) {
                for (int i = 0; i < 3; i++) {
                    if (diagonalPositive[i] == GridStatus.UNCLAIMED) {
                        blockMoveFoundAt = (i * 2) + 3; // Tracks blocking move.
                    }
                }
            }
            // Diagonal Negative Gradiant (1, 5, 9:
            GridStatus[] diagonalNegative = {grid.getMarkAt(1), grid.getMarkAt(5), grid.getMarkAt(9)};
            lineStatus = getLineStatus(diagonalNegative);

            if (lineStatus[0] == 2 & lineStatus[2] == 1) {
                for (int i = 0; i < 3; i++) {
                    if (diagonalNegative[i] == GridStatus.UNCLAIMED) {
                        return (i * 4) + 1; // Makes winning move.
                    }
                }
            } else if (lineStatus[1] == 2 && lineStatus[2] == 1 && blockMoveFoundAt < 1) {
                for (int i = 0; i < 3; i++) {
                    if (diagonalNegative[i] == GridStatus.UNCLAIMED) {
                        blockMoveFoundAt = (i * 4) + 1; // Tracks blocking move.
                    }
                }
            }
            // Makes appropriate moves if win not found:
            if (blockMoveFoundAt > 0) {
                return blockMoveFoundAt;
            }
            if (forkMoveFoundAt > 0) {
                return forkMoveFoundAt;
            }

            // Checks for opposite corner move, otherwise make centre move,
            // then make empty corner move, then make empty side move.
            return makeOppositeCornerCentreCornerOrSideMove();
        }
    }

    /**
     * Placeholder method, which will in the future select the next move of
     * the perfect second player.
     *
     * @return a random number between 1 and 9.
     */
    private Integer pickPositionAsSecond() { //todo
        if (grid.getNumberOfMarks() == 1) { // On player's first move:
            if (grid.getMarkAt(5) == GridStatus.X_CLAIMED) { // X first takes centre.
                return makeCornerMove();
            } else if (grid.getMarkAt(1) == GridStatus.X_CLAIMED || // X first takes corner.
                    grid.getMarkAt(3) == GridStatus.X_CLAIMED ||
                    grid.getMarkAt(7) == GridStatus.X_CLAIMED ||
                    grid.getMarkAt(9) == GridStatus.X_CLAIMED) {
                return 5; //Centre move.
            } else { // X first takes side.
                return makeSideMove(); // or return 5; // todo make centre move too.
            }
        } else {
            // For every row:
            for (int i = 0; i < 3; i++) {
                //Counts the number of cells in a row with a certain status:
                GridStatus[] row = grid.getRow(i);

                // Array of 3 int values that holds the number of Xs, Os, and
                // unclaimed squares respectively in a line.
                int[] lineStatus = getLineStatus(row);

                // 2Xs & 1 unclaimed we can block X:
                if (lineStatus[0] == 2 && lineStatus[2] == 1) {
                    for (int j = 0; j < 3; j++) {
                        if (row[j] == GridStatus.UNCLAIMED) {
                            return (i * 3) + 1 + j; // Makes the blocking move.
                        }
                    }
                }
            }
            // For every column:
            for (int i = 0; i < 3; i++) {
                GridStatus[] column = grid.getColumn(i);

                // Array of 3 int values that holds the number of Xs, Os, and
                // unclaimed squares respectively in a line.
                int[] lineStatus = getLineStatus(column);

                // 2Xs & 1 unclaimed we can block X:
                if (lineStatus[0] == 2 && lineStatus[2] == 1) {
                    for (int j = 0; j < 3; j++) {
                        if (column[j] == GridStatus.UNCLAIMED) {
                            return (j * 3) + 1 + i; // Makes the blocking move.
                        }
                    }
                }
            }

            // Diagonal Positive Gradiant (3, 5, 7):
            GridStatus[] diagonalPositive = {grid.getMarkAt(3), grid.getMarkAt(5), grid.getMarkAt(7)};
            int[] lineStatus = getLineStatus(diagonalPositive);

            if (lineStatus[0] == 2 && lineStatus[2] == 1) {
                for (int i = 0; i < 3; i++) {
                    if (diagonalPositive[i] == GridStatus.UNCLAIMED) {
                        return (i * 2) + 3; // Tracks blocking move.
                    }
                }
            }
            // Diagonal Negative Gradiant (1, 5, 9):
            GridStatus[] diagonalNegative = {grid.getMarkAt(1), grid.getMarkAt(5), grid.getMarkAt(9)};
            lineStatus = getLineStatus(diagonalNegative);

            if (lineStatus[0] == 2 && lineStatus[2] == 1) {
                for (int i = 0; i < 3; i++) {
                    if (diagonalNegative[i] == GridStatus.UNCLAIMED) {
                        return (i * 4) + 1; // Tracks blocking move.
                    }
                }
            }

            // Checks for opposite corner move, otherwise make centre move,
            // then make empty corner move, then make empty side move.
            return makeOppositeCornerCentreCornerOrSideMove();
        }
    }

    private Integer makeSideMove() {
        // Makes empty side move
        int[] sidePositions = {2, 4, 6, 8};
        return makeMoveOutOfArray(sidePositions);
    }

    private Integer makeCornerMove() {
        // Makes empty corner move
        int[] cornerPositions = {1, 3, 7, 9};
        return makeMoveOutOfArray(cornerPositions);
    }

    private Integer makeMoveOutOfArray(int[] positions) {
        for (int cornerPosition : positions) {
            if (grid.getMarkAt(cornerPosition) == GridStatus.UNCLAIMED) {
                return cornerPosition;
            }
        }
        return 0;
    }

    private Integer makeOppositeCornerCentreCornerOrSideMove() {
        // Checks for opposite corner moves.
        int oppositeCornerMoveAt = makeOppositeCornerMove();

        if (oppositeCornerMoveAt > 0) {
            return oppositeCornerMoveAt;
        }

        // Makes centre move:
        if (grid.getMarkAt(5) == GridStatus.UNCLAIMED) {
            return 5;
        }

        // Makes empty corner
        int cornerMoveAt = makeCornerMove();

        if (cornerMoveAt > 0) {
            return cornerMoveAt;
        }

        // Makes empty side move.
        int sideMoveAt = makeCornerMove();

        if (sideMoveAt > 0) {
            return cornerMoveAt;
        }
        return makeRandomMove();
    }

    /**
     * Makes a random move.
     *
     * @return a random number between 1 and 9.
     */
    private Integer makeRandomMove() {
        while (true) {
            int randomPosition = ThreadLocalRandom.current().nextInt(1, 10);
            if (grid.getMarkAt(randomPosition) == GridStatus.UNCLAIMED) {
                return randomPosition;
            }
        }
    }

    /**
     * Returns the statuses of a line of tic tac toe grid squares.
     *
     * @param line a line of tic tac toe grid squares.
     * @return an array of 3 int values that holds the number of Xs, Os, and
     * unclaimed squares respectively in a line. <i>Eg [2,0,1] for a line with
     * 2 Xs and one unclaimed square.</i>
     */
    private int[] getLineStatus(GridStatus[] line) {
        // Array of 3 int values that holds the number of Xs, Os, and unclaimed
        // squares respectively in a line.
        int[] lineStatus = {0, 0, 0};

        for (int i = 0; i < 3; i++) {
            if (line[i] == GridStatus.X_CLAIMED) {
                lineStatus[0]++;
            } else if (line[i] == GridStatus.O_CLAIMED) {
                lineStatus[1]++;
            } else {
                lineStatus[2]++;
            }
        }
        return lineStatus;
    }

    private Integer makeOppositeCornerMove() {
        GridStatus otherPlayersMark = isPlayingFirst() ? GridStatus.O_CLAIMED : GridStatus.X_CLAIMED;

        if (grid.getMarkAt(1) == otherPlayersMark && grid.getMarkAt(9) == GridStatus.UNCLAIMED) {
            return 9;
        } else if (grid.getMarkAt(3) == otherPlayersMark && grid.getMarkAt(7) == GridStatus.UNCLAIMED) {
            return 7;
        } else if (grid.getMarkAt(7) == otherPlayersMark && grid.getMarkAt(3) == GridStatus.UNCLAIMED) {
            return 3;
        } else if (grid.getMarkAt(9) == otherPlayersMark && grid.getMarkAt(1) == GridStatus.UNCLAIMED) {
            return 1;
        }

        return 0;
    }

    private boolean isPlayingFirst() {
        return grid.getNumberOfMarks() % 2 == 0;
    }
}
