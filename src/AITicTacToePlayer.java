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
     * Generates the next move for an AI player.
     *
     * @return the position that has been picked.
     */
    @Override
    public String pickPosition() {
        //TODO write position picker algorithm.
        if (grid.getNumberOfMarks() % 2 == 0) { // This AI player is playing first.
            return pickPositionAsFirst().toString(); //TODO Handle 0.
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

    private Integer pickPositionAsFirst() {
        if (grid.getNumberOfMarks() == 0) {
            return 5;
        } else if (grid.getNumberOfMarks() == 2) {
            // if there is a corner marked mark the opposite.
            // if side marked, mark adjacent corner.
            if (grid.getMarkAt(1) == GridStatus.O_CLAIMED) {
                return 9;
            } else if (grid.getMarkAt(3) == GridStatus.O_CLAIMED) {
                return 7;
            } else if (grid.getMarkAt(7) == GridStatus.O_CLAIMED) {
                return 3;
            } else if (grid.getMarkAt(9) == GridStatus.O_CLAIMED) {
                return 1;
            } else if (grid.getMarkAt(4) == GridStatus.O_CLAIMED
                    || grid.getMarkAt(8) == GridStatus.O_CLAIMED) {
                return 7;
            } else if (grid.getMarkAt(2) == GridStatus.O_CLAIMED
                    || grid.getMarkAt(4) == GridStatus.O_CLAIMED) {
                return 3;
            }
        } else {
            int blockMoveFoundAt = 0; // todo track which cell number the block is at instead.
            int forkMoveFoundAt = 0;
            int oppositeCornerMoveAt = 0;

            // For every row:
            for (int i = 0; i < TicTacToeGrid.NUMBER_OF_ROWS_AND_COLUMNS; i++) {
                //Counts the number of cells in a row with a certain status:
                int xCount = 0, oCount = 0, _Count = 0;
                GridStatus[] row = grid.getRow(i);

                //
                for (int j = 0; j < TicTacToeGrid.NUMBER_OF_ROWS_AND_COLUMNS; j++) {
                    if (row[j] == GridStatus.X_CLAIMED) {
                        xCount++;
                    } else if (row[j] == GridStatus.O_CLAIMED) {
                        oCount++;
                    } else {
                        _Count++;
                    }
                }

                if (xCount == 2 & _Count == 1) {
                    for (int j = 0; j < TicTacToeGrid.NUMBER_OF_ROWS_AND_COLUMNS; j++) {
                        if (row[j] == GridStatus.UNCLAIMED) {
                            return (i * 3) + 1 + j; // Makes winning move.
                        }
                    }
                } else if (oCount == 2 && _Count == 1 && blockMoveFoundAt < 1) {
                    for (int j = 0; j < TicTacToeGrid.NUMBER_OF_ROWS_AND_COLUMNS; j++) {
                        if (row[j] == GridStatus.UNCLAIMED) {
                            blockMoveFoundAt = (i * 3) + 1 + j; // Tracks blocking move.
                        }
                    }
                } else if (i == 0 || i == TicTacToeGrid.NUMBER_OF_ROWS_AND_COLUMNS - 1 && (blockMoveFoundAt < 1 || forkMoveFoundAt < 1) && (_Count > 0)) {
                    if ((row[0] == (GridStatus.X_CLAIMED)) && (row[TicTacToeGrid.NUMBER_OF_ROWS_AND_COLUMNS - 1] == GridStatus.UNCLAIMED)) {
                        forkMoveFoundAt = (i * 3) + 3; //End of the row.
                    } else if ((row[0] == (GridStatus.UNCLAIMED) && row[TicTacToeGrid.NUMBER_OF_ROWS_AND_COLUMNS - 1] == GridStatus.X_CLAIMED)) {
                        forkMoveFoundAt = (i * 3) + 1; //Beginning of the row.
                    } else if ((row[0] == GridStatus.UNCLAIMED) && oppositeCornerMoveAt < 1) {
                        if (i == 0 && grid.getMarkAt(9) == GridStatus.UNCLAIMED) {
                            return 9;
                        } else if (grid.getMarkAt(3) == GridStatus.UNCLAIMED) {
                            return 3;
                        }
                    } else if ((row[TicTacToeGrid.NUMBER_OF_ROWS_AND_COLUMNS - 1] == GridStatus.UNCLAIMED) && oppositeCornerMoveAt < 1) {
                        if (i == 0 && grid.getMarkAt(7) == GridStatus.UNCLAIMED) {
                            return 7;
                        } else if (grid.getMarkAt(1) == GridStatus.UNCLAIMED) {
                            return 1;
                        }
                    }
                }
            }

            // For every column: fixme
            for (int i = 0; i < TicTacToeGrid.NUMBER_OF_ROWS_AND_COLUMNS; i++) {
                //Counts the number of cells in a row with a certain status:
                int xCount = 0, oCount = 0, _Count = 0;
                GridStatus[] column = grid.getRow(i);

                //
                for (int j = 0; j < TicTacToeGrid.NUMBER_OF_ROWS_AND_COLUMNS; j++) {
                    if (column[j] == GridStatus.X_CLAIMED) {
                        xCount++;
                    } else if (column[j] == GridStatus.O_CLAIMED) {
                        oCount++;
                    } else {
                        _Count++;
                    }
                }

                if (xCount == 2 & _Count == 1) {
                    for (int j = 0; j < TicTacToeGrid.NUMBER_OF_ROWS_AND_COLUMNS; j++) {
                        if (column[j] == GridStatus.UNCLAIMED) {
                            return (j * 3) + 1 + i; // Makes winning move.
                        }
                    }
                } else if (oCount == 2 && _Count == 1 && blockMoveFoundAt < 1) {
                    for (int j = 0; j < TicTacToeGrid.NUMBER_OF_ROWS_AND_COLUMNS; j++) {
                        if (column[j] == GridStatus.UNCLAIMED) {
                            blockMoveFoundAt = (j * 3) + 1 + i;
                            ; // Tracks blocking move.
                        }
                    }
                } else if ((column[0] == GridStatus.UNCLAIMED) && oppositeCornerMoveAt < 1) {
                    if (i == 0 && grid.getMarkAt(9) == GridStatus.UNCLAIMED) {
                        return 9;
                    } else if (grid.getMarkAt(3) == GridStatus.UNCLAIMED) {
                        return 3;
                    }
                } else if ((column[TicTacToeGrid.NUMBER_OF_ROWS_AND_COLUMNS - 1] == GridStatus.UNCLAIMED) && oppositeCornerMoveAt < 1) {
                    if (i == 0 && grid.getMarkAt(7) == GridStatus.UNCLAIMED) {
                        return 7;
                    } else if (grid.getMarkAt(1) == GridStatus.UNCLAIMED) {
                        return 1;
                    }
                }
            }

            //block:

            if (blockMoveFoundAt > 0) {
                return blockMoveFoundAt;
            }
            if (forkMoveFoundAt > 0) {
                return forkMoveFoundAt;
            }
            if (oppositeCornerMoveAt > 0) {
                return oppositeCornerMoveAt;
            }

            // Empty corner
            int[] cornerPositions = {1, 3, 7, 9};

            for (int cornerPosition : cornerPositions) {
                if (grid.getMarkAt(cornerPosition) == GridStatus.UNCLAIMED) {
                    return cornerPosition;
                }
            }

            // Empty side
            int[] sidePositions = {2, 4, 6, 8};

            for (int sidePosition : sidePositions) {
                if (grid.getMarkAt(sidePosition) == GridStatus.UNCLAIMED) {
                    return sidePosition;
                }
            }

        }

        return 0;
    }

    private int pickPositionAsSecond() { //todo
        return 0;
    }
}
