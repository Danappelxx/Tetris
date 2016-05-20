package Shapes;

import com.sun.istack.internal.NotNull;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import Util.Tile;
import Util.Point;

/**
 * Created by dan on 5/9/16.
 */

public abstract class Shape {
    public static final int NUM_COLS = 5;
    public static final int NUM_ROWS = 5;

    public static final Color[] COLORS = new Color[]{
        Color.RED,
        Color.BLUE,
        Color.GREEN,
        Color.ORANGE,
        Color.CYAN,
        Color.PINK,
        Color.YELLOW
    };

    public static final Class<?>[] SHAPE_CLASSES = new Class<?>[]{
            FiveAcross.class,
            ThreeAcrossOneDownLeft.class,
            ThreeAcrossOneDownRight.class,
            ThreeAcrossOneUp.class,
            TwoAcrossTwoAcross.class,
            TwoAcrossTwoAcrossLeft.class,
            TwoAcrossTwoAcrossRight.class
    };

    private Tile[][] tiles = new Tile[NUM_ROWS][NUM_COLS];

    protected Color color;

    protected abstract String[] getStringRepresentation();

    public Shape() {
        color = COLORS[(int)(Math.random()*COLORS.length)];

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                // if tile should be here, make it. otherwise, keep it null
                if (getStringRepresentation()[row].charAt(col) == 'x') {
                    tiles[row][col] = new Tile();
                    tiles[row][col].setColor(color);
                }
            }
        }
    }

    /**
     * Rotates the tiles using the center as the center of rotation.
     * Shape should be in the middle for the result to be correct.
     */
    public void rotate() {
        Tile[][] rotated = new Tile[NUM_ROWS][NUM_COLS];

        // rotate current tiles, store in `rotated`
        for (int x = 0; x < NUM_ROWS; x++) {
            for (int y = 0; y < NUM_ROWS; y++) {
                rotated[x][y] = tiles[NUM_ROWS - y - 1][x];
            }
        }

        this.tiles = rotated;
    }

    public Shape copy() {
        try {
            Shape copy = this.getClass().newInstance();
            copy.tiles = this.tiles;
            copy.color = this.color;
            return copy;
        } catch (Exception e) {
            return null;
        }
    }

    @NotNull
    public static Shape createRandomShape() {
        Class<?> shapeClass = Shape.SHAPE_CLASSES[(int)(Math.random() * Shape.SHAPE_CLASSES.length)];
        try {
            return ((Class<Shape>)shapeClass).newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int y = 0; y < NUM_ROWS; y++) {
            for (int x = 0; x < NUM_COLS; x++) {
                result += tiles[y][x] == null ? "-" : "x";
            }
            result += "\n";
        }
        return result;
    }

    // MARK: Getters & Setters
    public Point getLowestPoint() {
        for (int y = 0; y < NUM_ROWS; y++)
            for (int x = 0; x < NUM_COLS; x++)
                if (tiles[y][x] != null)
                    return new Point(x, y);
        return null;
    }

    public Point getLeftmostPoint() {
        for (int x = 0; x < NUM_COLS; x++)
            for (int y = 0; y < NUM_ROWS; y++)
                if (tiles[y][x] != null)
                    return new Point(x, y);
        return null;
    }

    public Point getRightmostPoint() {
        for (int x = NUM_COLS - 1; x >= 0; x--)
            for (int y = 0; y < NUM_ROWS; y++)
                if (tiles[y][x] != null)
                    return new Point(x, y);
        return null;
    }


    /**
     * Returns a list of points representing the highest Y for that X
     */
    public List<Point> getHighestPoints() {
        List<Point> points = new ArrayList<Point>();
        for (int x = 0; x < NUM_COLS; x++) {
            int y = getHighestYForX(x);
            if (y >= 0) points.add(new Point(x, y));
        }
        return points;
    }
    int getHighestYForX(int x) {
        Tile[] col = getCol(x);
        for (int y = col.length - 1; y >= 0; y--) {
            if (col[y] != null) return y;
        }
        return -1;
    }

    /**
     * Returns a list of points representing the lowest X for that Y
     */
    public List<Point> getLeftmostPoints() {
        List<Point> points = new ArrayList<Point>();
        for (int y = 0; y < NUM_ROWS; y++) {
            int x = getLeftmostXForY(y);
            if (x >= 0) points.add(new Point(x, y));
        }
        return points;
    }
    int getLeftmostXForY(int y) {
        Tile[] row = getRow(y);
        for (int x = 0; x < row.length; x++) {
            if (row[x] != null) return x;
        }
        return -1;
    }

    /**
     * Returns a list of points representing the highest X for that Y
     */
    public List<Point> getRightmostPoints() {
        List<Point> points = new ArrayList<Point>();
        for (int y = 0; y < NUM_ROWS; y++) {
            int x = getRightmostXForY(y);
            if (x >= 0) points.add(new Point(x, y));
        }
        return points;
    }
    int getRightmostXForY(int y) {
        Tile[] row = getRow(y);
        for (int x = row.length - 1; x >= 0; x--) {
            if (row[x] != null) return x;
        }
        return -1;
    }


    public int getWidth() {
        return getRightmostPoint().getX() - getLeftmostPoint().getX() + 1;
    }


    public Tile[][] getTiles() {
        return this.tiles;
    }

    public Tile[] getRow(int row) { return tiles[row]; }

    public Tile[] getCol(int col) {
        Tile[] colArr = new Tile[NUM_COLS];
        for (int row = 0; row < NUM_ROWS; row++)
            colArr[row] = tiles[row][col];
        return colArr;
    }
}