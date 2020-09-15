import javax.swing.*;

public class TicTacToeGUIGame extends TicTacToeGame {
    private int gameMode;

    public TicTacToeGUIGame() {
        playTicTacToe();
    }

    protected void playTicTacToe() {
        boolean isValidGameModeEntered = false;

        while (!isValidGameModeEntered) {
            gameMode = Integer.parseInt(JOptionPane.showInputDialog("Select game mode 1, 2, 3, or 4"));

            if (gameMode == 1) {
                isValidGameModeEntered = true;
                String player1Name = JOptionPane.showInputDialog("Enter player 1 name:");
                String player2Name = JOptionPane.showInputDialog("Enter player 2 name:");

                players[0] = new HumanPlayer(player1Name);
                players[1] = new HumanPlayer(player2Name);
            } else if (gameMode == 2) {
                isValidGameModeEntered = true;
                String player1Name = JOptionPane.showInputDialog("Enter player 1 name:");
                AIDifficulty player2difficulty = inputAiDifficulty(2);

                players[0] = new HumanPlayer(player1Name);
                players[1] = new AITicTacToePlayer("AI player 2", player2difficulty, grid);
            } else if (gameMode == 3) {
                isValidGameModeEntered = true;
                AIDifficulty player1difficulty = inputAiDifficulty(1);
                String player2Name = JOptionPane.showInputDialog("Enter player 2 name:");

                players[0] = new AITicTacToePlayer("AI player 1", player1difficulty, grid);
                players[1] = new HumanPlayer(player2Name);
            } else if (gameMode == 4) {
                isValidGameModeEntered = true;
                AIDifficulty player1difficulty = inputAiDifficulty(1);
                AIDifficulty player2difficulty = inputAiDifficulty(2);

                players[0] = new AITicTacToePlayer("AI player 1", player1difficulty, grid);
                players[1] = new AITicTacToePlayer("AI player 2", player2difficulty, grid);
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid game mode.");
            }
        }
    }

    private AIDifficulty inputAiDifficulty(int aiPlayerNumber) {
        while (true) {
            String input = JOptionPane.showInputDialog("Enter ai difficulty for AI player " + aiPlayerNumber + ":");
            try {
                return validateAiDifficultyInput(input);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
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
