package Shapes;

import Util.Tile;
import com.sun.istack.internal.NotNull;
import java.awt.*;

/**
 * Created by dan on 5/9/16.
 */

public abstract class Shape {

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

    protected ShapeBoard board;

    protected Color color;

    protected abstract String[] getStringRepresentation();

    public Shape() {
        createBoard();

        color = COLORS[(int)(Math.random()*COLORS.length)];

        for (int row = 0; row < board.numRows; row++) {
            for (int col = 0; col < board.numCols; col++) {
                // if tile should be here, make it. otherwise, keep it null
                if (getStringRepresentation()[row].charAt(col) == 'x') {
                    board.getTiles()[row][col] = new Tile();
                    board.getTiles()[row][col].setColor(color);
                }
            }
        }
    }

    public Shape copy() {
        try {
            Shape copy = this.getClass().newInstance();
            copy.board = new ShapeBoard(this.board);
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

    protected void createBoard() {
        this.board = new ShapeBoard();
    }

    public ShapeBoard getBoard() {
        return this.board;
    }
}
