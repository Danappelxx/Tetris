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

    private ShapeInMotion createShapeInMotion() {
        Shape shape = Shape.createRandomShape();

        int minX = 0;
        int maxX = board.getWidth() - shape.getWidth();

        int x = (int)(Math.random() * maxX) + minX;
        int y = 0 - shape.getLowestPoint().getY();

        return new ShapeInMotion(shape, x, y);
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

        if (!canShapeGoDown()) {
            board.addShapeInMotion(shapeInMotion);
            shapeInMotion = null;
            return;
        }

        shapeInMotion.setY(shapeInMotion.getY() + 1);
    }
    public void shiftLeftIfPossible() {
        if (shapeInMotion == null) return;

        if (!canShapeGoLeft()) return;

        shapeInMotion.setX(shapeInMotion.getX() - 1);
    }
    public void shiftRightIfPossible() {
        if (shapeInMotion == null) return;

        if (!canShapeGoRight()) return;

        shapeInMotion.setX(shapeInMotion.getX() + 1);
    }
    public void rotateIfPossible() {
        if (shapeInMotion == null) return;

        if (!canShapeRotate()) return;

        shapeInMotion.getShape().rotate();
    }

    // MARK: Movement logic
    private boolean canShapeGoDown() {

        List<Point> shapeHighestPoints = shapeInMotion.getHighestPointsForEachX();
        int boardHighestY = board.getTiles().length - 1;

        // check if it is at the floor
        for (Point point: shapeHighestPoints) {
            if (point.getY() >= boardHighestY) return false;
        }

        // check if it has a tile below it
        for (Point point: shapeHighestPoints) {
            Tile tileBelow = board.getTiles()[point.getY() + 1][point.getX()];

            if (tileBelow != null) return false;
        }

        return true;
    }
    private boolean canShapeGoLeft() {

        List<Point> shapeLeftmostPoints = shapeInMotion.getLeftmostPointsForEachY();

        // check if it is next to the wall
        for (Point point: shapeLeftmostPoints) {
            if (point.getX() <= 0) return false;
        }

        // check if its leftmost tiles have a tile to the left of them
        for (Point point: shapeLeftmostPoints) {
            Tile tileLeft = board.getTiles()[point.getY()][point.getX() - 1];

            if (tileLeft != null) return false;
        }

        return true;
    }
    private boolean canShapeGoRight() {

        List<Point> shapeRightmostPoints = shapeInMotion.getRightmostPointsForEachY();

        // check if it is next to the wall
        for (Point point: shapeRightmostPoints) {
            if (point.getX() >= board.getWidth() - 1) return false;
        }

        // check if its rightmost tiles have a tile to the right of them
        for (Point point: shapeRightmostPoints) {
            Tile tileRight = board.getTiles()[point.getY()][point.getX() + 1];

            if (tileRight != null) return false;
        }

        return true;
    }
    private boolean canShapeRotate() {

        // create a copy so that work is not destructive
        ShapeInMotion shapeInMotion = this.shapeInMotion.copy();

        // rotate copy
        shapeInMotion.getShape().rotate();

        List<Point> points = shapeInMotion.getPoints();

        // ensure that no points of the rotated shape are out of bounds
        Boolean outOfBounds = points.stream()
                .anyMatch(point -> !(
                    point.getX() >= 0 &&
                    point.getX() < board.getWidth() &&
                    point.getY() >= 0 &&
                    point.getY() < board.getHeight()
                ));

        if (outOfBounds) return false;

        // ensure that no points of the rotated shape overlap with current points on the board
        Boolean overlaps = points.stream()
                .anyMatch(point -> this.board.getTiles()[point.getY()][point.getX()] != null);

        if (overlaps) return false;

        return true;
    }

    public ShapeInMotion getShapeInMotion() {
        return shapeInMotion;
    }
    public Board getBoardCopy() {
        return new Board(board);
    }
}
