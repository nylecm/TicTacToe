import javax.swing.*;
import java.awt.*;

/**
 * <b>File Name: </b> <p>GridPositionButton.java</p>
 * <b>Description: </b>
 * <p>
 * A JButton subclass that is used in a tic tac toe grid.
 * </p>
 *
 * @author nylecm
 */
class GridPositionButton extends JButton {
    private static final int BUTTON_TEXT_SIZE = 40;

    /**
     * Instantiates a new grid position button object setting text to grid
     * position number, and setting the buttons font.
     *
     * @param gridPosition the grid position that the button represents.
     */
    GridPositionButton(Integer gridPosition) {
        setText(gridPosition.toString());
        setFont(new Font("Ariel", Font.PLAIN, BUTTON_TEXT_SIZE));
    }
}
