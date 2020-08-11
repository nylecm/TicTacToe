
/**
 * <b>File Name: </b> <p>TicTacToeGrid.java</p>
 * <b>Description: </b>
 * <p>
 * Represents a standard tic tac toe grid.
 * </p>
 *
 * @author nylecm
 */
public class TicTacToeGrid {

    // Configures size of a grid:
    public final static int NUMBER_OF_ROWS_AND_COLUMNS = 3;
    public final static int NUMBER_OF_GRID_POSITIONS =
            NUMBER_OF_ROWS_AND_COLUMNS * NUMBER_OF_ROWS_AND_COLUMNS;

    // Represents a tic-tac-toe grid:
    private GridStatus[][] grid =
            new GridStatus[NUMBER_OF_ROWS_AND_COLUMNS][NUMBER_OF_ROWS_AND_COLUMNS];

    private int nextPlayer = 1;
    private int numberOfMarks = 0;

    /**
     * @return the number of marks made on the board.
     */
    public int getNumberOfMarks() {
        return numberOfMarks;
    }

    /**
     * Instantiates a new empty tic tac toe grid.
     */
    public TicTacToeGrid() {
        unclaimAllPositions();
    }

    /**
     * Makes a mark on the grid.
     *
     * @param gridPosition the grid position where the mark ought to be made.
     * @throws IndexOutOfBoundsException when grid position is out of range.
     * @throws IllegalArgumentException  when grid position is already occupied.
     */
    public void markGrid(int gridPosition) throws IndexOutOfBoundsException,
            IllegalArgumentException {
        int row = convertPositionNumberToRowNumber(gridPosition);
        int column = convertPositionNumberToColumnNumber(gridPosition);

        if (!isGridPositionFree(row, column)) {
            throw new IllegalArgumentException("Grid Position already occupied.");
        }

        GridStatus markToBeMade;
        if (getNextPlayer() == 1) {
            markToBeMade = GridStatus.X_CLAIMED;
        } else {
            markToBeMade = GridStatus.O_CLAIMED;
        }
        grid[row][column] = markToBeMade;
        numberOfMarks++;
    }

