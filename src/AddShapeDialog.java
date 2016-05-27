import Shapes.Shape;
import Shapes.ShapeBoard;
import Util.Board;
import Util.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by dan on 5/26/16.
 */

public class AddShapeDialog extends JDialog {
    Board board;
    BoardPanel boardPanel;

    JButton confirmButton;
    JButton cancelButton;

    Shape createdShape;

    public AddShapeDialog() {
        super((Frame) null, "Add Shape", true);

        setSize(350, 400);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        board = new Board(5, 5);
        board.fillBlank();
        boardPanel = new BoardPanel();
        boardPanel.setBoard(board);

        add(boardPanel);

        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(this.getWidth(), 100));
        panel.setLayout(new GridLayout(1, 2));

        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");

        confirmButton.setFocusable(false);
        cancelButton.setFocusable(false);

        panel.add(confirmButton);
        panel.add(cancelButton);

        add(panel);

        setupMouseListeners();
        setupActionListeners();
    }

    private void setupMouseListeners() {
        requestFocus();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                final int toolbarHeight = 25;
                Integer row = boardPanel.getRowForY(e.getY() - toolbarHeight);
                Integer col = boardPanel.getColForX(e.getX());
                if (row == null || col == null) return;

                Tile selected = board.getTiles()[row][col];
                if (selected.getColor() == null)
                    selected.setColor(Color.PINK);
                else
                    selected.setColor(null);

                // trigger the board reset hook
                boardPanel.setBoard(board);

                // redraw
                boardPanel.repaint();
            }
        });
    }

    private void setupActionListeners() {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createdShape = constructShape();
                setVisible(false);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    public Shape getCreatedShape() {
        setVisible(true);
        System.out.println(createdShape.getBoard().toString());
        return createdShape;
    }

    private String[] getStringRepresentation() {
        String[] representation = new String[board.numRows];

        for (int row = 0; row < board.numRows; row++) {
            representation[row] = "";
            for (int col = 0; col < board.numCols; col++)
                if (board.getTiles()[row][col].getColor() == null)
                    representation[row] += "-";
                else
                    representation[row] += "x";
        }

        return representation;
    }

    private Shape constructShape() {
        return new Shape() {
            @Override
            protected String[] getStringRepresentation() {
                return AddShapeDialog.this.getStringRepresentation().clone();
            }

            @Override
            public Shape copy() {
                Shape copy = constructShape();
                ShapeBoard board = new ShapeBoard(getBoard());
                copy.setBoard(board);
                copy.setColor(Shape.getRandomColor());
                return copy;
            }
        };
    }
}
