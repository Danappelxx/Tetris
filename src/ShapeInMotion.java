import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by dan on 5/9/16.
 */

public class ShapeInMotion {
    private Shape shape;
    private int x, y;

    public ShapeInMotion(Shape shape, int x, int y) {
        this.shape = shape;
        this.x = x;
        this.y = y;
    }

    public Point relativePoint(Point other) {
        return new Point(
            other.getX() + x,
            other.getY() + y);
    }

    public List<Point> getHighestPoints() {
        return shape.getHighestPoints()
                .stream()
                .map(this::relativePoint)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Point> getHighestPointsForEachX() {
        return shape.getHighestPointsForEachX()
                .stream()
                .map(this::relativePoint)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Point> getLeftmostPointsForEachY() {
        return shape.getLeftmostPointsForEachY()
                .stream()
                .map(this::relativePoint)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Point> getRightmostPointsForEachY() {
        return shape.getRightmostPointsForEachY()
                .stream()
                .map(this::relativePoint)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Shape getShape() {
        return shape;
    }

    public int getRelativeX() { return shape.getLeftmostPoint().getX() + x; }
    public int getRelativeY() { return shape.getLowestPoint().getY() + y; }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
