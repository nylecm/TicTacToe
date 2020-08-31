public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-cmd")) {
            TicTacToeCommandLine newCommandLineGame = new TicTacToeCommandLine();
        } else {
            TicTacToeGUIGame newGUIGame = new TicTacToeGUIGame();
        }
    }
}
