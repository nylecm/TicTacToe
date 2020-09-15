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

        if (game.getGameMode() == 3) {
            int aiFirstMove = Integer.parseInt(game.players[0].pickPosition());
            game.getGrid().markGrid(aiFirstMove);
            gridPositionButtons[aiFirstMove - 1].setText(GridStatus.X_CLAIMED.toString());
            game.getGrid().incrementPlayer();
        } else if (game.getGameMode() == 4) {
            initAiVsAiMode();
            //todo gui ai vs ai
        }
    }

    private void initAiVsAiMode() {

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

        ActionListener finishActionListener = e -> hideThisWindow();

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

            int gridPositionButtonNumber = Integer.parseInt(gridPositionButton.getText());

            gridPositionButton.setText(getPlayerMark()); // Makes player move:

            try {
                GameStatus status = game.handleInput(gridPositionButtonNumber);
                respondToWinTie(status);

                if (isGameToEnd(status)) {
                    terminateGame();
                    return;
                }
            } catch (IllegalArgumentException e) {
                gridPositionButton.setText(String.valueOf(gridPositionButtonNumber));
                return;
            }

            if (isHumanPlayingAgainstAi()) { // Makes AI move:
                aiMove();
            }
        }
    }

    private String getPlayerMark() {
        if (game.getGrid().getNextPlayer() == 1) {
            return GridStatus.X_CLAIMED.toString();
        } else {
            return GridStatus.O_CLAIMED.toString();
        }
    }

    private void aiMove() {
        int aiMove = Integer.parseInt(game.players[game.getGrid().getNextPlayer() - 1].pickPosition());
        gridPositionButtons[aiMove - 1].setText(getPlayerMark());
        GameStatus status = game.handleInput(aiMove);
        respondToWinTie(status);

        if (isGameToEnd(status)) {
            terminateGame();
        }
    }

    private void terminateGame() {
        setVisible(false);
    }

    private boolean isHumanPlayingAgainstAi() {
        int gameMode = game.getGameMode();
        return gameMode == 2 || gameMode == 3;
    }

    private boolean isGameToEnd(GameStatus status) {
        return status != GameStatus.GAME_TO_CONTINUE;
    }

    private void hideThisWindow() {
        this.setVisible(false);
    }

    private void respondToWinTie(GameStatus status) {
        if (status != GameStatus.GAME_TO_CONTINUE) {
            if (status == GameStatus.WIN) {
                game.getGrid().incrementPlayer(); //todo find a less hacky way!
                JOptionPane.showMessageDialog(null, game.players[game.getGrid().getNextPlayer() - 1].getName() + " won!");
            } else if (status == GameStatus.TIE) {
                JOptionPane.showMessageDialog(null, "There has been a tie.");
            }
        }
    }
}
