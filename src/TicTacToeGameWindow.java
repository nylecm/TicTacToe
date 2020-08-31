import javax.swing.*;
import java.awt.*;

public class TicTacToeGameWindow extends JFrame {
    public final static int WIDTH = 300;
    public final static int HEIGHT = 320;

    GridPositionButton[] gridPositionButtons = new GridPositionButton[TicTacToeGrid.NUMBER_OF_GRID_POSITIONS];

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

        for (int i = 0; i < gridPositionButtons.length; i++) {
            gridPositionButtons[i] = new GridPositionButton(i + 1);
            grid.add(gridPositionButtons[i]);
        }

        grid.setSize(WIDTH, WIDTH);

        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        bottom.setSize(WIDTH, 20);

        JLabel winCount = new JLabel("Player 1: x Player 2: y");
        JButton endGame = new JButton("Finish Game");

        bottom.add(winCount, BorderLayout.WEST);
        bottom.add(endGame, BorderLayout.EAST);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(grid, BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);

        add(panel);
    }

    private void gridButtonPressed(GridPositionButton gridPositionButton) {
        if (!gridPositionButton.getText().equals(GridStatus.X_CLAIMED) || gridPositionButton.getText().equals(GridStatus.O_CLAIMED)) {
            gridPositionButton.getText(); //Mark grid at this pos
        }
    }
}
