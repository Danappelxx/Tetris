import javax.swing.*;

/**
 * Created by dan on 5/20/16.
 */

public class InfoPanel extends JPanel {

    JLabel scoreLabel;

    public InfoPanel() {
        scoreLabel = new JLabel();
        setScore(0);
        add(scoreLabel);
    }

    public void setScore(int score) {
        this.scoreLabel.setText(Integer.toString(score));
    }
}
