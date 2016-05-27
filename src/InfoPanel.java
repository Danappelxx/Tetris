import javax.swing.*;
import java.awt.*;

/**
 * Created by dan on 5/20/16.
 */

public class InfoPanel extends JPanel {

    JLabel scoreLabel;
    JButton addShapeButton;

    public InfoPanel() {
        setLayout(new GridLayout(2, 1));

        scoreLabel = new JLabel("0", SwingConstants.CENTER);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        addShapeButton = new JButton("Add shape");
        addShapeButton.setFocusable(false);

        add(scoreLabel);
        add(addShapeButton);
    }

    public void setScore(int score) {
        this.scoreLabel.setText(Integer.toString(score));
    }

    public JButton getAddShapeButton() {
        return this.addShapeButton;
    }
}
