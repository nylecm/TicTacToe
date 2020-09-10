import javax.swing.*;

public class TicTacToeGUIGame extends TicTacToeGame {
    private int gameMode;

    public TicTacToeGUIGame() {
        playTicTacToe();
    }

    protected void playTicTacToe() {
        // Configure game:

        // Ask for game mode:
        // 1 2 3 or 4
        gameMode = Integer.parseInt(JOptionPane.showInputDialog("Select game mode 1, 2, 3, or 4"));
        //todo validation

        // Ask for player name/ AI difficulty:

        if (gameMode == 1) {
            players[0] = new HumanPlayer("Human player 1");
            players[1] = new HumanPlayer("Human player 2");
        } else if (gameMode == 2) {
            players[0] = new HumanPlayer("Human player 1");
            players[1] = new AITicTacToePlayer("AI player 2", AIDifficulty.HARD, grid);
        } else if (gameMode == 3) {
            players[0] = new AITicTacToePlayer("AI player 1", AIDifficulty.HARD, grid);
            players[1] = new HumanPlayer("Human player 2");
        } else if (gameMode == 4) {
            players[0] = new AITicTacToePlayer("AI player 1", AIDifficulty.HARD, grid);
            players[1] = new AITicTacToePlayer("AI player 2", AIDifficulty.HARD, grid);
        } else {
            System.out.println("BAD INPUT"); //todo validation...
        }
    }

    public GameStatus handleInput(int gridPosition) throws IllegalArgumentException {
        grid.markGrid(Integer.parseInt(String.valueOf(gridPosition)));
        grid.incrementPlayer();

        return checkWinTie();
    }

    private GameStatus checkWinTie() {
        if (getGrid().getNumberOfMarks() >= 3) {
            if (getGrid().isWin()) { //Win
                return GameStatus.WIN;
            } else if (getGrid().isMaxMovesMade()) { //Tie
                return GameStatus.TIE;
            }
        }
        return GameStatus.GAME_TO_CONTINUE;
    }

    public int getGameMode() {
        return gameMode;
    }
}
