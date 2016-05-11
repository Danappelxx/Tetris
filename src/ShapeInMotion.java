import java.awt.*;

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
            (int)other.getX() + x,
            (int)other.getY() + y);
    }

    public Shape getShape() {
        return shape;
    }

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
