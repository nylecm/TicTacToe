import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGameWindow extends JFrame {
    public final static int WIDTH = 300;
    public final static int HEIGHT = 320;

    private final TicTacToeGUIGame game = new TicTacToeGUIGame();

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

            int finalI = i;
            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gridButtonPressed(gridPositionButtons[finalI]);
                }
            };

            gridPositionButtons[i].addActionListener(actionListener);

            grid.add(gridPositionButtons[i]);
        }

        grid.setSize(WIDTH, WIDTH);

        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        bottom.setSize(WIDTH, 20);

        JLabel winCount = new JLabel("Player 1: x Player 2: y");
        JButton endGame = new JButton("Finish Game");

        ActionListener finishActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hideThisWindow();
            }
        }; //TODO null everything to enable gc.......

        endGame.addActionListener(finishActionListener);

        bottom.add(winCount, BorderLayout.WEST);
        bottom.add(endGame, BorderLayout.EAST);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(grid, BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);

        add(panel);
    }

    private void gridButtonPressed(GridPositionButton gridPositionButton) {
        if (!(gridPositionButton.getText().equals(GridStatus.X_CLAIMED.toString()) ||
                gridPositionButton.getText().equals(GridStatus.O_CLAIMED.toString()))) {
            try {
                TicTacToeGrid grid = game.getGrid();

                grid.markGrid(Integer.parseInt(gridPositionButton.getText()));

                String playerMarkSymbol = "";

                if (grid.getNextPlayer() == 1) {
                    playerMarkSymbol = GridStatus.X_CLAIMED.toString();
                } else {
                    playerMarkSymbol = GridStatus.O_CLAIMED.toString();
                }

                gridPositionButton.setText(playerMarkSymbol); //todo set text to cur plr symb

                if (game.getGrid().getNumberOfMarks() >= 3) {
                    if (game.getGrid().isWin()) { //Win
                        JOptionPane.showMessageDialog(null, "You have won!");
                    } else if (game.getGrid().isMaxMovesMade()) { //Tie
                        JOptionPane.showMessageDialog(null, "There has been a tie.");
                    }
                }

                grid.incrementPlayer();

            } catch (IllegalArgumentException e) {

            }
        }


    }

    private void hideThisWindow() {
        this.setVisible(false);
    }
}
