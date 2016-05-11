import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by dan on 5/10/16.
 */

public class TetrisModel {

    private ShapeInMotion shapeInMotion;
    private Board board;

    public TetrisModel() {
        this.board = new Board();
    }

    private Shape makeRandomShape() {
        Class<?> shapeClass = Shape.SHAPE_CLASSES[(int)(Math.random() * Shape.SHAPE_CLASSES.length)];
        try {
            return ((Class<Shape>)shapeClass).newInstance();
        } catch (Exception e) {
            return null;
        }
    }
    public void makeShapeInMotionIfNecessary() {
        if (shapeInMotion != null) return;

        Shape shape = makeRandomShape();

        int minX = 0;
        int maxX = board.getTiles()[0].length - shape.getWidth();
        int x = (int)(Math.random() * maxX) + minX;

        int y = (int)shape.getLeftmostPoint().getY();

        this.shapeInMotion = new ShapeInMotion(shape, x, y);
    }

    public void shiftDownIfPossible() {
        if (shapeInMotion == null) return;

        if (!canShapeGoDown()) {
            board.addShapeInMotion(shapeInMotion);
            shapeInMotion = null;
            return;
        }

        shapeInMotion.setY(shapeInMotion.getY() + 1);
    }
    private boolean canShapeGoDown() {
         ArrayList<Point> shapeHighestPoints = shapeInMotion.getShape().getHighestPoints()
                 .stream()
                 .map(shapeInMotion::relativePoint)
                 .collect(Collectors.toCollection(ArrayList::new));

        int boardHighestY = board.getTiles().length - 1;

        // check if it is at the floor
        for (Point point: shapeHighestPoints) {
            if (point.getY() >= boardHighestY) return false;
        }

        // check if it has a tile below it
        for (Point point: shapeHighestPoints) {
            Tile tileBelow = board.getTiles()[(int)point.getY() + 1][(int)point.getX()];

            if (tileBelow != null) return false;
        }

        return true;
    }

    public void shiftLeftIfPossible() {
        if (shapeInMotion == null) return;

        if (shapeInMotion.getX() <= 0) return;

        shapeInMotion.setX(shapeInMotion.getX() - 1);
    }
    public void shiftRightIfPossible() {
        if (shapeInMotion == null) return;

        if (shapeInMotion.getX() + shapeInMotion.getShape().getWidth() >= board.getTiles()[0].length) return;

        shapeInMotion.setX(shapeInMotion.getX() + 1);
    }

    public void rotateIfPossible() {
        if (shapeInMotion == null) return;
        shapeInMotion.getShape().rotate();
    }

    public ShapeInMotion getShapeInMotion() {
        return shapeInMotion;
    }
    public void setShapeInMotion(ShapeInMotion shapeInMotion) {
        this.shapeInMotion = shapeInMotion;
    }

    public Board getBoardCopy() {
        return new Board(board);
    }
    public void setBoard(Board board) {
        this.board = board;
    }
}
