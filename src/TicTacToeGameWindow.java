import javax.swing.*;
import java.awt.*;

public class TicTacToeGameWindow extends JFrame {
    public final static int WIDTH = 300;
    public final static int HEIGHT = 300;

    public TicTacToeGameWindow() {
        createComponents();
    }

    private void createComponents() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe Game");

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(3, 3));

        grid.add(new JButton("1"));
        grid.add(new JButton("2"));
        grid.add(new JButton("3"));
        grid.add(new JButton("4"));
        grid.add(new JButton("5"));
        grid.add(new JButton("6"));
        grid.add(new JButton("7"));
        grid.add(new JButton("8"));
        grid.add(new JButton("9"));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(grid, BorderLayout.CENTER);
        add(panel);
    }

    public static void main(String[] args) {
        JFrame newWin = new TicTacToeGameWindow();
        newWin.setVisible(true);
    }
}
