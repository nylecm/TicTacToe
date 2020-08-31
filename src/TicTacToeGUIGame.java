public class TicTacToeGUIGame extends TicTacToeGame {
    public TicTacToeGUIGame() {
        playTicTacToe();
    }

    protected void playTicTacToe() {
        TicTacToeMainMenu menu = new TicTacToeMainMenu();
        menu.setVisible(true);
    }
}

