public class TicTacToeGrid {
    public final int numberOfRowsAndColumns = 3;
    public final int maxGridPosition = numberOfRowsAndColumns *
            numberOfRowsAndColumns;

    private GRID_STATUS[][] gameBoard = new GRID_STATUS[numberOfRowsAndColumns][numberOfRowsAndColumns];

    public TicTacToeGrid() {

    }

    public boolean isGameFinished() {
        return isWin(); //TODO add tie condition
    }

    /*private boolean isTie() {

    }*/

    private boolean isWin() {
        // True When 3 in a row.
        for (GRID_STATUS[] gridRow : gameBoard) {
            if (isEveryGridPositionSame(gridRow)) {
                return true;
            }
        }

        for (int i = 0; i < numberOfRowsAndColumns; i++) {
            GRID_STATUS[] gridColumn = new GRID_STATUS[numberOfRowsAndColumns];
            for (int j = 0; j < numberOfRowsAndColumns; j++) {
                gridColumn[j] = gameBoard[i][j];
                if(isEveryGridPositionSame(gridColumn)) {
                    return true;
                }
            }
        }

        //Diagonal win:
        GRID_STATUS[] diagonalLineDown = new GRID_STATUS[3];
        diagonalLineDown[0] = gameBoard[0][0];
        diagonalLineDown[1] = gameBoard[1][1];
        diagonalLineDown[2] = gameBoard[2][2];


        if (isEveryGridPositionSame(diagonalLineDown)) {
            return true;
        }

        GRID_STATUS[] diagonalLineUp = new GRID_STATUS[3];
        diagonalLineUp[0] = gameBoard[2][2];
        diagonalLineUp[1] = gameBoard[1][1];
        diagonalLineUp[2] = gameBoard[0][0];


        if (isEveryGridPositionSame(diagonalLineUp)) {
            return true;
        }

        return false;
    }

    private boolean isEveryGridPositionSame(GRID_STATUS[] gridLine) {
        GRID_STATUS lastGridPosition = gridLine[0];
        for (int i = 1; i < numberOfRowsAndColumns; i++) {
            if (gridLine[i] != lastGridPosition || gridLine[i] == null) {
                return false;
            }
        }
        return true;
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
