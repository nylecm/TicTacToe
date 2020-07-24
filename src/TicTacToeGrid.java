/**
 * The type Tic tac toe grid.
 *
 * @author nylecm
 */
public class TicTacToeGrid {

    // Configures size of a grid:
    private final int numberOfRowsAndColumns = 3;
    private final int numberOfGridPositions =
            numberOfRowsAndColumns * numberOfRowsAndColumns;

    // Represents a tic-tac-toe grid:
    private final GRID_STATUS[][] grid =
            new GRID_STATUS[numberOfRowsAndColumns][numberOfRowsAndColumns];

    private int nextPlayer = 1;
    private int numberOfMarks = 0;

    /**
     * Instantiates a new Tic tac toe grid.
     */
    public TicTacToeGrid() {
        unclaimAllPositions();
    }

    /**
     * Mark grid.
     *
     * @param gridPosition the grid position
     * @throws IndexOutOfBoundsException the index out of bounds exception
     * @throws IllegalArgumentException  the illegal argument exception
     */
    public void markGrid(int gridPosition) throws IndexOutOfBoundsException, IllegalArgumentException {
        int row = convertPositionNumberToRowNumber(gridPosition);
        int column = convertPositionNumberToColumnNumber(gridPosition);

        if (!isGridPositionFree(row, column)) {
            throw new IllegalArgumentException("Grid Position already occupied.");
        }

        GRID_STATUS markToBeMade;
        if (getNextPlayer() == 1) {
            markToBeMade = GRID_STATUS.X_CLAIMED;
        } else {
            markToBeMade = GRID_STATUS.O_CLAIMED;
        }
        grid[row][column] = markToBeMade;
        numberOfMarks++;
    }

    private int convertPositionNumberToRowNumber(int gridPosition) throws IndexOutOfBoundsException {
        if (gridPosition < 1 || gridPosition > numberOfGridPositions) {
            throw new IndexOutOfBoundsException("Grid Position cannot be less " +
                    "than 1 or more than " + numberOfGridPositions + ".");
        } else if (gridPosition <= 3) {
            return 0;
        } else if (gridPosition <= 6) {
            return 1;
        } else {
            return 2;
        }
    }

    private int convertPositionNumberToColumnNumber(int gridPosition) throws IllegalArgumentException {
        if (gridPosition < 1 || gridPosition > numberOfGridPositions) {
            throw new IndexOutOfBoundsException("Grid Position cannot be less " +
                    "than 1 or more than " + numberOfGridPositions + ".");
        } else if (gridPosition % 3 == 1) {
            return 0;
        } else if (gridPosition % 3 == 2) {
            return 1;
        } else {
            return 2;
        }
    }

    private boolean isGridPositionFree(int gridRow, int gridColumn) {
        return grid[gridRow][gridColumn] == GRID_STATUS.UNCLAIMED;
    }

    /**
     * Gets next player.
     *
     * @return the next player
     */
    public int getNextPlayer() {
        return nextPlayer;
    }

    /**
     * Increment player.
     */
    public void incrementPlayer() {
        if (nextPlayer == 1) {
            nextPlayer++;
        } else {
            nextPlayer = 1;
        }
    }

    public boolean isWin() {
        // Win when a horizontal row is complete.
        for (GRID_STATUS[] gridRow : grid) {
            if (isEveryMarkInGridLineSame(gridRow)) {
                return true;
            }
        }

        //Win when a vertical column is complete.
        for (int i = 0; i < numberOfRowsAndColumns; i++) { //For top item in column:
            GRID_STATUS[] gridColumn = new GRID_STATUS[numberOfRowsAndColumns];
            gridColumn[0] = grid[0][i];
            //loop down 2 more times and add to array.
            for (int j = 1; j < numberOfRowsAndColumns; j++) {
                gridColumn[j] = grid[j][i];
            }
            if (isEveryMarkInGridLineSame(gridColumn)) {
                return true;
            }
        }

        //Diagonal wins:
        GRID_STATUS[] diagonalLineDown = new GRID_STATUS[3];
        diagonalLineDown[0] = grid[0][0];
        diagonalLineDown[1] = grid[1][1];
        diagonalLineDown[2] = grid[2][2];


        if (isEveryMarkInGridLineSame(diagonalLineDown)) {
            return true;
        }

        GRID_STATUS[] diagonalLineUp = new GRID_STATUS[3];
        diagonalLineUp[0] = grid[2][2];
        diagonalLineUp[1] = grid[1][1];
        diagonalLineUp[2] = grid[0][0];


        if (isEveryMarkInGridLineSame(diagonalLineUp)) {
            return true;
        }

        return false;
    }

    public boolean isMaxMovesMade() {
        return numberOfMarks >= 9;
    }

    private boolean isEveryMarkInGridLineSame(GRID_STATUS[] gridLine) {
        GRID_STATUS lastGridPosition = gridLine[0];
        for (int i = 1; i < numberOfRowsAndColumns; i++) {
            if (gridLine[i] != lastGridPosition || gridLine[i] == GRID_STATUS.UNCLAIMED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Unset all positions.
     */
    public void unclaimAllPositions() {
        for (int i = 0; i < numberOfRowsAndColumns; i++) {
            for (int j = 0; j < numberOfRowsAndColumns; j++) {
                grid[i][j] = GRID_STATUS.UNCLAIMED;
            }
        }
    }

    @Override
    public String toString() {
        String l1 = "\t" + grid[0][0].toString() + "\t|\t" + grid[0][1].toString() + "\t|\t" + grid[0][2].toString() + "\t\n";
        String l2 = "  ------|-------|------\n";
        String l3 = "\t" + grid[1][0].toString() + "\t|\t" + grid[1][1].toString() + "\t|\t" + grid[1][2].toString() + "\t\n";
        String l4 = "  ------|-------|------\n";
        String l5 = "\t" + grid[2][0].toString() + "\t|\t" + grid[2][1].toString() + "\t|\t" + grid[2][2].toString() + "\t";
        return l1 + l2 + l3 + l4 + l5;
    }
}
