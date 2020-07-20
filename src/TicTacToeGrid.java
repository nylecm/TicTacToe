/**
 * @author nylecm
 */
public class TicTacToeGrid {
    public final int numberOfRowsAndColumns = 3;
    public final int maxGridPosition = numberOfRowsAndColumns *
            numberOfRowsAndColumns;

    private GRID_STATUS[][] gameBoard = new GRID_STATUS[numberOfRowsAndColumns][numberOfRowsAndColumns];
    private int nextPlayer = 1;

    public TicTacToeGrid() {

    }

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

        incrementPlayer();
    }

    public int convertPositionNumberToRowNumber(int gridPosition) throws IllegalArgumentException {
        if (gridPosition < 1 || gridPosition > maxGridPosition) {
            throw new IndexOutOfBoundsException("Grid Position cannot be less " +
                    "than 1 or more than " + maxGridPosition + ".");
        } else if (gridPosition <= 3) {
            return 0;
        } else if (gridPosition <= 6) {
            return 1;
        } else {
            return 2;
        }
    }

    public int convertPositionNumberToColumnNumber(int gridPosition) throws IllegalArgumentException {
        if (gridPosition < 1 || gridPosition > maxGridPosition) {
            throw new IndexOutOfBoundsException("Grid Position cannot be less " +
                    "than 1 or more than " + maxGridPosition + ".");
        } else if (gridPosition % 3 == 1) {
            return 0;
        } else if (gridPosition % 3 == 2) {
            return 1;
        } else {
            return 2;
        }
    }

    public boolean isGridPositionFree(int gridRow, int gridColumn) {
        return gameBoard[gridRow][gridColumn] == null;
    }

    public int getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(int nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public void incrementPlayer() {
        if (nextPlayer == 1) {
            nextPlayer++;
        } else {
            nextPlayer = 1;
        }
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

    public String toString() {
        return gameBoard.toString(); //TODO game board toString
    }
}
