import java.util.Scanner;

/**
 * <b>File Name: </b> <p>HumanPlayer.java</p>
 * <b>Description: </b>
 * <p>
 * Represents a human player of a game where to make a move, one must enter a
 * string to pick a position.
 * </p>
 *
 * @author nylecm
 */
public class HumanPlayer extends Player {
    /**
     * Instantiates a new Human player.
     *
     * @param name the name of the player.
     */
    public HumanPlayer(String name) {
        super(name);
    }

    /**
     * Allows a human player to manually input a position.
     *
     * @return the position that has been picked.
     */
    @Override
    public String pickPosition() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Allows a human player to make a move without having to input it via command line
     *
     * @param gridNumber the position to be picked.
     * @return the position that has been picked.
     */
    public String pickPosition(Integer gridNumber) {
        return gridNumber.toString();
    }
}
