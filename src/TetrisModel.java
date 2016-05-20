import Util.Point;
import Util.Tile;
import Shapes.Shape;
import java.util.List;

/**
 * Created by dan on 5/10/16.
 */

public class TetrisModel {

    private ShapeInMotion shapeInMotion;
    private Board board;

    public TetrisModel() {
        this.board = new Board();
    }

    // Mark: Smart Factory Methods
    private ShapeInMotion createShapeInMotion() {
        Shape shape = Shape.createRandomShape();

        int minX = 0;
        int maxX = board.getWidth() - shape.getWidth();

        int x = (int)(Math.random() * maxX) + minX;
        int y = 0 - shape.getLowestPoint().getY();

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


    // MARK: Executing movement
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

        shapeInMotion.getShape().rotate();
    }

    // MARK: Getters & Setters
    public ShapeInMotion getShapeInMotion() {
        return shapeInMotion;
    }
    public Board getBoardCopy() {
        return new Board(board);
    }
}
