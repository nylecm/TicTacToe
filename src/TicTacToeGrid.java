public class TicTacToeGrid {
    public static final int NUMBER_OF_ROWS_AND_COLUMNS = 3;
    public static final int MAX_GRID_POSITION = NUMBER_OF_ROWS_AND_COLUMNS *
            NUMBER_OF_ROWS_AND_COLUMNS;

    private static GRID_STATUS[][] gameBoard = new GRID_STATUS[NUMBER_OF_ROWS_AND_COLUMNS][NUMBER_OF_ROWS_AND_COLUMNS];

    public TicTacToeGrid() {

    }

    public static boolean isGameFinished() {
        return isWin() || isTie();
    }

    private static boolean isTie() {

    }

    private static boolean isWin() {
        // True When 3 in a row.
        for (GRID_STATUS[] gridRow : ) {

        }
    }

    @Deprecated
    public static boolean isGridPositionOccupied(int gridPosition) {
        return gameBoard[convertPositionNumberToRowNumber(gridPosition)]
                [convertPositionNumberToColumnNumber(gridPosition)] == null;
    }

    public static boolean isGridPositionOccupied(int gridRow, int gridColumn) {
        return gameBoard[gridRow][gridColumn] == null;
    }

    public static int convertPositionNumberToRowNumber(int gridPosition) {
        if (gridPosition < 1 || gridPosition > MAX_GRID_POSITION) {
            throw new IllegalArgumentException("Grid Position cannot be less " +
                    "than 1 or more than " + MAX_GRID_POSITION + ".");
        } else if (gridPosition <= 3) {
            return 0;
        } else if (gridPosition <= 6) {
            return 1;
        } else {
            return 2;
        }
    }

    public static int convertPositionNumberToColumnNumber(int gridPosition) {
        if (gridPosition < 1 || gridPosition > MAX_GRID_POSITION) {
            throw new IllegalArgumentException("Grid Position cannot be less " +
                    "than 1 or more than " + MAX_GRID_POSITION + ".");
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