    /**
     * Converts a position number 1 to 9 to a row number for the matrix
     * implementation.
     *
     * @param gridPosition the position number 1 to 9.
     * @return the index of the corresponding row in the matrix.
     * @throws IndexOutOfBoundsException when grid position is out of range.
     */
    private int convertPositionNumberToRowNumber(int gridPosition) throws
            IndexOutOfBoundsException {
        if (gridPosition < 1 || gridPosition > NUMBER_OF_GRID_POSITIONS) {
            throw new IndexOutOfBoundsException("Grid Position cannot be less " +
                    "than 1 or more than " + NUMBER_OF_GRID_POSITIONS + ".");
        } else if (gridPosition <= 3) {
            return 0;
        } else if (gridPosition <= 6) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * Converts a position number 1 to 9 to a column number for the matrix
     * implementation.
     *
     * @param gridPosition the position number 1 to 9.
     * @return the index of the corresponding column in the matrix.
     * @throws IllegalArgumentException when grid position is out of range.
     */
    private int convertPositionNumberToColumnNumber(int gridPosition) throws
            IllegalArgumentException {
        if (gridPosition < 1 || gridPosition > NUMBER_OF_GRID_POSITIONS) {
            throw new IndexOutOfBoundsException("Grid Position cannot be less " +
                    "than 1 or more than " + NUMBER_OF_GRID_POSITIONS + ".");
        } else if (gridPosition % 3 == 1) {
            return 0;
        } else if (gridPosition % 3 == 2) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * Checks if the given position on the grid is unclaimed.
     *
     * @param gridRow    the index of the row of the given position.
     * @param gridColumn the index of the column of the given position.
     * @return true if grid position is unclaimed.
     */
    public boolean isGridPositionFree(int gridRow, int gridColumn)
            throws IndexOutOfBoundsException {
        return grid[gridRow][gridColumn] == GridStatus.UNCLAIMED;
    }

    public GridStatus getMarkAt(int gridPosition) {
        return grid[convertPositionNumberToRowNumber(gridPosition)]
                [convertPositionNumberToColumnNumber(gridPosition)];
    }

    /**
     * Gets number of the next player.
     *
     * @return the next player
     */
    public int getNextPlayer() {
        return nextPlayer;
    }

    /**
     * Increments the next player. If next player was previously 1, it will
     * change to 0, if it was previously 0 it will switch to 1.
     */
    public void incrementPlayer() {
        if (nextPlayer == 1) {
            nextPlayer++;
        } else {
            nextPlayer = 1;
        }
    }

    /**
     * Checks if there is a winning combination of marks on the board. That is
     * if the are 3 Xs or Os in a line horizontally, vertically or diagonally.
     *
     * @return true if there is a winning combination of marks on the board.
     */
    public boolean isWin() {
        // Win when a horizontal row is complete.
        for (GridStatus[] gridRow : grid) {
            if (isEveryMarkInGridLineSame(gridRow)) {
                return true;
            }
        }

        //Win when a vertical column is complete.
        for (int i = 0; i < NUMBER_OF_ROWS_AND_COLUMNS; i++) { //For top item in column:
            GridStatus[] gridColumn = getColumn(i);
            if (isEveryMarkInGridLineSame(gridColumn)) {
                return true;
            }
        }

        //Diagonal wins:
        GridStatus[] diagonalLineDown = new GridStatus[3];
        diagonalLineDown[0] = grid[0][0];
        diagonalLineDown[1] = grid[1][1];
        diagonalLineDown[2] = grid[2][2];

        if (isEveryMarkInGridLineSame(diagonalLineDown)) {
            return true;
        }

        GridStatus[] diagonalLineUp = new GridStatus[3];
        diagonalLineUp[0] = grid[0][2];
        diagonalLineUp[1] = grid[1][1];
        diagonalLineUp[2] = grid[2][0];

        if (isEveryMarkInGridLineSame(diagonalLineUp)) {
            return true;
        }

        return false;
    }

    /**
     * Checks if all marks in a line are the same.
     *
     * @param gridLine an array of marks in a line on the grid.
     * @return true if all members of the array are the same mark (but not
     * unclaimed).
     */
    private boolean isEveryMarkInGridLineSame(GridStatus[] gridLine) {
        GridStatus lastGridPosition = gridLine[0];
        for (int i = 1; i < NUMBER_OF_ROWS_AND_COLUMNS; i++) {
            if (gridLine[i] != lastGridPosition || gridLine[i] == GridStatus.UNCLAIMED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets a row from the tic tac toe grid.
     *
     * @param rowNumber the number of the row where 0 is the first row.
     * @return the row as a GridStatus[].
     */
    public GridStatus[] getRow(int rowNumber) {
        return grid[rowNumber];
    }

    /**
     * Gets a column from the tic tac toe grid.
     *
     * @param columnNumber the number of the column where 0 is the first column.
     * @return the column as a GridStatus[].
     */
    public GridStatus[] getColumn(int columnNumber) {
        GridStatus[] gridColumn = new GridStatus[NUMBER_OF_ROWS_AND_COLUMNS];
        //loop down 2 more times and add to array.
        for (int j = 0; j < NUMBER_OF_ROWS_AND_COLUMNS; j++) {
            gridColumn[j] = grid[j][columnNumber];
        }
        return gridColumn;
    }

    /**
     * Checks if the board is fully filled up with marks.
     *
     * @return true if the number of marks made is greater than the number of grid positions.
     */
    public boolean isMaxMovesMade() {
        return numberOfMarks >= NUMBER_OF_GRID_POSITIONS;
    }

    /**
     * Makes every position on the board unclaimed.
     */
    private void unclaimAllPositions() {
        for (int i = 0; i < NUMBER_OF_ROWS_AND_COLUMNS; i++) {
            for (int j = 0; j < NUMBER_OF_ROWS_AND_COLUMNS; j++) {
                grid[i][j] = GridStatus.UNCLAIMED;
            }
        }
    }

    /**
     * @return a word art grid with all the marks made visible.
     */
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
