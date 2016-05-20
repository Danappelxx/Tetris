import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import Shapes.Shape;
import Util.Point;
import Util.Tile;

/**
 * Created by dan on 5/9/16.
 */

public class ShapeInMotion {
    private Shape shape;
    private Point point;

    public ShapeInMotion(Shape shape, Point point) {
        this.shape = shape;
        this.point = point;
    }

    // MARK: Movement logic
    public boolean canGoDown(Board board) {

        List<Point> shapeHighestPoints = getHighestPoints();
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

    public boolean canGoLeft(Board board) {

        List<Point> shapeLeftmostPoints = getLeftmostPoints();

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

    public boolean canGoRight(Board board) {

        List<Point> shapeRightmostPoints = getRightmostPoints();

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

    public boolean canRotate(Board board) {

        // create a copy so that work is not destructive
        ShapeInMotion copy = copy();

        // rotate copy
        copy.getShape().rotate();

        List<Point> points = copy.getPoints();

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
                .anyMatch(point -> board.getTiles()[point.getY()][point.getX()] != null);

        if (overlaps) return false;

        return true;
    }

    /**
     * Takes the shape's tiles and adds them to the Board.
     */
    public void addToBoard(Board board) {
        for (int row = 0; row < Shape.NUM_ROWS; row++) {
            for (int col = 0; col < Shape.NUM_COLS; col++) {
                if (shape.getTiles()[row][col] != null) {
                    Tile tile = shape.getTiles()[row][col];
                    board.getTiles()[row + point.getY()][col + point.getX()] = tile;
                }
            }
        }
    }

    public Point relativePoint(Point other) {
        return new Point(
            other.getX() + point.getX(),
            other.getY() + point.getY());
    }

    public List<Point> getHighestPoints() {
        return shape.getHighestPoints()
                .stream()
                .map(this::relativePoint)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Point> getLeftmostPoints() {
        return shape.getLeftmostPoints()
                .stream()
                .map(this::relativePoint)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Point> getRightmostPoints() {
        return shape.getRightmostPoints()
                .stream()
                .map(this::relativePoint)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ShapeInMotion copy() {
        Shape shapeCopy = shape.copy();
        return new ShapeInMotion(shapeCopy, new Point(point.getX(), point.getY()));
    }

    public List<Point> getPoints() {
        List<Point> points = new ArrayList<Point>();
        for (int x = 0; x < Shape.NUM_COLS; x++)
            for (int y = 0; y < Shape.NUM_ROWS; y++)
                if (shape.getTiles()[y][x] != null)
                    points.add(this.relativePoint(new Point(x, y)));
        return points;
    }

    public Shape getShape() {
        return shape;
    }

    public Point getPoint() { return point; }
    public void setPoint(Point point) { this.point = point; }
}
