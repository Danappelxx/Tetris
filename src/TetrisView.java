import javax.swing.*;
import java.awt.*;

/**
 * Created by dan on 5/9/16.
 */

public class TetrisView extends JFrame {

    private BoardPanel boardPanel;

    public TetrisView() {
        super("Tetris");

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        final double height = dim.getHeight() - 150;
        final double width = (height / 2) - 25;
        this.setSize((int) (width), (int) (height));
        this.setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2 - getHeight() / 2);

        boardPanel = new BoardPanel();

        add(boardPanel);
        boardPanel.setSize(this.getSize());
    }

    public void configure(TetrisViewModel viewModel) {
        this.boardPanel.setBoard(viewModel.getBoard());
    }
}
