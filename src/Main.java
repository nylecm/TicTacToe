/**
 * <b>File Name: </b> <p>Main.java</p>
 * <b>Description: </b>
 * <p>
 * The main class of the program; used to launch the program.
 * </p>
 *
 * @author nylecm
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments, if the first argument is -cmd, game will
     *             be launched in command-line mode.
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-cmd")) {
            TicTacToeCommandLine newCommandLineGame = new TicTacToeCommandLine();
        } else {
            TicTacToeMainMenu menu = new TicTacToeMainMenu();
            menu.setVisible(true);
        }
    }
}
