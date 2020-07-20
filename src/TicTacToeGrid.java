public class TicTacToeGrid {
    public final int numberOfRowsAndColumns = 3;
    public final int maxGridPosition = numberOfRowsAndColumns *
            numberOfRowsAndColumns;

    private GRID_STATUS[][] gameBoard = new GRID_STATUS[numberOfRowsAndColumns][numberOfRowsAndColumns];

    public TicTacToeGrid() {

    }

    public boolean isGameFinished() {
        return isWin() || isTie();
    }

    private boolean isTie() {

    }

    private boolean isWin() {
        // True When 3 in a row.
        for (GRID_STATUS[] gridRow : gameBoard) {
            if (isEveryGridPositionSame()) {

            }
        }
    }

    @Deprecated
    public boolean isGridPositionOccupied(int gridPosition) {
        return gameBoard[convertPositionNumberToRowNumber(gridPosition)]
                [convertPositionNumberToColumnNumber(gridPosition)] == null;
    }

    public boolean isGridPositionOccupied(int gridRow, int gridColumn) {
        return gameBoard[gridRow][gridColumn] == null;
    }

    public int convertPositionNumberToRowNumber(int gridPosition) {
        if (gridPosition < 1 || gridPosition > maxGridPosition) {
            throw new IllegalArgumentException("Grid Position cannot be less " +
                    "than 1 or more than " + maxGridPosition + ".");
        } else if (gridPosition <= 3) {
            return 0;
        } else if (gridPosition <= 6) {
            return 1;
        } else {
            return 2;
        }
    }

    public int convertPositionNumberToColumnNumber(int gridPosition) {
        if (gridPosition < 1 || gridPosition > maxGridPosition) {
            throw new IllegalArgumentException("Grid Position cannot be less " +
                    "than 1 or more than " + maxGridPosition + ".");
        } else if (gridPosition % 3 == 1) {
            return 0;
        } else if (gridPosition % 3 == 2) {
            return 1;
        } else {
            return 2;
        }
    }

    public String toString() {
        return gameBoard.toString(); //TODO game board toString
    }
}
