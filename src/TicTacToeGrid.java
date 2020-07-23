/**
 * The type Tic tac toe grid.
 *
 * @author nylecm
 */
public class TicTacToeGrid {
    /**
     * The Number of rows and columns.
     */
    public final int numberOfRowsAndColumns = 3;
    /**
     * The Number of grid positions.
     */
    public final int numberOfGridPositions = numberOfRowsAndColumns *
            numberOfRowsAndColumns;

    private GRID_STATUS[][] gameBoard = new GRID_STATUS[numberOfRowsAndColumns][numberOfRowsAndColumns];
    private int nextPlayer = 1;
    private int numberOfMarks = 0;

    /**
     * Instantiates a new Tic tac toe grid.
     */
    public TicTacToeGrid() {
        unsetAllPositions();
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
        gameBoard[row][column] = markToBeMade;
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
        return gameBoard[gridRow][gridColumn] == GRID_STATUS.UNCLAIMED;
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

    /**
     * Is game finished boolean.
     *
     * @return the boolean
     */
//FIXME
    public boolean isGameFinished() {
        return isWin() || numberOfMarks >= numberOfGridPositions; //TODO add tie condition
    }

    private boolean isWin() {
        // True When 3 in a row.
        for (GRID_STATUS[] gridRow : gameBoard) {
            if (isEveryMarkInGridLineSame(gridRow)) {
                return true;
            }
        }

        for (int i = 0; i < numberOfRowsAndColumns; i++) { //For top item in column:
            GRID_STATUS[] gridColumn = new GRID_STATUS[numberOfRowsAndColumns];
            gridColumn[0] = gameBoard[0][i];
            //loop down 2 more times and add to array.
            for (int j = 1; j < numberOfRowsAndColumns; j++) {
                gridColumn[j] = gameBoard[j][i];
            }
            if (isEveryMarkInGridLineSame(gridColumn)) {
                return true;
            }
        }

        //Diagonal win:
        GRID_STATUS[] diagonalLineDown = new GRID_STATUS[3];
        diagonalLineDown[0] = gameBoard[0][0];
        diagonalLineDown[1] = gameBoard[1][1];
        diagonalLineDown[2] = gameBoard[2][2];


        if (isEveryMarkInGridLineSame(diagonalLineDown)) {
            return true;
        }

        GRID_STATUS[] diagonalLineUp = new GRID_STATUS[3];
        diagonalLineUp[0] = gameBoard[2][2];
        diagonalLineUp[1] = gameBoard[1][1];
        diagonalLineUp[2] = gameBoard[0][0];


        if (isEveryMarkInGridLineSame(diagonalLineUp)) {
            return true;
        }

        return false;
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
    public void unsetAllPositions() {
        for (int i = 0; i < numberOfRowsAndColumns; i++) {
            for (int j = 0; j < numberOfRowsAndColumns; j++) {
                gameBoard[i][j] = GRID_STATUS.UNCLAIMED;
            }
        }
    }

    @Override
    public String toString() {
        String l1 = "\t" + gameBoard[0][0].toString() + "\t|\t" + gameBoard[0][1].toString() + "\t|\t" + gameBoard[0][2].toString() + "\t\n";
        String l2 = "  ------|-------|------\n";
        String l3 = "\t" + gameBoard[1][0].toString() + "\t|\t" + gameBoard[1][1].toString() + "\t|\t" + gameBoard[1][2].toString() + "\t\n";
        String l4 = "  ------|-------|------\n";
        String l5 = "\t" + gameBoard[2][0].toString() + "\t|\t" + gameBoard[2][1].toString() + "\t|\t" + gameBoard[2][2].toString() + "\t";
        return l1 + l2 + l3 + l4 + l5;
    }
}
