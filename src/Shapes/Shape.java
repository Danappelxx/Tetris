package Shapes;

import Util.Board;
import Util.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static List<Shape> SHAPE_TEMPLATES = new ArrayList(Arrays.asList(new Shape[]{
            new FiveAcross(),
            new ThreeAcrossOneDownLeft(),
            new ThreeAcrossOneDownRight(),
            new ThreeAcrossOneUp(),
            new TwoAcrossTwoAcross(),
            new TwoAcrossTwoAcrossLeft(),
            new TwoAcrossTwoAcrossRight()
    }));

    protected ShapeBoard board;

    protected Color color;

    protected abstract String[] getStringRepresentation();

    public Shape() {
        try {
            board = getBoardClass().newInstance();
        } catch (Exception e) {}

        color = Shape.getRandomColor();

        String[] representation = getStringRepresentation();

        for (int row = 0; row < board.numRows; row++) {
            for (int col = 0; col < board.numCols; col++) {
                // if tile should be here, make it. otherwise, keep it null
                if (representation[row].charAt(col) == 'x') {
                    board.getTiles()[row][col] = new Tile();
                    board.getTiles()[row][col].setColor(color);
                }
            }
        }
    }

    public static Color getRandomColor() {
        return COLORS[(int)(Math.random()*COLORS.length)];
    }

    public Shape copy() {
        try {
            Shape copy = this.getClass().newInstance();
            copy.board = this.getBoardClass().getConstructor(Board.class).newInstance(this.getBoard());
            copy.color = this.color;
            return copy;
        } catch (Exception e) {
            return null;
        }
    }

    public static Shape createRandomShape() {
        Shape template = Shape.SHAPE_TEMPLATES.get((int)(Math.random() * Shape.SHAPE_TEMPLATES.size()));
        Shape copy = template.copy();
        copy.setColor(Shape.getRandomColor());
        return copy;
    }

    protected Class<? extends ShapeBoard> getBoardClass() {
        return ShapeBoard.class;
    }

    public ShapeBoard getBoard() {
        return this.board;
    }

    public void setBoard(ShapeBoard board) {
        this.board = board;
    }

    public void setColor(Color newColor) {
        this.board.forEachTile(tile -> {
            if (tile == null) return;
            if (tile.getColor() == color)
                tile.setColor(newColor);
        });
        this.color = newColor;
    }
}
