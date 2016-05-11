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

    public void createShapeInMotionIfNecessary() {
        if (shapeInMotion != null) return;

        Shape shape = Shape.createRandomShape();

        int minX = 0;
        int maxX = board.getWidth() - shape.getWidth();
        int x = (int)(Math.random() * maxX) + minX;

        // TODO: do this correctly
        int y = shape.getLowestPoint().getY();

        this.shapeInMotion = new ShapeInMotion(shape, x, y);
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

        List<Point> shapeHighestPoints = shapeInMotion.getHighestPoints();
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

        // check if it is next to the wall
        if (shapeInMotion.getRelativeX() <= 0) return false;

        // TODO: check if its leftmost tiles dont have a tile to the left of them

        return true;
    }
    private boolean canShapeGoRight() {

        // check if it is next to the right wall
        if (shapeInMotion.getRelativeX() + shapeInMotion.getShape().getWidth() >= board.getWidth()) return false;

        // TODO: check if its rightmost tiles dont have a tile to the right of them

        return true;
    }
    private boolean canShapeRotate() {
        // TODO: Implement
        return true;
    }


    public ShapeInMotion getShapeInMotion() {
        return shapeInMotion;
    }
    public Board getBoardCopy() {
        return new Board(board);
    }
}
