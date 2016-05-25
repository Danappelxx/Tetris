import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dan on 5/9/16.
 */

public class TetrisView extends JFrame {

    private BoardPanel boardPanel;
    private InfoPanel infoPanel;

    public TetrisView() {
        super("Tetris");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dim.getHeight() - 150;
        int width = height/2 - 25;
        this.setSize(width, height);
        this.setLocation(dim.width / 2 - width / 2, dim.height / 2 - height / 2);

        boardPanel = new BoardPanel();
        infoPanel = new InfoPanel();

        add(boardPanel);
        add(infoPanel);

        Dimension size = new Dimension(width, (int) (height * 0.9));
        boardPanel.setPreferredSize(size);
        boardPanel.setMaximumSize(size);

        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    }

    public void configure(TetrisViewModel viewModel) {
        this.boardPanel.setBoard(viewModel.getBoard());
        this.infoPanel.setScore(viewModel.getScore());
    }

    public void displayGameOverDialog(int score) {
        JFrame dialog = new JFrame();
        JPanel content = new JPanel();
        JButton exit = new JButton();
        JLabel info = new JLabel("Game Over! Your score was " + score + ".");

        exit.setText("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        dialog.setSize(200, 100);
        info.setPreferredSize(new Dimension(dialog.getWidth() - 10, dialog.getHeight() / 2));
        exit.setPreferredSize(new Dimension(dialog.getWidth() - 10, dialog.getHeight() / 2));

        dialog.setLocation(new Point((int) (this.getLocation().getX() + this.getWidth() / 2), (int) (this.getLocation().getY() + this.getHeight() / 2)));
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);

        exit.setFocusable(false);

        content.add(info);
        content.add(exit);
        dialog.add(content);

        dialog.setVisible(true);
    }
}
