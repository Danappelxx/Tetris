package Shapes;

import Util.Board;
import Util.Point;
import Util.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dan on 5/20/16.
 */

public class ShapeBoard extends Board {
    public ShapeBoard() {
        super(5, 5);
    }
    public ShapeBoard(Board other) {
        super(other);
    }

    /**
     * Rotates the tiles using the center as the center of rotation.
     * Shape should be in the middle for the result to be correct.
     */
    public void rotate() {
        Tile[][] rotated = new Tile[numRows][numCols];

        // rotate current tiles, store in `rotated`
        for (int x = 0; x < numRows; x++) {
            for (int y = 0; y < numCols; y++) {
                rotated[x][y] = tiles[numRows - y - 1][x];
            }
        }

        this.tiles = rotated;
    }

    // MARK: Getters & Setters
    public Point getLowestPoint() {
        for (int y = 0; y < numRows; y++)
            for (int x = 0; x < numCols; x++)
                if (tiles[y][x] != null)
                    return new Point(x, y);
        return null;
    }

    public Point getLeftmostPoint() {
        for (int x = 0; x < numRows; x++)
            for (int y = 0; y < numCols; y++)
                if (tiles[y][x] != null)
                    return new Point(x, y);
        return null;
    }

    public Point getRightmostPoint() {
        for (int x = numCols - 1; x >= 0; x--)
            for (int y = 0; y < numRows; y++)
                if (tiles[y][x] != null)
                    return new Point(x, y);
        return null;
    }


    /**
     * Returns a list of points representing the highest Y for that X
     */
    public List<Point> getHighestPoints() {
        List<Point> points = new ArrayList<Point>();
        for (int x = 0; x < numCols; x++) {
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
        for (int y = 0; y < numRows; y++) {
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
        for (int y = 0; y < numRows; y++) {
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


    @Override
    public int getWidth() {
        return getRightmostPoint().getX() - getLeftmostPoint().getX() + 1;
    }

    public Tile[] getRow(int row) { return tiles[row]; }

    public Tile[] getCol(int col) {
        Tile[] colArr = new Tile[numRows];
        for (int row = 0; row < numRows; row++)
            colArr[row] = tiles[row][col];
        return colArr;
    }
}
