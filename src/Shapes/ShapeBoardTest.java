package Shapes;

import Util.Point;
import junit.framework.TestCase;
import java.util.List;

/**
 * Created by dan on 5/11/16.
 */

public class ShapeBoardTest extends TestCase {

    private class TestShape extends Shape {
        public TestShape() {
            super();
        }
        protected String[] getStringRepresentation() {
            return new String[]{
                    "-----",
                    "--xx-",
                    "-xx--",
                    "-----",
                    "-----"
            };
        }
    }

    ShapeBoard board;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        board = new TestShape().getBoard();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        board = null;
    }


    public void testGetWidth() throws Exception {
        int width = board.getWidth();
        System.out.println(board.toString());
        assertEquals(width, 3);
    }


    public void testGetLowestPoint() throws Exception {
        Point point = board.getLowestPoint();

        assertEquals(point.getY(), 1);
        assertEquals(point.getX(), 2);
    }

    public void testGetLeftmostPoint() throws Exception {
        Point point = board.getLeftmostPoint();

        assertEquals(point.getX(), 1);
        assertEquals(point.getY(), 2);
    }

    public void testGetRightmostPoint() throws Exception {
        Point point = board.getRightmostPoint();

        assertEquals(point.getX(), 3);
        assertEquals(point.getY(), 1);
    }


    public void testGetHighestYForX() throws Exception {
        int for0 = board.getHighestYForX(0);
        int for1 = board.getHighestYForX(1);
        int for2 = board.getHighestYForX(2);

        assertEquals(for0, -1);
        assertEquals(for1, 2);
        assertEquals(for2, 2);
    }

    public void testGetLeftmostXForY() throws Exception {
        int for0 = board.getLeftmostXForY(0);
        int for1 = board.getLeftmostXForY(1);
        int for2 = board.getLeftmostXForY(2);

        assertEquals(for0, -1);
        assertEquals(for1, 2);
        assertEquals(for2, 1);
    }

    public void testGetRightmostXForY() throws Exception {
        int for0 = board.getRightmostXForY(0);
        int for1 = board.getRightmostXForY(1);
        int for2 = board.getRightmostXForY(2);

        assertEquals(for0, -1);
        assertEquals(for1, 3);
        assertEquals(for2, 2);
    }


    public void testGetHighestPoints() throws Exception {
        List<Point> points = board.getHighestPoints();

        assertEquals(points.size(), 3);

        assertEquals(points.get(0).getX(), 1);
        assertEquals(points.get(0).getY(), 2);

        assertEquals(points.get(1).getX(), 2);
        assertEquals(points.get(1).getY(), 2);

        assertEquals(points.get(2).getX(), 3);
        assertEquals(points.get(2).getY(), 1);
    }

    public void testGetLeftmostPoints() throws Exception {
        List<Point> points = board.getLeftmostPoints();

        assertEquals(points.size(), 2);

        assertEquals(points.get(0).getX(), 2);
        assertEquals(points.get(0).getY(), 1);

        assertEquals(points.get(1).getX(), 1);
        assertEquals(points.get(1).getY(), 2);
    }

    public void testGetRightmostPoints() throws Exception {
        List<Point> points = board.getRightmostPoints();

        assertEquals(points.size(), 2);

        assertEquals(points.get(0).getX(), 3);
        assertEquals(points.get(0).getY(), 1);

        assertEquals(points.get(1).getX(), 2);
        assertEquals(points.get(1).getY(), 2);
    }


    public void testRotate() throws Exception {

        String original =
                "-----\n" +
                "--xx-\n" +
                "-xx--\n" +
                "-----\n" +
                "-----\n";
        assertEquals(board.toString(), original);

        board.rotate();

        String rotated =
                "-----\n" +
                "--x--\n" +
                "--xx-\n" +
                "---x-\n" +
                "-----\n";
        assertEquals(board.toString(), rotated);
    }


    public void testToString() throws Exception {
        String expected =
                "-----\n" +
                "--xx-\n" +
                "-xx--\n" +
                "-----\n" +
                "-----\n";
        assertEquals(board.toString(), expected);
    }
}