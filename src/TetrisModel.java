import Shapes.Shape;
import Util.Board;
import Util.Point;
import Util.Tile;

import java.awt.*;

/**
 * Created by dan on 5/10/16.
 */

public class TetrisModel {

    private ShapeInMotion shapeInMotion;
    private Board board;
    private int rowsCleared;

    public TetrisModel() {
        this.board = new Board(20, 10);
    }

    // Mark: Smart Factory Methods
    private ShapeInMotion createShapeInMotion() {
        Shape shape = Shape.createRandomShape();

        int minX = 0;
        int maxX = board.getWidth() - shape.getBoard().getWidth();

        int x = (int)(Math.random() * maxX) + minX;
        int y = -shape.getBoard().getLowestPoint().getY();

        return new ShapeInMotion(shape, new Point(x, y));
    }

    public void createShapeInMotionIfNecessary() throws TetrisController.GameOverException {
        if (shapeInMotion != null) return;

        ShapeInMotion shapeInMotion = createShapeInMotion();

        // ensure that no points of the rotated shape overlap with current points on the board
        Boolean overlaps = shapeInMotion.getPoints().stream()
                .anyMatch(point -> this.board.getTiles()[point.getY()][point.getX()] != null);

        if (overlaps) throw new TetrisController.GameOverException();

        this.shapeInMotion = shapeInMotion;
    }

    public void checkGameOver() throws TetrisController.GameOverException {
        // check every tile in topmost row
        // if any tiles are _not_ null, game over
        for (Tile tile: board.getTiles()[0])
            if (tile != null)
                throw new TetrisController.GameOverException();
    }

    // MARK: Executing movement
    public void drop() {
        drop(shapeInMotion);
    }

    public void drop(ShapeInMotion shape) {

        if (shape == null) return;

        while (shape.canGoDown(board)) {
            shape.getPoint().setY(shape.getPoint().getY() + 1);
        }
    }

    public void shiftDownIfPossible() {
        if (shapeInMotion == null) return;

        if (!shapeInMotion.canGoDown(board)) {
            shapeInMotion.addToBoard(board);
            shapeInMotion = null;
            return;
        }

        shapeInMotion.getPoint().setY(shapeInMotion.getPoint().getY() + 1);
    }

    public void shiftLeftIfPossible() {
        if (shapeInMotion == null) return;

        if (!shapeInMotion.canGoLeft(board)) return ;

        shapeInMotion.getPoint().setX(shapeInMotion.getPoint().getX() - 1);
    }

    public void shiftRightIfPossible() {
        if (shapeInMotion == null) return;

        if (!shapeInMotion.canGoRight(board)) return;

        shapeInMotion.getPoint().setX(shapeInMotion.getPoint().getX() + 1);
    }

    public void rotateIfPossible() {
        if (shapeInMotion == null) return;

        if (!shapeInMotion.canRotate(board)) return;

        shapeInMotion.getShape().getBoard().rotate();
    }

    /**
     * Checks if any row is full and if so clears it and shifts the rows down.
     * Clears from the bottom up.
     * Only checks for tiles that have fallen (shape in motion is ignored).
     */
    public void clearRows() {

        int rowsCleared = 0;

        for (int _ = 0; _ < board.numRows; _++)
            rowLoop:
            for (int row = board.numRows - 1; row >= 0; row--) {

                // check if each column has a tile
                for (int col = 0; col < board.numCols; col++)
                    // if it doesnt have a tile, skip this row
                    if (board.getTiles()[row][col] == null) continue rowLoop;

                board.removeRow(row);
                rowsCleared++;
            }

        // score multiplier
        this.rowsCleared += rowsCleared * rowsCleared;
    }

    public ShapeInMotion getShadow() {
        ShapeInMotion shadow = new ShapeInMotion(getShapeInMotion().getShape().copy(), getShapeInMotion().getPoint().copy());
        shadow.getShape().getBoard().forEachTile(tile -> {
            if (tile != null)
                tile.setColor(Color.GRAY);
        });

        drop(shadow);
        return shadow;
    }

    // MARK: Getters & Setters
    public ShapeInMotion getShapeInMotion() {
        return shapeInMotion;
    }
    public Board getBoardCopy() {
        return new Board(board);
    }
    public int getRowsCleared() {
        return rowsCleared;
    }
}
