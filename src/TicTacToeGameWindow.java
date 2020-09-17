import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

//todo comment TicTacToeGameWindow
public class TicTacToeGameWindow extends JFrame {
    public final static int WIDTH = 300;
    public final static int HEIGHT = 320;

    private boolean isGridLocked = false;

    private final TicTacToeGUIGame game = new TicTacToeGUIGame();

    GridPositionButton[] gridPositionButtons =
            new GridPositionButton[TicTacToeGrid.NUMBER_OF_GRID_POSITIONS];

    public TicTacToeGameWindow() {
        createComponents();

        if (game.getGameMode() == 3) {
            aiMove(0);
        } else if (game.getGameMode() == 4) {
            initAiVsAiMode();
        }
    }

    private void initAiVsAiMode() {
        GameStatus status = GameStatus.GAME_TO_CONTINUE;

        while (!isGameToEnd(status)) {
            aiMove(1);
        }
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
            ActionListener actionListener = e -> gridButtonPressed(gridPositionButtons[finalI]);
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
            boolean isMoveMadeOrGameFinished;
            try {
                isMoveMadeOrGameFinished = playerMove(gridPositionButton);
            } catch (IllegalStateException e) {
                return;
            }

            if (isMoveMadeOrGameFinished && isHumanPlayingAgainstAi()) {
                Thread newThread = new Thread(() -> {
                    aiMove(1);
                });
                newThread.start();
            }
        }
    }

    private boolean playerMove(GridPositionButton gridPositionButton) throws IllegalStateException {
        if (!isGridLocked) {
            int gridPositionButtonNumber = Integer.parseInt(gridPositionButton.getText());
            Color oldButtonTextColour = gridPositionButton.getForeground();

            try {
                gridPositionButton.setText(getPlayerMark());
                gridPositionButton.setForeground(getPlayerColour());
                GameStatus status = game.handleInput(gridPositionButtonNumber);
                respondToWinTie(status);

                if (isGameToEnd(status)) {
                    terminateGame();
                    return false;
                }
            } catch (IllegalArgumentException e) {
                gridPositionButton.setText(String.valueOf(gridPositionButtonNumber));
                gridPositionButton.setForeground(oldButtonTextColour);
                return false;
            }
            return true;
        } else {
            throw new IllegalStateException("Grid is locked!");
        }
    }

    private void aiMove(int timeDelay) {
        isGridLocked = true;
        int aiMove = Integer.parseInt(game.players[game.getGrid().getNextPlayer() - 1].pickPosition());

        try {
            TimeUnit.SECONDS.sleep(timeDelay);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        GridPositionButton button = gridPositionButtons[aiMove - 1];
        button.setText(getPlayerMark());
        button.setForeground(getPlayerColour());

        GameStatus status = game.handleInput(aiMove);
        respondToWinTie(status);

        if (isGameToEnd(status)) {
            terminateGame();
        }
        isGridLocked = false;
    }

    private String getPlayerMark() {
        if (game.getGrid().getNextPlayer() == 1) {
            return GridStatus.X_CLAIMED.toString();
        } else {
            return GridStatus.O_CLAIMED.toString();
        }
    }

    private Color getPlayerColour() {
        if (game.getGrid().getNextPlayer() == 1) {
            return Color.BLUE;
        } else {
            return Color.RED;
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
