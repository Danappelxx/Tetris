package Shapes;

/**
 * Created by dan on 5/18/16.
 */

public class TwoAcrossTwoAcrossLeft extends Shape {
    public TwoAcrossTwoAcrossLeft() {
        super();
    }

    protected String[] getStringRepresentation() {
        return new String[]{
                "-----",
                "--xx-",
                "-xx---",
                "-----",
                "-----"
        };
    }
}
