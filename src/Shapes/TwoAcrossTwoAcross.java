package Shapes;

import Util.Board;

/**
 * Created by dan on 5/18/16.
 */

public class TwoAcrossTwoAcross extends Shape {

    public TwoAcrossTwoAcross() {
        super();
    }

    protected String[] getStringRepresentation() {
        return new String[]{
                "-----",
                "-xx--",
                "-xx--",
                "-----",
                "-----"
        };
    }

    public static class TwoAcrossTwoAcrossShapeBoard extends ShapeBoard {
        // boilerplate for dynamic instantiation
        public TwoAcrossTwoAcrossShapeBoard() {
            super();
        }
        public TwoAcrossTwoAcrossShapeBoard(Board other) {
            super(other);
        }

        @Override
        public void rotate() {
            // squares dont rotate, so do nothing
        }
    }

    @Override
    protected Class<? extends ShapeBoard> getBoardClass() {
        return TwoAcrossTwoAcrossShapeBoard.class;
    }
}
