import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public String pickPosition() {
        //A mere human needs to manually input a position number.
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
