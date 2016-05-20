package Shapes;

import Util.Point;
import junit.framework.TestCase;
import java.util.List;

/**
 * Created by dan on 5/11/16.
 */

public class ShapeTest extends TestCase {

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

    Shape testShape;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        testShape = new TestShape();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        testShape = null;
    }


    public void testGetWidth() throws Exception {
        int width = testShape.getWidth();
        System.out.println(testShape.toString());
        assertEquals(width, 3);
    }


    public void testGetLowestPoint() throws Exception {
        Point point = testShape.getLowestPoint();

        assertEquals(point.getY(), 1);
        assertEquals(point.getX(), 2);
    }

    public void testGetLeftmostPoint() throws Exception {
        Point point = testShape.getLeftmostPoint();

        assertEquals(point.getX(), 1);
        assertEquals(point.getY(), 2);
    }

    public void testGetRightmostPoint() throws Exception {
        Point point = testShape.getRightmostPoint();

        assertEquals(point.getX(), 3);
        assertEquals(point.getY(), 1);
    }


    public void testGetHighestYForX() throws Exception {
        int for0 = testShape.getHighestYForX(0);
        int for1 = testShape.getHighestYForX(1);
        int for2 = testShape.getHighestYForX(2);

        assertEquals(for0, -1);
        assertEquals(for1, 2);
        assertEquals(for2, 2);
    }

    public void testGetLeftmostXForY() throws Exception {
        int for0 = testShape.getLeftmostXForY(0);
        int for1 = testShape.getLeftmostXForY(1);
        int for2 = testShape.getLeftmostXForY(2);

        assertEquals(for0, -1);
        assertEquals(for1, 2);
        assertEquals(for2, 1);
    }

    public void testGetRightmostXForY() throws Exception {
        int for0 = testShape.getRightmostXForY(0);
        int for1 = testShape.getRightmostXForY(1);
        int for2 = testShape.getRightmostXForY(2);

        assertEquals(for0, -1);
        assertEquals(for1, 3);
        assertEquals(for2, 2);
    }


    public void testGetHighestPoints() throws Exception {
        List<Point> points = testShape.getHighestPoints();

        assertEquals(points.size(), 3);

        assertEquals(points.get(0).getX(), 1);
        assertEquals(points.get(0).getY(), 2);

        assertEquals(points.get(1).getX(), 2);
        assertEquals(points.get(1).getY(), 2);

        assertEquals(points.get(2).getX(), 3);
        assertEquals(points.get(2).getY(), 1);
    }

    public void testGetLeftmostPoints() throws Exception {
        List<Point> points = testShape.getLeftmostPoints();

        assertEquals(points.size(), 2);

        assertEquals(points.get(0).getX(), 2);
        assertEquals(points.get(0).getY(), 1);

        assertEquals(points.get(1).getX(), 1);
        assertEquals(points.get(1).getY(), 2);
    }

    public void testGetRightmostPoints() throws Exception {
        List<Point> points = testShape.getRightmostPoints();

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
        assertEquals(testShape.toString(), original);

        testShape.rotate();

        String rotated =
                "-----\n" +
                "--x--\n" +
                "--xx-\n" +
                "---x-\n" +
                "-----\n";
        assertEquals(testShape.toString(), rotated);
    }


    public void testToString() throws Exception {
        String expected =
                "-----\n" +
                "--xx-\n" +
                "-xx--\n" +
                "-----\n" +
                "-----\n";
        assertEquals(testShape.toString(), expected);
    }
}