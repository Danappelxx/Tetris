package Util;

/**
 * Created by dan on 5/11/16.
 */

public class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
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

    public Point copy() { return new Point(x, y); }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
