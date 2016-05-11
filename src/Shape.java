import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

    protected Tile[][] tiles = new Tile[NUM_ROWS][NUM_COLS];

    protected Color color;

    protected abstract String[] getStringRepresentation();

    public Shape() {
        // TODO: Generate color randomly
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

    public void rotate() {
        // TODO: Implement
    }

    public boolean isATile(int row, int col) {
        return tiles[row][col] != null;
    }
    public Tile getTile(int row, int col) { return tiles[row][col]; }

    public Tile[] getRow(int row) { return tiles[row]; }
    public Tile[] getCol(int col) {
        Tile[] colArr = new Tile[NUM_COLS];
        for (int row = 0; row < NUM_ROWS; row++)
            colArr[row] = tiles[row][col];
        return colArr;
    }

    public int getHighestYForX(int x) {
        Tile[] col = getCol(x);
        for (int y = col.length - 1; y >= 0; y--) {
            if (col[y] != null) return y;
        }
        return -1;
    }
    public Point getHighestPoint() {
        int largestY = -1;
        int largestX = -1;

        for (int x = 0; x < NUM_COLS; x++) {
            int curr = getHighestYForX(x);
            if (curr > largestY) {
                largestY = curr;
                largestX = x;
            }
        }

        return new Point(largestX, largestY);
    }
    public List<Point> getHighestPoints() {
        List<Point> points = new ArrayList<Point>();

        int highestY = (int)getHighestPoint().getY();
        for (int x = 0; x < NUM_COLS; x++)
            if (tiles[highestY][x] != null) points.add(new Point(x, highestY));

        return points;
    }

    public int getLowestYForX(int x) {
        Tile[] col = getCol(x);
        for (int y = 0; y < col.length; y++) {
            if (col[y] != null) return y;
        }
        return -1;
    }
    public Point getLowestPoint() {
        int smallestY = Integer.MAX_VALUE;
        int smallestX = Integer.MAX_VALUE;

        for (int x = 0; x < NUM_COLS; x++) {
            int curr = getLowestYForX(x);
            if (curr < smallestY) {
                smallestY = curr;
                smallestX = x;
            }
        }

        return new Point(smallestX, smallestY);
    }
    public List<Point> getLowestPoints() {
        List<Point> points = new ArrayList<Point>();

        int lowestY = (int)getLowestPoint().getY();
        for (int x = 0; x < NUM_COLS; x++)
            if (tiles[lowestY][x] != null) points.add(new Point(x, lowestY));

        return points;
    }

    public Point getLeftmostPoint() {
        for (int x = 0; x < NUM_COLS; x++) {
            for (int y = 0; y < NUM_ROWS; y++) {
                if (tiles[y][x] != null) return new Point(x, y);
            }
        }
        return null;
    }
    public Point getRightmostPoint() {
        for (int x = NUM_COLS - 1; x >= 0; x--) {
            for (int y = 0; y < NUM_ROWS; y++) {
                if (tiles[y][x] != null) return new Point(x, y);
            }
        }
        return null;
    }

    public int getWidth() {
        return (int)(getRightmostPoint().getX() - getLeftmostPoint().getX()) + 1;
    }
}
