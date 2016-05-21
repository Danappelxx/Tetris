import javax.swing.*;
import java.awt.*;

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
}
