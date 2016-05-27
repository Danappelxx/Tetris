import Util.Board;
import javax.swing.*;
import java.awt.*;

/**
 * Created by dan on 5/9/16.
 */

public class BoardPanel extends JPanel {
    public static final Color DEFAULT_TILE_COLOR = new Color(238, 238, 238);

    private Board board;

    public BoardPanel() {}

    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    private int tileWidth;
    private int tileHeight;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (board == null) return;

        Color oldColor = g.getColor();

        final int xOffset = 10;
        this.minX = xOffset;
        final int yOffset = 10;
        this.minY = yOffset;
        this.tileWidth = (getWidth() - xOffset*2) / board.numCols;
        this.tileHeight = tileWidth;

        for (int row = 0; row < board.numRows; row++) {
            for (int col = 0; col < board.numCols; col++) {
                int x = xOffset + tileWidth*col;
                int y = yOffset + tileHeight * row;

                maxX = x + tileWidth;
                maxY = y + tileHeight;

                // white outline
                g.setColor(Color.WHITE);
                g.drawRect(x, y, tileWidth, tileHeight);

                // fill of actual color
                g.setColor(board.getTiles()[row][col].getColor());
                g.fillRect(x + 1, y + 1, tileWidth - 1, tileHeight - 1);
            }
        }

        g.setColor(oldColor);
    }

    public void setBoard(Board board) {
        this.board = new Board(board);

        this.board.forEachTile(tile -> {
            if (tile.getColor() == null) {
                tile.setColor(DEFAULT_TILE_COLOR);
            }
        });
    }

    public Integer getColForX(int x) {
        if (x < minX || x > maxX) return null;
        return (x - minX) / tileWidth;
    }

    public Integer getRowForY(int y) {
        if (y < minY || y > maxY) return null;
        return (y - minY) / tileHeight;
    }
}
