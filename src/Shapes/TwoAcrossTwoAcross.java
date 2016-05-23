package Shapes;

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

    @Override
    protected void createBoard() {
        this.board = new ShapeBoard() {
            @Override
            public void rotate() {
                // squares dont rotate, so do nothing
            }
        };
    }
}
