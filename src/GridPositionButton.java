import javax.swing.*;
import javax.xml.bind.annotation.XmlType;
import java.awt.*;

public class GridPositionButton extends JButton {
    private final Integer gridPosition;
    private GridStatus gridStatus = GridStatus.UNCLAIMED;
    private static final int BUTTON_TEXT_SIZE = 40;

    public GridPositionButton(Integer gridPosition) {
        this.gridPosition = gridPosition;
        setText(gridPosition.toString());
        setFont(new Font("Ariel", Font.PLAIN, BUTTON_TEXT_SIZE));
    }

    public Integer getGridPosition() {
        return gridPosition;
    }

    public GridStatus getGridStatus() {
        return gridStatus;
    }

    public void setGridStatus(GridStatus gridStatus) {
        this.gridStatus = gridStatus;
    }
}
