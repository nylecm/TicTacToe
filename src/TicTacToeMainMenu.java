import javax.swing.*;
import java.awt.event.ActionListener;

//todo comment TicTacToeMainMenu
class TicTacToeMainMenu extends JFrame {
    private final static int WIDTH = 300;
    private final static int HEIGHT = 200;

    TicTacToeMainMenu() {
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
        setTitle("Main Menu");

        JButton startGameButton = new JButton("Start Game");

        ActionListener startGameButtonListener = e -> {
            TicTacToeGameWindow newGameWindow = new TicTacToeGameWindow();
            newGameWindow.setVisible(true);
        };
        startGameButton.addActionListener(startGameButtonListener);

        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.add(startGameButton);
        add(mainMenuPanel);
    }
}
